/*
*manage 系统模块管理 service
*/ 
import { managerService } from '~/config/server'
import { NetService } from '~/utils/net.service'
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service"

export class ModuleService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 查询已启用的模块列表
   */
  @Debounce()
  getEffectiveModule() {
    return this.netService.send({
      server: managerService.moduleController.getEffectiveModule,
      data: {},
      loading: true
    })
  }
  /*根据主键查询模块信息
   */
  getModuleInfo(sysCode) {
    return this.netService.send({
      server: managerService.moduleController.getModuleInfo,
      data: {sysCode},
      loading: true
    })
  }
  /*查询模块列表
   */
  getModuleList(data,page) {
    return this.netService.send({
      server: managerService.moduleController.getModuleList,
      data: data,
      page: page,
      loading: true
    })
  }
  /*根据模块主键查询模块相关的菜单
   */
  getModuleMenuListBySysCode({ }) {
    return this.netService.send({
      server: managerService.moduleController.getModuleMenuListBySysCode,
      data: {},
      loading: true
    })
  }
  /*保存系统模块
   */
  saveSysModule(data) {
    return this.netService.send({
      server: managerService.moduleController.saveSysModule,
      data:data,
      loading: true
    })
  }
  /*修改系统状态
   */
  updateStatus(data) {
    return this.netService.send({
      server: managerService.moduleController.updateStatus,
      data:data,
      loading: true
    })
  }
  /*修改系统模块
   */
  updateSysModule(data) {
    return this.netService.send({
      server: managerService.moduleController.updateSysModule,
      data: data,
      loading: true
    })
  }
}

