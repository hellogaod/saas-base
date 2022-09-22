import { StorageService } from '~/utils/storage.service'

export default {
  /**
   * 更新菜单折叠状态
   * @param state
   * @param collapse
   */
  updateMenuCollapse(state, collapse) {
    state.menuCollapse = collapse
  },
  /**
   * 更新页面布局
   * @param state
   * @param layout
   */
  updateLayout(state, layout) {
    state.layout = layout || 'default'
  },
  /**
   * 更新字典数据
   * @param state
   * @param data
   */
  updateDictData(state, data) {
    state.dictData = data
  },
  /**
   * 更新用户token
   * @param state
   * @param token
   */
  updateUserToken(state, token) {
    if (!token) {
      state.tokenExpire = false
    }
    state.userToken = token
    StorageService.getStorage('session').setItem('userToken', token)
  },
  /**
   * 更新用户数据
   * @param state
   * @param user
   */
  updateUserData(state, user) {
    state.userData = user
  },
  /**
   * 更新用户角色信息
   * @param state
   * @param role
   */
  updateUserRole(state, role) {
    state.userRole = role
  },
  /**
   * 更新菜单
   * @param state
   * @param rescource
   */
  updateUserMenuResource(state, rescource) {
    state.menuResource = rescource
  },
  /**
  * 更新用户资源信息
  * @param state
  * @param rescource
  */
  updateUserControlResource(state, rescource) {
    state.controlResource = rescource
  },
  /**
   * 更新公司数据
   * @param state
   * @param data
   */
  updateCompanyList(state, data) {
    state.companyList = data
  },
  /**
   * 更新委托方数据
   * @param state
   * @param data
   */
  updatePrincipalList(state, data) {
    state.principalList = data
  },
  /**
   * 更新门店数据
   * @param state
   * @param data
   */
  updateStoreList(state, data) {
    state.storeList = data
  },
  updateOutSourceList(state, data) {
    state.outSourceList = data
  },
  /**
   * 更新部门数据
   * @param state
   * @param data
   */
  updateDepartmentList(state, data) {
    state.departmentList = data
  },
  /**
   * 更新公司编号
   * @param state
   * @param data
   */
  updateCompanyCode(state, data) {
    state.companyCode = data
  },
  /**
   * 修改token过期标识
   * @param state
   * @param data
   */
  updateTokenExpire(state, data) {
    state.tokenExpire = data
  },
  /**
   * 更新模块资源
   * @param state
   * @param data
   */
  updateUsermoduleListResource(state, data) {
    state.updateUsermoduleListResource = data
  },
  /**
   * 更新模块选中
   * @param state
   * @param data
   */
  module(state, data) {
    state.module = data
  },
  /**
   * 更新初始化状态
   * @param state
   */
  ready(state) {
    state.ready = true
  }
}
