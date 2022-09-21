/*
*manager 系统参数 service
*/ 
import { managerService } from '~/config/server'
import { NetService } from '~/utils/net.service'
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service"

export class OtherService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 查询企业三方关联列表
   */
  @Debounce()
  getCompanyConfigList(companyCode) {
    return this.netService.send({
      server: managerService.otherController.getCompanyConfigList,
      data: {companyCode},
      loading: true
    })
  }
  /**
   * 查询参数配置
   */
  getOtherConfigInfo(id) {
    return this.netService.send({
      server: managerService.otherController.getOtherConfigInfo,
      data: {id},
      loading: true
    })
  }
  /**
   * 查询启用的三方列表
   */
  getOtherConfigList(companyCode) {
    return this.netService.send({
      server: managerService.otherController.getOtherConfigList,
      data: {companyCode},
      loading: true
    })
  }
   /**
   * 查询配置列表
   */
  getOtherList(data,page) {
    return this.netService.send({
      server: managerService.otherController.getOtherList,
      data: data,
      page:page,
      loading: true
    })
  }
  /**
   * 保存参数配置
   */
  saveOtherConfig(data) {
    return this.netService.send({
      server: managerService.otherController.saveOtherConfig,
      data: data,
      loading: true
    })
  }
  /**
   * 修改配置
   */
  updateOther({ }) {
    return this.netService.send({
      server: managerService.otherController.updateOther,
      data: {},
      loading: true
    })
  }
  /**
   * 修改参数配置
   */
  updateOtherConfig(data) {
    return this.netService.send({
      server: managerService.otherController.updateOtherConfig,
      data: data,
      loading: true
    })
  }
  /**
   * 修改状态
   */
  updateStatus({ status, id }) {
    return this.netService.send({
      server: managerService.otherController.updateStatus,
      data: {
        status:status,
        id:id
      },
      loading: true
    })
  }
}

