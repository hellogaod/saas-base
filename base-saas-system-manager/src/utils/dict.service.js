import store from '~/store';
var state = store.state;
var DictService = /** @class */ (function () {
    function DictService() {
    }
    /**
     * 获取字典数据
     * @param codes
     */
    DictService.getDictData = function () {
        var codes = [];
        for (var _i = 0; _i < arguments.length; _i++) {
            codes[_i] = arguments[_i];
        }
        var results = [];
        // 获取字典项
        codes.forEach(function (code) {
            var items = state.dictData
                .filter(function (x) { return x.typeCode === code; })
                .map(function (x) { return ({
                value: x.id,
                label: x.name
            }); });
            results.push.apply(results, items);
        });
        return results.length === 1 ? results[0] : results;
    };
    return DictService;
}());
export { DictService };
