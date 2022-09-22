import injector from 'vue-inject';
var Service = /** @class */ (function () {
    function Service() {
    }
    Service.getInstance = function () {
        var instance;
        var key = this.name.replace(this.name.charAt(0), this.name.charAt(0).toLocaleLowerCase());
        if (!injector.$$factories[key]) {
            injector.service(key, this);
        }
        try {
            instance = injector.get(key);
        }
        catch (ex) {
            instance = new this();
        }
        return instance;
    };
    return Service;
}());
export { Service };
