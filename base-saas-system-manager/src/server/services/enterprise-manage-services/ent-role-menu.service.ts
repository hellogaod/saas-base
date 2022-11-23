/*
*systemweb 角色权限管理 service
*/
import {systemwebService} from '~/server/controller'
import {NetService} from '~/utils/net.service'
import {Inject, Debounce} from "~/core/decorator";
import {Service} from "~/server/service"

export class entRoleMenuService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 根据角色编号查询已有的菜单编号
   */
  @Debounce()
  getMenuByRoleId({roleId, sysCode}) {
    return this.netService.send({
      server: systemwebService.entRoleMenuController.getMenuByRoleId,
      data: {
        roleId: roleId,
        sysCode: sysCode
      },
      loading: true
    })
  }

  /**
   * 添加角色权限
   */
  @Debounce()
  addRoleMenu(data) {
    return this.netService.send({
      server: systemwebService.entRoleMenuController.addRoleMenu,
      data: data,
      loading: true
    })
  }

  /**
   * 获取菜单-用户管理（角色分配）
   */
  @Debounce()
  getMenuListByRoleId({roleId}) {
    return this.netService.send({
      server: systemwebService.entRoleMenuController.getMenuListByRoleId,
      data: {roleId},
      loading: true
    })
  }
}

