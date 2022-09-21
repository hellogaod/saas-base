/*
*systemweb 活动推荐活动管理controller
*/
import {requestType, servicePreName} from '~/config/enum.config'
/*
* 后台服务名称
*/
const SERVICE = servicePreName + 'system-web'
const CONTROLLER = 'companyProfile'
/*
* 前端服务名称
*/
export default {
  /**
   * 保存
   */
  saveCompanyProfile: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'saveCompanyProfile',
    type: requestType.Post
  },
  /**
   * 获取
   */
  getCompanyProfile: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'getCompanyProfile',
    type: requestType.Get
  },

}
