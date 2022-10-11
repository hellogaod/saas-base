/*
*systemweb 系统菜单 service
*/
import {systemwebService} from '~/server/controller'
import {NetService} from '~/utils/net.service'
import {Inject, Debounce} from "~/core/decorator";
import {Service} from "~/server/service"

export class entMenuService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 获取菜单树的集合
   */
  @Debounce()
  getAllMenuTree(sysCode) {
    return this.netService.send({
      server: systemwebService.entEntmenuController.getAllMenuTree,
      data: {sysCode},
      loading: true
    })
  }

  /**
   * 获取企业模块对应下的菜单
   */
  @Debounce()
  getEntMenuByCode(entModuleCode) {
    return this.netService.send({
      server: systemwebService.entEntmenuController.getEntMenuByCode,
      data: {entModuleCode},
      loading: true
    })
  }

  /**
   * 获取菜单详情
   */
  @Debounce()
  getMenuDetail(data) {
    return this.netService.send({
      server: systemwebService.entEntmenuController.getMenuDetail,
      data: {
        parentId: data.parentId,
        entMenuId: data.entMenuId,
        menuId: data.menuId,
        userId: data.userId,
        sysCode: data.sysCode,
        roleId: data.roleId
      },
      loading: true
    })
  }
}

