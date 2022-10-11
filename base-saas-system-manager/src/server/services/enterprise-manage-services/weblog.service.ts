/*
*systemweb 日志管理 service
*/
import {systemwebService} from '~/server/controller'
import {NetService} from '~/utils/net.service'
import {Inject, Debounce} from "~/core/decorator";
import {Service} from "~/server/service"

export class WeblogService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 系统日志列表
   */
  @Debounce()
  getLogList(data, page) {
    return this.netService.send({
      server: systemwebService.entLogController.getLogList,
      data: data,
      page: page,
      loading: true
    })
  }

  /**
   * 系统日志查看
   */
  @Debounce()
  getErrorLogById(id) {
    return this.netService.send({
      server: systemwebService.entLogController.getErrorLogById,
      data: {id},
      loading: true
    })
  }
}

