/*
*systemweb 图形验证码controller
*/
import {requestType, commonServiceName} from '~/server/enum.config'
/*
* 后台服务名称
*/
const CONTROLLER = 'validate/code'
/*
* 前端服务名称
*/
export default {

  captchaApi: {
    service: commonServiceName,
    controller: CONTROLLER,
    action: 'get',
    type: requestType.Get
  },

  check: {
    service: commonServiceName,
    controller: CONTROLLER,
    action: 'check',
    type: requestType.Get
  },

}
