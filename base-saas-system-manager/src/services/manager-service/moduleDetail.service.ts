/*
*manager 系统模块菜单管理 service
*/ 
import { managerService } from '~/config/server'
import { NetService } from '~/utils/net.service'
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service"

export class ModuledetailService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 查询已启用的模块列表
   */
  @Debounce()
  getAllMenuTree(sysCode) {
    return this.netService.send({
      server: managerService.moduleDetailController.getAllMenuTree,
      data: {sysCode},
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
  //获取一级菜单
  getOneMenu(flag,data){
    return this.netService.send({
      server: managerService.moduleDetailController.getOneMenu,
      data: {
        flag: flag,
        sysCode: data
      },
      loading: true
    })
  }
  //添加菜单
  addMenu(data) {
    return this.netService.send({
      server: managerService.moduleDetailController.addMenu,
      data:data,
      loading: true
    })
  }
  //更改状态
 updateMenuStatus(data) {
  return this.netService.send({
    server: managerService.moduleDetailController.updateMenuStatus,
    data:data,
    loading: true
  })
}
//编辑获取信息
getMenuById(id) {
  return this.netService.send({
    server: managerService.moduleDetailController.getMenuById,
    data:{id},
    loading: true
  })
}
//编辑保存信息
editMenu(data) {
  return this.netService.send({
    server: managerService.moduleDetailController.editMenu,
    data:data,
    loading: true
  })
}
checkMenuChildStatus(data) {
  return this.netService.send({
    server: managerService.moduleDetailController.checkMenuChildStatus,
    data:data,
    loading: true
  })
}

}
 

