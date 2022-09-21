/*
*systemweb 组织机构管理controller
*/
import {requestType, servicePreName} from '~/config/enum.config'
/*
* 后台服务名称
*/
const SERVICE = servicePreName + 'system-web'
const CONTROLLER = 'organization'
/*
* 前端服务名称
*/
export default {
  /**
   * 查询table数据列表
   */
  getOrgList: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getOrgList',
    type: requestType.Get
  },
  /**
   * 用户管理数据权限中获取组织架构的菜单树
   */
  getOrgByOrgId: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getOrgByOrgId',
    type: requestType.Get
  },
  /**
   * 保存
   */
  saveOrg: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'saveOrg',
    type: requestType.Post
  },

  /**
   * 修改弹框回显数据
   */
  getOrgById: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getOrgById',
    type: requestType.Get
  },

   /**
   * 修改弹框 根据orgId回显数据
   */
  updateOrg: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'updateOrg',
    type: requestType.Post
  },

  /**
   * 启用/停用
   */
  updateOrgStatus: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'updateOrgStatus',
    type: requestType.Post
  }
}
