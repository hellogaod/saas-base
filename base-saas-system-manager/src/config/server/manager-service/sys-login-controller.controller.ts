/*
*manage 系统登录controller
*/
import {requestType, servicePreName} from '~/config/enum.config'
/*
* 后台服务名称
*/
const SERVICE = servicePreName +'manage' //本地
// const SERVICE = 'system-manage' //測試
const CONTROLLER = 'syslogin'
/*
* 前端服务名称
*/
export default {
  /**
   * 账号密码登录
   */
  login: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'syslogin',
    type: requestType.Post
  },
  /**
   * 获取用户权限url
   */
  queryUserPermission: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'queryUserPermission',
    type: requestType.Post
  },
  /**
   * 加载系统菜单
   */
  getMenu: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'index',
    type: requestType.Get
  },
  /**
   * 修改密码
   */
  updateUserPwd: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'updateUserPwd',
    type: requestType.Post
  },
  /**
   * 退出
   */
  logout: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'logout',
    type: requestType.Post
  }
}
