/*
*systemweb 字典管理controller
*/
import { requestType, servicePreName } from '~/config/enum.config';
/*
* 后台服务名称
*/
var SERVICE = servicePreName + 'system-web';
var CONTROLLER = 'rich';
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
};
