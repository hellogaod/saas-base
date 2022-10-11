/*
*systemweb 用户权限管理 service
*/
import {systemwebService} from '~/server/controller'
import {NetService} from '~/utils/net.service'
import {Inject, Debounce} from "~/core/decorator";
import {Service} from "~/server/service"

export class sysUserPermissionService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 添加数据权限
   */
  @Debounce()
  addDataPermission(data) {
    return this.netService.send({
      server: systemwebService.entUserDataPermissionController.addDataPermission,
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
      server: systemwebService.entUserDataPermissionController.getUserDataPermisson,
      data: {},
      loading: true
    })
  }
}

