const sortType = {
    "ascending":"asc",
    "descending":"desc"
}

export class SortService {
  private sort = {}

  constructor() {

  }

  /**
   * 转换排序对象为字符串
   */
  static stringify(value):string{
    if(typeof value !== "object"){
      return ""
    }

    if(value instanceof SortService){
      value = value.sort
    }

    return Object.entries(value).map(([k, v]) => `sort=${k},${v}`).join('&')
  }

  /**
   * 更新分页配置信息
   * @param param
   */
  update(key, value) {
    if(key == null||value == null){
      return this.reset()
    }

    this.sort = {
      [key]:sortType[value]||value
    }
  }

  /**
   * 重置分页数据
   */
  reset(){
    this.sort = {}
  }
}
