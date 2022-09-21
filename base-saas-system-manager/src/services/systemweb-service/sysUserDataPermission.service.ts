/*
*systemweb user-data-permission service
*/ 
import { systemwebService } from '~/config/server'
import { NetService } from '~/utils/net.service'
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service"

export class sysUserPermissionService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 添加数据权限
   */
  @Debounce()
  addDataPermission(data) {
    return this.netService.send({
      server: systemwebService.syswebUserDataPermissionController.addDataPermission,
      data: data,
      loading: true
    })
  }
  /**
   * 获取数据权限
   */
  @Debounce()
  getUserDataPermisson() {
    return this.netService.send({
      server: systemwebService.syswebUserDataPermissionController.getUserDataPermisson,
      data: {},
      loading: true
    })
  }
}

