/*
*manage 常用service-图形验证码 service
*/
import {commonService} from '~/server/controller'
import {NetService} from '~/utils/net.service'
import {Inject, Debounce} from "~/core/decorator";
import {Service} from "~/server/service"

export class ComValidateService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * @description 加载图形验证码
   */
  captchaApi() {
    return this.netService.send({
      server: commonService.commonValidateController.captchaApi,
      data: {},
      loading: true
    })
  }

}

