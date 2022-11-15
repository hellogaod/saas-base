/*
*systemweb 系统参数配置controller
*/
import {requestType, servicePreName} from '~/server/enum.config'
/*
* 后台服务名称
*/
const SERVICE = servicePreName + 'system-web'
const CONTROLLER = 'sysOtherConfig'
/*
* 前端服务名称
*/
export default {
  /**
   * 查询数据
   */
  getCompnaySysPara: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getCompnaySysPara',
    type: requestType.Get
  },

  /**
   * 查询数据
   */
  saveSysPara: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'saveSysPara',
    type: requestType.Post
  }
}
