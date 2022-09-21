// import store from "~/store";

export default {
  /**
   * 用户部门数据
   * @param param0
   */
  departmentData(state) {
    let departmentList: Array<any> = state.departmentList

    if (!departmentList) {
      return
    }

    // 生成部门结构数据
    let fun = node => {
      // 递归对象子元素
      let children = departmentList.filter(x => node.id === x.parentId).map(fun);

      if (children && children.length) {
        return Object.assign({}, node, { children })
      } else {
        return node
      }
    };

    // 过滤父节点
    let rootList = departmentList.filter(x => {
      if (!x.parentId) {
        return true;
      }

      return !departmentList.find(item => item.id === x.parentId);
    });

    // 生成树形结构
    return rootList.map(fun)
  },
  /**
   * 用户部门数据
   * @param param0
   */
  visitDepartmentData(state) {
    let departmentList: Array<any> = state.departmentList.filter(x => x.type == 2)

    if (!departmentList) {
      return
    }

    // 生成部门结构数据
    let fun = node => {
      // 递归对象子元素
      let children = departmentList.filter(x => node.id === x.parentId).map(fun);

      if (children && children.length) {
        return Object.assign({}, node, { children })
      } else {
        return node
      }
    };

    // 过滤父节点
    let rootList = departmentList.filter(x => {
      if (!x.parentId) {
        return true;
      }

      return !departmentList.find(item => item.id === x.parentId);
    });

    // 生成树形结构
    return rootList.map(fun)
  },
     /**
   * 电话催收部门数据
   * @param param0
   */
  callDepartmentData(state) {
    let departmentList: Array<any> = state.departmentList.filter(x =>  x.type == 1)

    if (!departmentList) {
      return
    }
    // 生成部门结构数据
    let fun = node => {
      // 递归对象子元素
      let children = departmentList.filter(x => node.id === x.parentId).map(fun);

      if (children && children.length) {
        return Object.assign({}, node, { children })
      } else {
        return node
      }
    };

    // 过滤父节点
    let rootList = departmentList.filter(x => {
      if (!x.parentId) {
        return true;
      }

      return !departmentList.find(item => item.id === x.parentId);
    });

    // 生成树形结构
    return rootList.map(fun)
  },

  /** 
   * 返回是否超级管理员
  */
  isSupperAdmin(state) {
    return state.userData.id === "0o0oo0o0-0o0o-0000-0000-0ooo000o0o0o"
  }
}
