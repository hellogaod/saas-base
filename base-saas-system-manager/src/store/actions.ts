export default {

  /**
   * 更新用户登录数据
   */
  updateUserLoginData({state, commit, dispatch}, {token, userInfo, module}) {

    // 更新用户token
    if (token) {
      commit('updateUserToken', token);
    }
    if (userInfo) {
      // 更新公司编号
      commit('updateCompanyCode', userInfo.companyCode ? userInfo.companyCode : '');

      // 更新用户数据
      commit('updateUserData', {
        id: userInfo.userId,
        account: userInfo.account,
        companyName: userInfo.companyName,
        companyCode: userInfo.companyCode,
        orgId: userInfo.orgId,
        userType: userInfo.userType,
        realName: userInfo.realName,
      });
    }

    //更新模块和菜单信息
    if (module) {

      // 当前用户拥有的所有模块
      commit('updateUsermoduleListResource', module);

      //当前默认选中第一个模块
      commit('module', module[0])

      // 当前默认第一个模块下的所有菜单
      commit('updateUserMenuResource', module[0].menuList);
    }
  },
  /**
   * 更新用户选中模块数据
   */
  updateModuleData({state, commit, dispatch}, {data}) {
    commit('module', data);
  },
  /**
   * 清除登录数据
   */
  clearUserLoginData({commit}) {
    // 重置用户token
    commit('updateUserToken', "");
    // 更新用户菜单资源
    commit('updateUserMenuResource', []);

    // 重置用户数据
    commit('updateUserData', {})
    //重置token过期标识
    commit('updateTokenExpire', false);
    // 重置模块资源
    commit('updateUsermoduleListResource', []);
    //重置用户选中模块数据
    commit('module', {});
  }
}
