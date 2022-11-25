/*
*systemweb 企业管理者登录 service
*/
import {systemwebService,commonService} from '~/server/controller'
import {NetService} from '~/utils/net.service'
import {Inject, Debounce} from "~/core/decorator";
import {Service} from "~/server/service"

import md5 from "md5"
import commonValidateController from "~/server/controller/common-controller/validate-code.controller";

export class entLoginService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 登录
   */
  @Debounce()
  doLoginRsa({keyScript}) {
    return this.netService.send({
      server: systemwebService.entLoginController.doLogin,
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
      server: commonService.commonValidateController.captchaApi,
      data: {},
      loading: true
    })
  }


  /**
   * 登录
   */
  @Debounce()
  doLogin({userName, password, companyCode, code, key}) {
    return this.netService.send({
      server: systemwebService.entLoginController.doLogin,
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
      server: systemwebService.entLoginController.index,
      data: {},
      loading: true
    })
  }

  /**
   * 修改密码
   */
  updateUserPwd({oldPwd, newPwd}) {
    return this.netService.send({
      server: systemwebService.entLoginController.updateUserPwd,
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
      server: systemwebService.entLoginController.logout,
      data: {},
      loading: true
    })
  }
}

