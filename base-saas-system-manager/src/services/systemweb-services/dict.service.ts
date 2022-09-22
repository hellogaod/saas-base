/*
*systemweb dict字典管理 service
*/ 
import { systemwebService } from '~/config/server'
import { NetService } from '~/utils/net.service'
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service"

export class webDictService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 查询大类数据列表
   */
  @Debounce()
  getDictItemList(data,page) {
    return this.netService.send({
      server: systemwebService.syswebDictController.getDictItemList,
      data: data,
      page: page,
      loading: true
    })
  }
  /**
   * 根据id查询字典明细
   */
  @Debounce()
  getDictDetailById(id) {
    return this.netService.send({
      server: systemwebService.syswebDictController.getDictDetailById,
      data: {id},
      loading: true
    })
  }
  /**
   * 查询明细数据列表
   */
  @Debounce()
  getDictDetailList(data,page) {
    return this.netService.send({
      server: systemwebService.syswebDictController.getDictDetailList,
      data: data,
      page: page,
      loading: true
    })
  }
  /**
   * 根据id获取字典大类信息
   */
  @Debounce()
  getDictItemById(id) {
    return this.netService.send({
      server: systemwebService.syswebDictController.getDictItemById,
      data: {id},
      loading: true
    })
  }
  /**
   * 保存字典明细
   */
  @Debounce()
  saveDictDetail(data) {
    return this.netService.send({
      server: systemwebService.syswebDictController.saveDictDetail,
      data: data,
      loading: true
    })
  }
  /**
   * 保存字典大类
   */
  @Debounce()
  saveDictItem(data) {
    return this.netService.send({
      server: systemwebService.syswebDictController.saveDictItem,
      data: data,
      loading: true
    })
  }
   /**
   * 修改字典明细
   */
  @Debounce()
  updateDictDetail(data) {
    return this.netService.send({
      server: systemwebService.syswebDictController.updateDictDetail,
      data: data,
      loading: true
    })
  }
  /**
   * 修改字典明细状态
   */
  @Debounce()
  updateDictDetailStatus({ status,id }) {
    return this.netService.send({
      server: systemwebService.syswebDictController.updateDictDetailStatus,
      data: {
        status:status,
        id:id
      },
      loading: true
    })
  }
  /**
   * 修改字典大类
   */
  @Debounce()
  updateDictItem(data) {
    return this.netService.send({
      server: systemwebService.syswebDictController.updateDictItem,
      data: data,
      loading: true
    })
  }
  /**
   * 修改字典大类状态
   */
  @Debounce()
  updateDictItemStatus({ status,id }) {
    return this.netService.send({
      server: systemwebService.syswebDictController.updateDictItemStatus,
      data: {
        status:status,
        id:id
      },
      loading: true
    })
  }

  /**
   * 根据字典code 查询
 /**
   * 根据字典编码查询字典明细
   */
  // @Debounce()
  getDictDetailByItemCode(data) {
    return this.netService.send({
      server: systemwebService.syswebDictController.getDictDetailByItemCode,
      data: data,
      loading: true
    })
  }

   /**
   * 根据字典编码查询字典明细（多个code’）
   */
  // @Debounce()
  getDicDetailByItemList(data) {
    return this.netService.send({
      server: systemwebService.syswebDictController.getDicDetailByItemList,
      data: data,
      loading: true
    })
  }
}


