/*
*manager 系统企业管理 service
*/ 
import { managerService } from '~/config/server'
import { NetService } from '~/utils/net.service'
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service"

export class EnterpiseService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 获取企业列表
   */
  @Debounce()
  getSysEnterpriseList(data, page) {
    return this.netService.send({
      server: managerService.enterpriseController.getSysEnterpriseList,
      data: data,
      page: page,
      loading: true
    })
  }
  /**
   * 添加企业信息
   */
  addSysEnterprise(data) {
    return this.netService.send({
      server: managerService.enterpriseController.addSysEnterprise,
      data: data,
      loading: true
    })
  }
  /**
   * 根据企业编码查询企业信息
   */
  getEnterpriseByCompanyCode(companyCode) {
    return this.netService.send({
      server: managerService.enterpriseController.getEnterpriseByCompanyCode,
      data: {companyCode},
      loading: true
    })
  }
  /**
   * 修改企业关联三方列表
   */
  updateCompanyOtherConf(data) {
    return this.netService.send({
      server: managerService.enterpriseController.updateCompanyOtherConf,
      data: {
        data: data
      },
      loading: true
    })
  }
   /**
   * 修改状态
   */
  updateStatus({status,companyCode}) {
    return this.netService.send({
      server: managerService.enterpriseController.updateStatus,
      data: {
        status: status,
        companyCode: companyCode
      },
      loading: true
    })
  }
  /**
   * 修改企业信息
   */
  updateSysEnterprise(data) {
    return this.netService.send({
      server: managerService.enterpriseController.updateSysEnterprise,
      data: data,
      loading: true
    })
  }
}

