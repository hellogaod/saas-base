import {requestType, servicePreName} from '~/server/enum.config'
/*
* 后台服务名称
*/
const SERVICE = servicePreName +'manage' //本地
// const SERVICE = 'system-manage' //測試
const CONTROLLER = 'sysModuleDetail'

export default {
  /**
   * 查询所有资源
   */
  getAllMenuTree: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getAllMenuTree',
    type: requestType.Get
  },
  getAllMenuDetailList:{
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getAllMenuDetailList',
    type: requestType.Get
  },
  getOneMenu:{
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getOneMenu',
    type: requestType.Get
  },
  addMenu:{
    service: SERVICE,
    controller: CONTROLLER,
    action: 'addMenu',
    type: requestType.Post
  },
  updateMenuStatus:{
    service: SERVICE,
    controller: CONTROLLER,
    action: 'updateMenuStatus',
    type: requestType.Post
  },
  getMenuById:{
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getMenuById',
    type: requestType.Get
  },
  editMenu:{
    service: SERVICE,
    controller: CONTROLLER,
    action: 'editMenu',
    type: requestType.Post
  },
  checkMenuChildStatus:{
    service: SERVICE,
    controller: CONTROLLER,
    action: 'checkMenuChildStatus',
    type: requestType.Get
  },
}

