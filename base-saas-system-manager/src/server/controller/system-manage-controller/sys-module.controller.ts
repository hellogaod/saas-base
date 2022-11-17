/*
*manage 系统模块管理controller
*/
import {requestType, managerServiceName} from '~/server/enum.config'
/*
* 后台服务名称
*/
const CONTROLLER = 'sysModule'
/*
* 前端服务名称
*/
export default {
  /**
   * 查询已启用的模块列表
   */
  getEffectiveModule: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getEffectiveModule',
    type: requestType.Get
  },
  /**
   * 根据主键查询模块信息
   */
  getModuleInfo: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getModuleInfo',
    type: requestType.Get
  },
  /**
   * 查询模块列表
   */
  getModuleList: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getModuleList',
    type: requestType.Get
  },
  /**
   * 根据模块主键查询模块相关的菜单
   */
  getModuleMenuListBySysCode: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getModuleMenuListBySysCode',
    type: requestType.Get
  },
   /**
   * 保存系统模块
   */
  saveSysModule: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'saveSysModule',
    type: requestType.Post
  },
   /**
   * 修改系统状态
   */
  updateStatus: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'updateSysModuleStatus',
    type: requestType.Post
  },
  /**
   * 修改系统模块
   */
  updateSysModule: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'updateSysModule',
    type: requestType.Post
  }
}
