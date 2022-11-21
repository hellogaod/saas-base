/*
*manage 系统企业信息管理controller
*/
import {requestType, managerServiceName} from '~/server/enum.config'
/*
* 后台服务名称
*/
const CONTROLLER = 'sysEnterprise'
/*
* 前端服务名称
*/
export default {
  /**
   * 添加企业信息
   */
  addSysEnterprise: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'addSysEnterprise',
    type: requestType.Post
  },
  /**
   * 根据企业编码查询企业信息
   */
  getEnterpriseByCompanyCode: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getEnterpriseByCompanyCode',
    type: requestType.Get
  },
  /**
   * 获取企业列表
   */
  getSysEnterpriseList: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getSysEnterpriseList',
    type: requestType.Get
  },
  /**
   * 修改企业关联三方列表
   */
  updateCompanyOtherConf: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'updateCompanyOtherConf',
    type: requestType.Post
  },
   /**
   * 修改状态
   */
  updateStatus: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'updateStatus',
    type: requestType.Post
  },
   /**
   * 修改企业信息
   */
  updateSysEnterprise: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'updateSysEnterprise',
    type: requestType.Post
  },
  /**
   * 查询企业三方关联列表
   */
  getCompanyConfigList: {
    service: managerServiceName,
    controller: CONTROLLER,
    action: 'getCompanyConfigList',
    type: requestType.Get
  },
}
