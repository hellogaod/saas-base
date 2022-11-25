/*
*systemweb 用户管理controller
*/
import {requestType, managerServiceName} from '~/server/enum.config'
/*
* 后台服务名称
*/
const CONTROLLER = 'entUser'
/*
* 前端服务名称
*/
export default {
  /**
   * 添加用户
   */
  addUser: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'addUser',
    type: requestType.Post
  },
  /**
   * 查询单个用户
   */
  getUserById: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getUserById',
    type: requestType.Get
  },
  /**
   * 获取用户列表
   */
  getUserList: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getUserList',
    type: requestType.Get
  },
  /**
   * 重置密码
   */
  resetPassword: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'resetPassword',
    type: requestType.Post
  },
  /**
   * 用户启用停用
   */
  updateStatus: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'updateStatus',
    type: requestType.Post
  },
  /**
   * 修改用户信息
   */
  updateUser: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'updateUser',
    type: requestType.Post
  }
}
