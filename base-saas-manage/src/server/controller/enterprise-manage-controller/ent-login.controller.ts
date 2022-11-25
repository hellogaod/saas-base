/*
*systemweb 系统登录controller
*/
import {requestType, managerServiceName} from '~/server/enum.config'
/*
* 后台服务名称
*/
const CONTROLLER = 'entlogin'
/*
* 前端服务名称
*/
export default {
  /**
   * 账号密码登录
   */
  doLogin: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'doLogin',
    type: requestType.Post
  },
  /**
   * 菜单和模块
   */
  index: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'index',
    type: requestType.Get
  },
   /**
   * 修改密码
   */
  updateUserPwd: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'updateUserPwd',
    type: requestType.Post
  },
    /**
   * 退出
   */
  logout: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'logout',
    type: requestType.Post
  }
}
