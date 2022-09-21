/*
*systemweb 日志管理controller
*/
import {requestType, servicePreName} from '~/config/enum.config'
/*
* 后台服务名称
*/
const SERVICE = servicePreName + 'system-web'
const CONTROLLER = 'webLog'
/*
* 前端服务名称
*/
export default {
  /**
   * 登录日志列表
   */
    getLogList: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getLogList',
    type: requestType.Get
  },
    /**
   * 登录日志查看
   */
  getErrorLogById: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getErrorLogById',
    type: requestType.Get
  },
}
