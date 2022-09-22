import Vue from 'vue';
var DataTransfer = /** @class */ (function () {
    function DataTransfer() {
    }
    /**
     * 格式化数据成树形结构
     */
    DataTransfer.treeToArray = function (data, parent, level, expandedAll) {
        var tmp = [];
        Array.from(data).forEach(function (record) {
            if (record._expanded === undefined) {
                Vue.set(record, '_expanded', expandedAll);
            }
            if (parent) {
                Vue.set(record, '_parent', parent);
            }
            var _level = 0;
            if (level !== undefined && level !== null) {
                _level = level + 1;
            }
            Vue.set(record, '_level', _level);
            tmp.push(record);
            if (record.children && record.children.length > 0) {
                var children = DataTransfer.treeToArray(record.children, record, _level, expandedAll);
                tmp = tmp.concat(children);
            }
        });
        return tmp;
    };
    return DataTransfer;
}());
export { DataTransfer };
