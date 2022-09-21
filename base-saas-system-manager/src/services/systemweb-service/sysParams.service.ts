/*
*systemweb systemParams系统参数配置 service
*/ 
import { systemwebService } from '~/config/server'
import { NetService } from '~/utils/net.service'
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service"

export class webSystemParamsService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 查询数据
   */
  @Debounce()
  getCompnaySysPara() {
    return this.netService.send({
      server: systemwebService.syswebParamsController.getCompnaySysPara,
      data: {},
      loading: true
    })
  }

  /**
   * 保存
   */
  @Debounce()
  saveSysPara(data) {
    return this.netService.send({
      server: systemwebService.syswebParamsController.saveSysPara,
      data: data,
      loading: true
    })
  }
}

    