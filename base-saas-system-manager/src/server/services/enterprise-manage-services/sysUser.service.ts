/*
*systemweb 用户管理 service
*/
import {systemwebService} from '~/server/controller'
import {NetService} from '~/utils/net.service'
import {Inject, Debounce} from "~/core/decorator";
import {Service} from "~/server/service"

export class sysUserService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 添加用户
   */
  @Debounce()
  addUser(data) {
    return this.netService.send({
      server: systemwebService.entUserController.addUser,
      data: data,
      loading: true
    })
  }

  /**
   * 查询单个用户
   */
  @Debounce()
  getUserById(userId) {
    return this.netService.send({
      server: systemwebService.entUserController.getUserById,
      data: {userId},
      loading: true
    })
  }

  /**
   * 获取用户列表
   */
  @Debounce()
  getUserList(data, page) {
    return this.netService.send({
      server: systemwebService.entUserController.getUserList,
      data: data,
      page: page,
      loading: true
    })
  }

  /**
   * 重置密码
   */
  @Debounce()
  resetPassword(userId) {
    return this.netService.send({
      server: systemwebService.entUserController.resetPassword,
      data: {userId},
      loading: true
    })
  }

  /**
   * 修改用户信息
   */
  @Debounce()
  updateUser(data) {
    return this.netService.send({
      server: systemwebService.entUserController.updateUser,
      data: data,
      loading: true
    })
  }


  /**
   * 用户启用停用
   */
  @Debounce()
  updateStatus({status, userId}) {
    return this.netService.send({
      server: systemwebService.entUserController.updateStatus,
      data: {
        status: status,
        userId: userId
      },
      loading: true
    })
  }
}

