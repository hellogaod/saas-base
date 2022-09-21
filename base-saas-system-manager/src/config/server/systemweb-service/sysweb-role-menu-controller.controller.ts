/*
*systemweb 角色权限管理controller
*/
import {requestType, servicePreName} from '~/config/enum.config'
/*
* 后台服务名称
*/
const SERVICE = servicePreName + 'system-web'
const CONTROLLER = 'roleMenu'
/*
* 前端服务名称
*/
export default {
  /**
   * 添加角色权限
   */
  addRoleMenu: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'addRoleMenu',
    type: requestType.Post
  },
  /**
   * 根据角色编号查询已有的菜单编号
   */
  getMenuByRoleId: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getMenuByRoleId',
    type: requestType.Get
  },
  /**
   * 获取菜单
   */
  getMenuListByRoleId: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getMenuListByRoleId',
    type: requestType.Get
  }
}
