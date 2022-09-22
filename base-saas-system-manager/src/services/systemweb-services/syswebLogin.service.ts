/*
*systemweb 系统登录 service
*/
import { systemwebService } from '~/config/server'
import { NetService } from '~/utils/net.service'
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service"

import md5 from "md5"
export class webLoginService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 登录
   */
  @Debounce()
  doLoginRsa({ keyScript }) {
    return this.netService.send({
      server: systemwebService.syswebLoginController.doLogin,
      data: {
        keyScript
      },
      loading: true
    })
  }


  /**
 * @description 加载图形验证码
 */
  captchaApi() {
    return this.netService.send({
      server: systemwebService.syswebValidateController.captchaApi,
      data: {},
      loading: true
    })
  }


  /**
   * 登录
   */
  @Debounce()
  doLogin({ userName, password, companyCode,code,key }) {
    return this.netService.send({
      server: systemwebService.syswebLoginController.doLogin,
      data: {
        userName,
        password: md5(password),
        companyCode,
        code,
        key
      },
      loading: true
    })
  }
  /**
   * 获取菜单和模块
   */
  index() {
    return this.netService.send({
      server: systemwebService.syswebLoginController.index,
      data: {},
      loading: true
    })
  }
  /**
   * 修改密码
   */
  updateUserPwd({oldPwd,newPwd}) {
    return this.netService.send({
      server: systemwebService.syswebLoginController.updateUserPwd,
      data: {
        oldPwd: oldPwd,
        newPwd: newPwd
      },
      loading: true
    })
  }
  /**
   * 退出
   */
  logout() {
    return this.netService.send({
      server: systemwebService.syswebLoginController.logout,
      data: {},
      loading: true
    })
  }
}

