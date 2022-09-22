/*
*manage 系统参数配置controller
*/
import { requestType, servicePreName } from '~/config/enum.config';
/*
* 后台服务名称
*/
var SERVICE = servicePreName + 'manage'; //本地
// const SERVICE = 'system-manage' //測試
var CONTROLLER = 'sysOther';
/*
* 前端服务名称
*/
export default {
    /**
     * 查询企业三方关联列表
     */
    getCompanyConfigList: {
        service: SERVICE,
        controller: CONTROLLER,
        action: 'getCompanyConfigList',
        type: requestType.Get
    },
    /**
     * 查询参数配置
     */
    getOtherConfigInfo: {
        service: SERVICE,
        controller: CONTROLLER,
        action: 'getOtherConfigInfo',
        type: requestType.Get
    },
    /**
     * 查询启用的三方列表
     */
    getOtherConfigList: {
        service: SERVICE,
        controller: CONTROLLER,
        action: 'getOtherConfigList',
        type: requestType.Get
    },
    /**
     * 查询配置列表
     */
    getOtherList: {
        service: SERVICE,
        controller: CONTROLLER,
        action: 'getOtherList',
        type: requestType.Get
    },
    /**
    * 保存参数配置
    */
    saveOtherConfig: {
        service: SERVICE,
        controller: CONTROLLER,
        action: 'saveOtherConfig',
        type: requestType.Post
    },
    /**
    * 修改配置
    */
    updateOther: {
        service: SERVICE,
        controller: CONTROLLER,
        action: 'updateOther',
        type: requestType.Post
    },
    /**
     * 修改参数配置
     */
    updateOtherConfig: {
        service: SERVICE,
        controller: CONTROLLER,
        action: 'updateOtherConfig',
        type: requestType.Post
    },
    /**
     * 修改状态
     */
    updateStatus: {
        service: SERVICE,
        controller: CONTROLLER,
        action: 'updateStatus',
        type: requestType.Post
    }
};
