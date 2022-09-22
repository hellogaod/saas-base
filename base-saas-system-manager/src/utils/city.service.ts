const cityData = require('~/assets/json/city.json');

export class CityService {

  /**
   * 获取城市信息
   * @param level
   * @param id
   */
  static getCityData({ level = 3, id = 1 } = {}) {
    let fun = (id, currentLevel = 1) => {
      let items = new Array()

      cityData
        .filter(x => x.pid === id)
        .forEach(x => {
          // 生成城市对象
          let item: any = {
            value: x.id,
            label: x.name
          }

          // 检测获取级别
          if (currentLevel < level) {
            let children = fun(x.id, currentLevel + 1)
            if (children && children.length > 0) {
              item.children = children
            }
          }

          items.push(item)
        })

      return items
    }

    return fun(id)
  }

  /**
   * 获取城市节点父元素
   * @param id
   */
  static getCityParent(id) {
    let result: Array<any> = []

    // 向根节点遍历
    let fun = (itemId) => {
      let item: any = cityData.find(x => x.id === itemId)
      if (item && item.pid !== 1) {
        result.unshift(item.pid)
        fun(item.pid)
      }
    }

    fun(id)

    return result
  }

  /**
   * 获取城市名称
   * @param id
   */
  static getCityName(...ids) {
    let results: Array<string> = []
    if(!ids[0]) {
      return ''
    }
    ids.forEach(id => {
      let item = cityData.find(c => c.id === id)
      results.push(item.name)
    })

    return results.length === 1 ? results[0] : results
  }
}
