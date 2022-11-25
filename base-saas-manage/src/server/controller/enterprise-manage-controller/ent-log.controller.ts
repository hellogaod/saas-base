/*
*systemweb 日志管理controller
*/
import {requestType, managerServiceName} from '~/server/enum.config'
/*
* 后台服务名称
*/
const CONTROLLER = 'entLog'
/*
* 前端服务名称
*/
export default {
  /**
   * 登录日志列表
   */
  getLogList: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getLogList',
    type: requestType.Get
  },
  /**
   * 登录日志查看
   */
  getErrorLogById: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getErrorLogById',
    type: requestType.Get
  },
}
