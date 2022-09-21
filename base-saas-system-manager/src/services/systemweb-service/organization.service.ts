/*
*systemweb organization机构管理 service
*/ 
import { systemwebService } from '~/config/server'
import { NetService } from '~/utils/net.service'
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service"

export class webOrganizationService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 查询table数据列表
   */
  @Debounce()
  getOrgList() {
    return this.netService.send({
      server: systemwebService.syswebOrganizationController.getOrgList,
      data: {},
      loading: true
    })
  }
  /**
   * 用户管理数据权限中获取组织架构的菜单树
   */
  @Debounce()
  getOrgByOrgId(orgId,userId,menuId) {
    return this.netService.send({
      server: systemwebService.syswebOrganizationController.getOrgByOrgId,
      data: {orgId,userId,menuId},
      loading: true
    })
  }

  /**
   * 添加
   */
  @Debounce()
  saveOrg(data) {
    return this.netService.send({
      server: systemwebService.syswebOrganizationController.saveOrg,
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
      server: systemwebService.syswebOrganizationController.getOrgById,
      data: { orgId },
      loading: true
    })
  }

   /**
   * 修改
   */
  @Debounce()
  updateOrg(data) {
    return this.netService.send({
      server: systemwebService.syswebOrganizationController.updateOrg,
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
      server: systemwebService.syswebOrganizationController.updateOrgStatus,
      data: data,
      loading: true
    })
  }
}

    