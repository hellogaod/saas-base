/*
*systemweb  service
*/ 
import { systemwebService } from '~/config/server'
import { NetService } from '~/utils/net.service'
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service"

export class webRichService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * /api/rich/downLoadFile
   */
  @Debounce()
  downLoadFile(data) {
    return this.netService.send({
      server: systemwebService.syswebRichController.downLoadFile,
      data: data,
      loading: true
    })
  }
}


