import {StorageUtil} from '~/utils/storage.util'

//调用这里面的方法，用于更新state字段信息
export default {

  /**
   * 更新页面布局
   * @param state
   * @param layout
   */
  updateLayout(state, layout) {
    state.layout = layout || 'default'
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
    StorageUtil.getStorage('session').setItem('userToken', token)
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
   * 更新菜单
   * @param state
   * @param rescource
   */
  updateUserMenuResource(state, rescource) {
    state.menuResource = rescource
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
