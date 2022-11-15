/*
*systemweb dict字典管理 service
*/
import {systemwebService} from '~/server/controller'
import {NetService} from '~/utils/net.service'
import {Inject, Debounce} from "~/core/decorator";
import {Service} from "~/server/service"

export class webDictService extends Service {

  @Inject(NetService)
  private netService: NetService

  /**
   * 查询大类数据列表
   */
  @Debounce()
  getDictItemList(data, page) {
    return this.netService.send({
      server: systemwebService.entDictController.getDictItemList,
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
      server: systemwebService.entDictController.getDictDetailById,
      data: {id},
      loading: true
    })
  }

  /**
   * 查询明细数据列表
   */
  @Debounce()
  getDictDetailList(data, page) {
    return this.netService.send({
      server: systemwebService.entDictController.getDictDetailList,
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
      server: systemwebService.entDictController.getDictItemById,
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
      server: systemwebService.entDictController.saveDictDetail,
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
      server: systemwebService.entDictController.saveDictItem,
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
      server: systemwebService.entDictController.updateDictDetail,
      data: data,
      loading: true
    })
  }

  /**
   * 修改字典明细状态
   */
  @Debounce()
  updateDictDetailStatus({status, id}) {
    return this.netService.send({
      server: systemwebService.entDictController.updateDictDetailStatus,
      data: {
        status: status,
        id: id
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
      server: systemwebService.entDictController.updateDictItem,
      data: data,
      loading: true
    })
  }

  /**
   * 修改字典大类状态
   */
  @Debounce()
  updateDictItemStatus({status, id}) {
    return this.netService.send({
      server: systemwebService.entDictController.updateDictItemStatus,
      data: {
        status: status,
        id: id
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
      server: systemwebService.entDictController.getDictDetailByItemCode,
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
      server: systemwebService.entDictController.getDicDetailByItemList,
      data: data,
      loading: true
    })
  }
}


