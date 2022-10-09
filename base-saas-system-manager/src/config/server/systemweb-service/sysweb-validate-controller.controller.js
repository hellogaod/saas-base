/*
*systemweb 图形验证码controller
*/
import { requestType, servicePreName } from '~/config/enum.config';
/*
* 后台服务名称
*/
var SERVICE = servicePreName + 'system-web';
var CONTROLLER = 'validate/code';
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
};