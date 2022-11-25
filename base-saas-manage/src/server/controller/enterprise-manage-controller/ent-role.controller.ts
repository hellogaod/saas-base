/*
*systemweb 角色管理controller
*/
import {requestType, managerServiceName} from '~/server/enum.config'
/*
* 后台服务名称
*/
const CONTROLLER = 'entRole'
/*
* 前端服务名称
*/
export default {
  /**
   * 添加角色
   */
  addRole: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'addRole',
    type: requestType.Post
  },
  /**
   * 角色分配，获取角色
   */
  getRole: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getRole',
    type: requestType.Get
  },
  /**
   * 查询单个角色
   */
  getRoleById: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getRoleById',
    type: requestType.Get
  },
  /**
   * 获取角色列表
   */
  getRoleList: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getRoleList',
    type: requestType.Get
  },
  /**
   * 修改角色信息
   */
  updateRole: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'updateRole',
    type: requestType.Post
  },
   /**
   * 角色启用停用
   */
  updateStatus: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'updateStatus',
    type: requestType.Post
  },
}
