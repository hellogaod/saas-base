/*
*systemweb 字典管理controller
*/
import {requestType, servicePreName} from '~/server/enum.config'
/*
* 后台服务名称
*/
const SERVICE = servicePreName + 'system-web'
const CONTROLLER = 'rich'
/*
* 前端服务名称
*/
export default {
  /**
   * 
   */
  downLoadFile: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'downLoadFile',
    type: requestType.Get
  }
}
