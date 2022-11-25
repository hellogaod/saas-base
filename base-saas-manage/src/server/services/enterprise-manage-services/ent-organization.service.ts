/*
*systemweb organization机构管理 service
*/
import {systemwebService} from '~/server/controller'
import {NetService} from '~/utils/net.service'
import {Inject, Debounce} from "~/core/decorator";
import {Service} from "~/server/service"

export class webOrganizationService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 查询table数据列表
   */
  @Debounce()
  getOrgList() {
    return this.netService.send({
      server: systemwebService.entOrganizationController.getOrgList,
      data: {},
      loading: true
    })
  }

  /**
   * 添加
   */
  @Debounce()
  saveOrg(data) {
    return this.netService.send({
      server: systemwebService.entOrganizationController.saveOrg,
      data: data,
      loading: true
    })
  }

  /**
   * 修改弹框 根据orgId回显数据
   */
  @Debounce()
  getOrgById(orgId) {
    return this.netService.send({
      server: systemwebService.entOrganizationController.getOrgById,
      data: {orgId},
      loading: true
    })
  }

  /**
   * 修改
   */
  @Debounce()
  updateOrg(data) {
    return this.netService.send({
      server: systemwebService.entOrganizationController.updateOrg,
      data: data,
      loading: true
    })
  }

  /**
   * 启用/停用
   */
  @Debounce()
  updateOrgStatus(data) {
    return this.netService.send({
      server: systemwebService.entOrganizationController.updateOrgStatus,
      data: data,
      loading: true
    })
  }
}

