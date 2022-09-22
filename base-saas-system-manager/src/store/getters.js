// import store from "~/store";
export default {
    /**
     * 用户部门数据
     * @param param0
     */
    departmentData: function (state) {
        var departmentList = state.departmentList;
        if (!departmentList) {
            return;
        }
        // 生成部门结构数据
        var fun = function (node) {
            // 递归对象子元素
            var children = departmentList.filter(function (x) { return node.id === x.parentId; }).map(fun);
            if (children && children.length) {
                return Object.assign({}, node, { children: children });
            }
            else {
                return node;
            }
        };
        // 过滤父节点
        var rootList = departmentList.filter(function (x) {
            if (!x.parentId) {
                return true;
            }
            return !departmentList.find(function (item) { return item.id === x.parentId; });
        });
        // 生成树形结构
        return rootList.map(fun);
    },
    /**
     * 用户部门数据
     * @param param0
     */
    visitDepartmentData: function (state) {
        var departmentList = state.departmentList.filter(function (x) { return x.type == 2; });
        if (!departmentList) {
            return;
        }
        // 生成部门结构数据
        var fun = function (node) {
            // 递归对象子元素
            var children = departmentList.filter(function (x) { return node.id === x.parentId; }).map(fun);
            if (children && children.length) {
                return Object.assign({}, node, { children: children });
            }
            else {
                return node;
            }
        };
        // 过滤父节点
        var rootList = departmentList.filter(function (x) {
            if (!x.parentId) {
                return true;
            }
            return !departmentList.find(function (item) { return item.id === x.parentId; });
        });
        // 生成树形结构
        return rootList.map(fun);
    },
    /**
  * 电话催收部门数据
  * @param param0
  */
    callDepartmentData: function (state) {
        var departmentList = state.departmentList.filter(function (x) { return x.type == 1; });
        if (!departmentList) {
            return;
        }
        // 生成部门结构数据
        var fun = function (node) {
            // 递归对象子元素
            var children = departmentList.filter(function (x) { return node.id === x.parentId; }).map(fun);
            if (children && children.length) {
                return Object.assign({}, node, { children: children });
            }
            else {
                return node;
            }
        };
        // 过滤父节点
        var rootList = departmentList.filter(function (x) {
            if (!x.parentId) {
                return true;
            }
            return !departmentList.find(function (item) { return item.id === x.parentId; });
        });
        // 生成树形结构
        return rootList.map(fun);
    },
    /**
     * 返回是否超级管理员
    */
    isSupperAdmin: function (state) {
        return state.userData.id === "0o0oo0o0-0o0o-0000-0000-0ooo000o0o0o";
    }
};
