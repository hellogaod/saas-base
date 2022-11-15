/*
*systemweb 合同管理 service
*/
import {systemwebService} from '~/server/controller';
import {NetService} from '~/utils/net.service'
import {Inject, Debounce} from "~/core/decorator";
import {Service} from "~/server/service"

export class companyProfile extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 保存
   */
  @Debounce()
  saveCompanyProfile(content) {
    return this.netService.send({
      server: systemwebService.entCompanyProfileController.saveCompanyProfile,
      data: {content},
      loading: true
    })
  }

  /**
   * 获取
   */
  @Debounce()
  getCompanyProfile({}) {
    return this.netService.send({
      server: systemwebService.entCompanyProfileController.getCompanyProfile,
      data: {},
      loading: true
    })
  }


}

