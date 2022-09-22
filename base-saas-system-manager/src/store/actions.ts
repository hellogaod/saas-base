import router from '~/router'
import { ReminderService } from "~/utils/reminder.service"

// const creatCompanyService = () => import('~/services/business-service/company.service')
// const createDepartmentService = () => import('~/services/business-service/department.service')

export default {
  /**
   * 更新公司列表
   */
  async getCompanyList({ getters, dispatch, commit }) {
    // let { CompanyService } = await creatCompanyService()

    // CompanyService.getInstance()
    //   .getCompanyList()
    //   .subscribe(data => {
    //     commit('updateCompanyList', data)
    //   })
  },
  /**
   * 更新门店列表
   */
  async getStoreList({ state, commit }) {
    // let { DepartmentService } = await createDepartmentService()

    // DepartmentService.getInstance()
    //   .queryShopDept(state.userData.companyCode)
    //   .subscribe(data => {
    //     commit('updateStoreList', data)
    //   })
  },
  /**
   * 获取部门数据
   * @param param0
   */
  async getDepartmentList({ commit }) {
    // let { DepartmentService } = await createDepartmentService()

    // DepartmentService.getInstance()
    //   .queryOwnDepartment()
    //   .subscribe(data => {
    //     commit('updateDepartmentList', data)
    //   });
  },
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
      // 更新控件资源
      commit('updateUserControlResource', data.allBtnIds);
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
    // 重置用户控件资源
    commit('updateUserControlResource', []);
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
