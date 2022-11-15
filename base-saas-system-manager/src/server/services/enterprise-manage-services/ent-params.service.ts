/*
*systemweb systemParams系统参数配置 service
*/
import {systemwebService} from '~/server/controller'
import {NetService} from '~/utils/net.service'
import {Inject, Debounce} from "~/core/decorator";
import {Service} from "~/server/service"

export class webSystemParamsService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 查询数据
   */
  @Debounce()
  getCompnaySysPara() {
    return this.netService.send({
      server: systemwebService.entParamsController.getCompnaySysPara,
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
      server: systemwebService.entParamsController.saveSysPara,
      data: data,
      loading: true
    })
  }
}

