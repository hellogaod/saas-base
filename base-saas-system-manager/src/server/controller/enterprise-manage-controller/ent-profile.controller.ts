/*
*systemweb 公司简介配置controller
*/
import {requestType, managerServiceName} from '~/server/enum.config'
/*
* 后台服务名称
*/
const CONTROLLER = 'entProfile'
/*
* 前端服务名称
*/
export default {
  /**
   * 保存
   */
  saveCompanyProfile: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'saveEntProfile',
    type: requestType.Post
  },
  /**
   * 获取
   */
  getCompanyProfile: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getEntProfile',
    type: requestType.Get
  },

}
