/*
*systemweb 日志管理 service
*/ 
import { systemwebService } from '~/config/server'
import { NetService } from '~/utils/net.service'
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service"

export class WeblogService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 系统日志列表
   */
  @Debounce()
  getLogList(data,page) {
    return this.netService.send({
      server: systemwebService.syswebLogController.getLogList,
      data:data,
      page:page,
      loading: true
    })
  } 
    /**
   * 系统日志查看
   */
  @Debounce()
  getErrorLogById(id) {
    return this.netService.send({
      server: systemwebService.syswebLogController.getErrorLogById,
      data:{id},
      loading: true
    })
  }
}

