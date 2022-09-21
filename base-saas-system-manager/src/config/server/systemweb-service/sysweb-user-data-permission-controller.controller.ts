/*
*systemweb 用户权限管理controller
*/
import {requestType, servicePreName} from '~/config/enum.config'
/*
* 后台服务名称
*/
const SERVICE = servicePreName + 'system-web'
const CONTROLLER = 'userDatePermission'
/*
* 前端服务名称
*/
export default {
  /**
   * 添加数据权限
   */
  addDataPermission: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'addDataPermission',
    type: requestType.Post
  },
  /**
   * 获取数据权限
   */
  getUserDataPermisson: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getUserDataPermisson',
    type: requestType.Get
  }
}
