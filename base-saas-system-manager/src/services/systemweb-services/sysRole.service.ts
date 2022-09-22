/*
*systemweb 角色管理 service
*/ 
import { systemwebService } from '~/config/server'
import { NetService } from '~/utils/net.service'
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service"

export class sysRoleService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 添加角色
   */
  @Debounce()
  addRole(data) {
    return this.netService.send({
      server: systemwebService.syswebRoleController.addRole,
      data: data,
      loading: true
    })
  }
  /**
   * 角色分配，获取角色
   */
  @Debounce()
  getRole(userId) {
    return this.netService.send({
      server: systemwebService.syswebRoleController.getRole,
      data: {userId},
      loading: true
    })
  }
  /**
   * 查询单个角色
   */
  @Debounce()
  getRoleById(roleId) {
    return this.netService.send({
      server: systemwebService.syswebRoleController.getRoleById,
      data: {roleId},
      loading: true
    })
  }
  /**
   * 获取角色列表
   */
  @Debounce()
  getRoleList(data,page) {
    return this.netService.send({
      server: systemwebService.syswebRoleController.getRoleList,
      data: data,
      page: page,
      loading: true
    })
  }
  /**
   * 修改角色信息
   */
  @Debounce()
  updateRole(data) {
    return this.netService.send({
      server: systemwebService.syswebRoleController.updateRole,
      data: data,
      loading: true
    })
  }
  /**
   * 角色启用停用
   */
  @Debounce()
  updateStatus({ status,roleId }) {
    return this.netService.send({
      server: systemwebService.syswebRoleController.updateStatus,
      data: {
        status:status,
        roleId:roleId
      },
      loading: true
    })
  }
}

