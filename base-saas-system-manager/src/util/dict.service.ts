import store from '~/store'

const { state } = store

export class DictService {
  /**
   * 获取字典数据
   * @param codes
   */
  static getDictData(...codes) {
    let results: Array<string> = []

    // 获取字典项
    codes.forEach(code => {

      let items = state.dictData
        .filter(x => x.typeCode === code)
        .map(x => ({
          value: x.id,
          label: x.name
        }))

      results.push(...items)
    })

    return results.length === 1 ? results[0] : results
  }

  // /**
  //  * 过滤器：获取字典名称
  //  * @param value 字典键值
  //  */
  // static getDictName(value) {
  //   let result = ''
  //   if (value) {
  //     let data = state.dictData.find(x => value === x.id)
  //     if (data) {
  //       result = data.name
  //     }
  //   }
  //   return result;
  // }
}
