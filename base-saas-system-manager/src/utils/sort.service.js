var sortType = {
    "ascending": "asc",
    "descending": "desc"
};
var SortService = /** @class */ (function () {
    function SortService() {
        this.sort = {};
    }
    /**
     * 转换排序对象为字符串
     */
    SortService.stringify = function (value) {
        if (typeof value !== "object") {
            return "";
        }
        if (value instanceof SortService) {
            value = value.sort;
        }
        return Object.entries(value).map(function (_a) {
            var k = _a[0], v = _a[1];
            return "sort=" + k + "," + v;
        }).join('&');
    };
    /**
     * 更新分页配置信息
     * @param param
     */
    SortService.prototype.update = function (key, value) {
        if (key == null || value == null) {
            return this.reset();
        }
        this.sort = (_a = {},
            _a[key] = sortType[value] || value,
            _a);
        var _a;
    };
    /**
     * 重置分页数据
     */
    SortService.prototype.reset = function () {
        this.sort = {};
    };
    return SortService;
}());
export { SortService };
