/*
*systemweb 角色管理controller
*/
import {requestType, servicePreName} from '~/server/enum.config'
/*
* 后台服务名称
*/
const SERVICE = servicePreName + 'system-web'
const CONTROLLER = 'role'
/*
* 前端服务名称
*/
export default {
  /**
   * 添加角色
   */
  addRole: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'addRole',
    type: requestType.Post
  },
  /**
   * 角色分配，获取角色
   */
  getRole: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getRole',
    type: requestType.Get
  },
  /**
   * 查询单个角色
   */
  getRoleById: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getRoleById',
    type: requestType.Get
  },
  /**
   * 获取角色列表
   */
  getRoleList: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getRoleList',
    type: requestType.Get
  },
  /**
   * 修改角色信息
   */
  updateRole: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'updateRole',
    type: requestType.Post
  },
   /**
   * 角色启用停用
   */
  updateStatus: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'updateStatus',
    type: requestType.Post
  },
}
