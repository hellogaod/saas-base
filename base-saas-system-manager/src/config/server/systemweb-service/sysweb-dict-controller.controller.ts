/*
*systemweb 字典管理controller
*/
import {requestType, servicePreName} from '~/config/enum.config'
/*
* 后台服务名称
*/
const SERVICE = servicePreName + 'system-web'
const CONTROLLER = 'dict'
/*
* 前端服务名称
*/
export default {
  /**
   * 查询大类数据列表
   */
  getDictItemList: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getDictItemList',
    type: requestType.Get
  },
  /**
   * 根据id查询字典明细
   */
  getDictDetailById: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getDictDetailById',
    type: requestType.Get
  },
  /**
   * 查询明细数据列表
   */
  getDictDetailList: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getDictDetailList',
    type: requestType.Get
  },
  /**
   * 根据id获取字典大类信息
   */
  getDictItemById: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getDictItemById',
    type: requestType.Get
  },
  /**
   * 保存字典明细
   */
  saveDictDetail: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'saveDictDetail',
    type: requestType.Post
  },
  /**
   * 保存字典大类
   */
  saveDictItem: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'saveDictItem',
    type: requestType.Post
  },
  /**
   * 修改字典明细
   */
  updateDictDetail: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'updateDictDetail',
    type: requestType.Post
  },
  /**
   * 修改字典明细状态
   */
  updateDictDetailStatus: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'updateDictDetailStatus',
    type: requestType.Post
  },
  /**
   * 修改字典大类
   */
  updateDictItem: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'updateDictItem',
    type: requestType.Post
  },
  /**
   * 修改字典大类状态
   */
  updateDictItemStatus: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'updateDictItemStatus',
    type: requestType.Post
  },
  /**
   * 根据字典code 查询
    /**
   * 根据字典编码查询字典明细
   */
  getDictDetailByItemCode: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getDictDetailByItemCode',
    type: requestType.Get
  },
  
  /**
   * 根据字典编码查询字典明细（多个code）
   */
  getDicDetailByItemList: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getDicDetailByItemList',
    type: requestType.Get
  },
}
