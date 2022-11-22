/*
*systemweb 字典管理controller
*/
import {requestType, managerServiceName} from '~/server/enum.config'
/*
* 后台服务名称
*/
const CONTROLLER = 'entDict'
/*
* 前端服务名称
*/
export default {
  /**
   * 查询大类数据列表
   */
  getDictItemList: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getDictList',
    type: requestType.Get
  },
  /**
   * 根据id查询字典明细
   */
  getDictDetailById: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getDictDetailById',
    type: requestType.Get
  },
  /**
   * 查询明细数据列表
   */
  getDictDetailList: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getDictDetailList',
    type: requestType.Get
  },
  /**
   * 根据id获取字典大类信息
   */
  getDictItemById: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getDictById',
    type: requestType.Get
  },
  /**
   * 保存字典明细
   */
  saveDictDetail: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'saveDictDetail',
    type: requestType.Post
  },
  /**
   * 保存字典大类
   */
  saveDictItem: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'saveDict',
    type: requestType.Post
  },
  /**
   * 修改字典明细
   */
  updateDictDetail: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'updateDictDetail',
    type: requestType.Post
  },
  /**
   * 修改字典明细状态
   */
  updateDictDetailStatus: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'updateDictDetailStatus',
    type: requestType.Post
  },
  /**
   * 修改字典大类
   */
  updateDictItem: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'updateDict',
    type: requestType.Post
  },
  /**
   * 修改字典大类状态
   */
  updateDictItemStatus: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'updateDictStatus',
    type: requestType.Post
  },
  /**
   * 根据字典code 查询
   /**
   * 根据字典编码查询字典明细
   */
  getDictDetailByItemCode: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getDictDetailByItemCode',
    type: requestType.Get
  },

  /**
   * 根据字典编码查询字典明细（多个code）
   */
  getDicDetailByItemList: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getDicDetailByItemList',
    type: requestType.Get
  },
}
