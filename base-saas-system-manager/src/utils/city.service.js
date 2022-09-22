var cityData = require('~/assets/json/city.json');
var CityService = /** @class */ (function () {
    function CityService() {
    }
    /**
     * 获取城市信息
     * @param level
     * @param id
     */
    CityService.getCityData = function (_a) {
        var _b = _a === void 0 ? {} : _a, _c = _b.level, level = _c === void 0 ? 3 : _c, _d = _b.id, id = _d === void 0 ? 1 : _d;
        var fun = function (id, currentLevel) {
            if (currentLevel === void 0) { currentLevel = 1; }
            var items = new Array();
            cityData
                .filter(function (x) { return x.pid === id; })
                .forEach(function (x) {
                // 生成城市对象
                var item = {
                    value: x.id,
                    label: x.name
                };
                // 检测获取级别
                if (currentLevel < level) {
                    var children = fun(x.id, currentLevel + 1);
                    if (children && children.length > 0) {
                        item.children = children;
                    }
                }
                items.push(item);
            });
            return items;
        };
        return fun(id);
    };
    /**
     * 获取城市节点父元素
     * @param id
     */
    CityService.getCityParent = function (id) {
        var result = [];
        // 向根节点遍历
        var fun = function (itemId) {
            var item = cityData.find(function (x) { return x.id === itemId; });
            if (item && item.pid !== 1) {
                result.unshift(item.pid);
                fun(item.pid);
            }
        };
        fun(id);
        return result;
    };
    /**
     * 获取城市名称
     * @param id
     */
    CityService.getCityName = function () {
        var ids = [];
        for (var _i = 0; _i < arguments.length; _i++) {
            ids[_i] = arguments[_i];
        }
        var results = [];
        if (!ids[0]) {
            return '';
        }
        ids.forEach(function (id) {
            var item = cityData.find(function (c) { return c.id === id; });
            results.push(item.name);
        });
        return results.length === 1 ? results[0] : results;
    };
    return CityService;
}());
export { CityService };
