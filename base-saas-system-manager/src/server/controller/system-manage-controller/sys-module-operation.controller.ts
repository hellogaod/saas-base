import {requestType, managerServiceName} from '~/server/enum.config'
/*
* 后台服务名称
*/
// const SERVICE = 'system-manage' //測試
const CONTROLLER = 'sysMenu'

export default {
  /**
   * 查询所有资源
   */
  getAllMenuTree: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getAllMenuList',
    type: requestType.Get
  },
  getAllMenuDetailList:{
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getAllMenuDetailList',
    type: requestType.Get
  },
  getOneMenu:{
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getOneMenu',
    type: requestType.Get
  },
  addMenu:{
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'addMenu',
    type: requestType.Post
  },
  updateMenuStatus:{
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'updateMenuStatus',
    type: requestType.Post
  },
  getMenuById:{
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getMenuById',
    type: requestType.Get
  },
  editMenu:{
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'editMenu',
    type: requestType.Post
  },
  checkMenuChildStatus:{
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'checkMenuChildStatus',
    type: requestType.Get
  },
}

