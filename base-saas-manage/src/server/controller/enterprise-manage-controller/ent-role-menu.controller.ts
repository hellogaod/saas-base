/*
*systemweb 角色权限管理controller
*/
import {requestType, managerServiceName} from '~/server/enum.config'
/*
* 后台服务名称
*/
const CONTROLLER = 'entRoleMenu'
/*
* 前端服务名称
*/
export default {
  /**
   * 添加角色权限
   */
  addRoleMenu: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'addRoleMenu',
    type: requestType.Post
  },
  /**
   * 根据角色编号查询已有的菜单编号
   */
  getMenuByRoleId: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getMenuByRoleId',
    type: requestType.Get
  },
  /**
   * 获取菜单
   */
  getMenuListByRoleId: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getMenuTreeByRoleId',
    type: requestType.Get
  }
}
