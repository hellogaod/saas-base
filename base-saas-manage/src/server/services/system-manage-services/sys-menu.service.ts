/*
*manage 系统模块菜单管理 service
*/
import {managerService} from '~/server/controller'
import {NetService} from '~/utils/net.service'
import {Inject, Debounce} from "~/core/decorator";
import {Service} from "~/server/service"

export class SysMenuService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 查询已启用的模块列表
   */
  @Debounce()
  getAllMenuTree(moduleId, parentId) {
    return this.netService.send({
      server: managerService.moduleDetailController.getAllMenuTree,
      data: {moduleId: moduleId, parentId: parentId},
      loading: true
    })
  }

  @Debounce()
  getChildrenMenuByParentId(userId, parentId) {
    return this.netService.send({
      server: managerService.moduleDetailController.getChildrenMenuByParentId,
      data: {userId: userId, parentId: parentId},
      loading: true
    })
  }

  //表格数据
  getAllMenuDetailList(data) {
    return this.netService.send({
      server: managerService.moduleDetailController.getAllMenuDetailList,
      data: data,
      loading: true
    })
  }

  //添加菜单
  addMenu(data) {
    return this.netService.send({
      server: managerService.moduleDetailController.addMenu,
      data: data,
      loading: true
    })
  }

  //更改状态
  updateMenuStatus(menuId,status) {
    return this.netService.send({
      server: managerService.moduleDetailController.updateMenuStatus,
      data: {menuId,status},
      loading: true
    })
  }

//编辑获取信息
  getMenuById(menuId) {
    return this.netService.send({
      server: managerService.moduleDetailController.getMenuById,
      data: {menuId},
      loading: true
    })
  }

//编辑保存信息
  editMenu(data) {
    return this.netService.send({
      server: managerService.moduleDetailController.editMenu,
      data: data,
      loading: true
    })
  }

}


