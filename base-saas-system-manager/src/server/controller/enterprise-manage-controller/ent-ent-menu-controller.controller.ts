/*
*systemweb 系统菜单controller
*/
import {requestType, servicePreName} from '~/server/enum.config'
/*
* 后台服务名称
*/
const SERVICE = servicePreName + 'system-web'
const CONTROLLER = 'entMenu'
/*
* 前端服务名称
*/
export default {
  /**
   * 获取菜单树的集合
   */
  getAllMenuTree: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getAllMenuTree',
    type: requestType.Get
  },
  /**
   * 获取企业模块对应下的菜单
   */
  getEntMenuByCode: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getEntMenuByCode',
    type: requestType.Get
  },
  /**
   * 获取菜单详情
   */
  getMenuDetail: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getMenuDetail',
    type: requestType.Get
  },
}
