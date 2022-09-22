/*
*systemweb 合同管理 service
*/ 
import { systemwebService } from '~/config/server';
import { NetService } from '~/utils/net.service'
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service"

export class companyProfile extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 保存
   */
  @Debounce()
  saveCompanyProfile(content) {
    return this.netService.send({
      server: systemwebService.syswebCompanyProfileController.saveCompanyProfile,
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
      server: systemwebService.syswebCompanyProfileController.getCompanyProfile,
      data: {},
      loading: true
    })
  }

  
}

