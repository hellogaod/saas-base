
export default {

  /**
   * 更新用户登录数据
   */
  updateUserLoginData({ state, commit, dispatch }, { token, userInfo, data }) {
    // 更新用户token
    if (!!token) {
      commit('updateUserToken', token);
    }
    if(userInfo){
      // 更新公司编号
      commit('updateCompanyCode', userInfo.companyCode?userInfo.companyCode:'');

      // 更新用户数据
      commit('updateUserData', {
        id: userInfo.userId,
        account: userInfo.account,
        companyName: userInfo.companyName,
        companyCode: userInfo.companyCode,
        dataPermission: userInfo.dataPermission,
        orgId: userInfo.orgId,
        permissionList: userInfo.permissionList,
        userType: userInfo.userType,
        realName: userInfo.realName,
      });
    }
    if(data){
      // 更新用户控件资源
      commit('updateUserMenuResource', data.menuList);

      if(data.moduleList){
        // 更新模块资源-
        commit('updateUsermoduleListResource', data.moduleList);
        commit('module',data.moduleList[0])
      }
    }
  },
  /**
   * 更新用户选中模块数据
   */
  updateModuleData({ state, commit, dispatch }, { data }) {
    commit('module', data);
  },
  /**
   * 清除登录数据
   */
  clearUserLoginData({ commit }) {
    // 重置用户token
    commit('updateUserToken', "");
    // 更新用户菜单资源
    commit('updateUserMenuResource', []);

    // 重置用户数据
    commit('updateUserData', {})
    //重置token过期标识
    commit('updateTokenExpire', false);
     // 重置模块资源
    commit('updateUsermoduleListResource',[]);
    //重置用户选中模块数据
    commit('module', {});
  }
}
