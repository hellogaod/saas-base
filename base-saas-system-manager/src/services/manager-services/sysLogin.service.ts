/*
*manage 系统登录 service
*/ 
import { managerService } from '~/config/server'
import { NetService } from '~/utils/net.service'
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service"

import md5 from "md5"
export class LoginService extends Service {

  @Inject(NetService)
  private netService: NetService

    /**
 * @description 加载图形验证码
 */
     captchaApi() {
      return this.netService.send({
        server: managerService.sysValidateController.captchaApi,
        data: {},
        loading: true
      }) 
    }

  /**
   * 登录
   */
  @Debounce()
  login({ username, password,code,key }) {
    return this.netService.send({
      server: managerService.loginController.login,
      data: {
        username,
        password: md5(password),
        code,
        key
      },
      loading: true
    })
  }
  /*获取菜单列表
   */
  getMenu({ userId }) {
    return this.netService.send({
      server: managerService.loginController.getMenu,
      data: {
        userId
      },
      loading: true
    })
  }
  /**
   * 修改密码
   */
  updateUserPwd({oldPwd,newPwd}) {
    return this.netService.send({
      server: managerService.loginController.updateUserPwd,
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
      server: managerService.loginController.logout,
      data: {},
      loading: true
    })
  }
}

