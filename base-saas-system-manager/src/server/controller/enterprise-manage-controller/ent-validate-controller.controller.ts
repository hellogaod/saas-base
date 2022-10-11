/*
*systemweb 图形验证码controller
*/
import {requestType, servicePreName} from '~/server/enum.config'
/*
* 后台服务名称
*/
const SERVICE = servicePreName + 'system-web'
const CONTROLLER = 'validate/code'
/*
* 前端服务名称
*/
export default {

  captchaApi: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'get',
    type: requestType.Get
  },

  check: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'check',
    type: requestType.Get
  },
  
}
