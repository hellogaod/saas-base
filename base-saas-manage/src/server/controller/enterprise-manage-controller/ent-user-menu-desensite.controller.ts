/*
*systemweb 用户权限管理controller
*/
import {requestType, managerServiceName} from '~/server/enum.config'
/*
* 后台服务名称
*/
const CONTROLLER = 'entUserDatePermission'
/*
* 前端服务名称
*/
export default {
  /**
   * 添加数据权限
   */
  addDataPermission: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'operationDesensite',
    type: requestType.Post
  },
  /**
   * 获取数据权限
   */
  getUserDataPermisson: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getUserDataPermisson',
    type: requestType.Get
  }
}
