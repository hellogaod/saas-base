import store from '~/store'
import * as enumData from "~/config/enum.config"
import appConfig from "~/config/app.config"

export class FilterService {
  /**
   * 转换枚举数据
   * @param value
   * @param key
   */
  static enumConvert(value: number | string, key) {
    // 排除空字典或者空key
    if (!enumData || !key || !enumData[key]) {
      return ''
    }

    let data = enumData[key] instanceof Array ? enumData[key] : Object.values(enumData[key])

    let target = data.find(x => x.value === value)

    return target ? target.name : ""
  }
  /**
   * 转换字典数据
   * @param id
   */
  static dictConvert(id) {
    let dictData = store.state.dictData
    let target

    if (dictData) {
      target = dictData.find(x => x.id === id)
    }

    return target ? target.name : ""
  }
  /**
   * 日期范围转换
   * @param dateRange
   * @param fmt
   */
  static dateRanageFormat(dateRange, fmt = "yyyy-MM-dd hh:mm:ss") {
    let target = {
      start: '',
      end: ''
    }
    // 检测非法输入
    if (!dateRange || dateRange.length === 0 || !(dateRange instanceof Array)) {
      return target
    }

    target.start = FilterService.dateFormat(dateRange[0], fmt)
    target.end = FilterService.dateFormat(dateRange[1], fmt)
    return target
  }

  /**
   * 日期时间格式化
   * @param date 
   * @param fmt 默认值为长日期格式
   */
  static dateTimeFormat(date, fmt = "yyyy-MM-dd hh:mm:ss"): string {
    return FilterService.dateFormat(date, fmt)
  }

  /**
   * 日期格式化
   * @param date
   * @param fmt 默认值为短日期格式
   */
  static dateFormat(date, fmt = "yyyy-MM-dd"): string {
    // 空数据处理
    if (date === null || date === undefined || date === '') {
      return ''
    }

    // 如果是时间戳则转化为时间
    if (typeof date === 'number') {
      date = new Date(date)
    }

    let o = {
      'M+': date.getMonth() + 1, // 月份
      'd+': date.getDate(), // 日
      'h+': date.getHours(), // 小时
      'm+': date.getMinutes(), // 分
      's+': date.getSeconds(), // 秒
      'q+': Math.floor((date.getMonth() + 3) / 3), // 季度
      'S': date.getMilliseconds() // 毫秒
    }

    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))

    for (var k in o) {
      if (new RegExp('(' + k + ')').test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
    }
    return fmt
  }
  /**
   * 千分位转换
   * @param number
   */
  static toThousands(number, fixed: number = 2): String {
    let num: any = ''
    if (number === null || number === '') {
      num = number
    } else {
      num = Number(number).toFixed(fixed)
      if (isNaN(num) || num === '' || num === undefined || num === null) {
        return ''
      }
      num = num + ''
      if (/^.*\..*$/.test(num)) {
        let pointIndex = num.lastIndexOf('.')
        let intPart = num.substring(0, pointIndex)
        let pointPart = num.substring(pointIndex + 1, num.length)
        intPart = intPart + ''
        let re = /(-?\d+)(\d{3})/
        while (re.test(intPart)) {
          intPart = intPart.replace(re, '$1,$2')
        }
        num = intPart + '.' + pointPart
      } else {
        num = num + ''
        let re = /(-?\d+)(\d{3})/
        while (re.test(num)) {
          num = num.replace(re, '$1,$2')
        }
      }
    }
    return num
  }
  /**
   * 手机号脱敏显示转换器
   */
  static encryptPhone(value) {
    if (!appConfig.encryptSwitch) false
    if (!value || value === '') {
      return ''
    }
    return value.substr(0, 3) + '****' + value.substr(7)
  }
  /**
   * 身份证脱敏显示转换器
   */
  static encryptIDCardFour(value) {
    if (!appConfig.encryptSwitch) false
    if (!value || value === '') {
      return ''
    }
    // return value.substr(0, 3).padEnd(value.length - 4, '*') + value.substr(-4)
    return value.substr(0, 3) + '****' + value.substr(-4)
  }
  /**
   * 长度过长省略显示
   */
  static ellipsis(value, length) {
    if (!value) {
      return ''
    }
    let data = String(value).trim()
    if (data.length <= length) {
      return data
    } else {
      return data.substr(0, length) + '...'
    }
  }
  /**
   * 小数转换为百分比
   * @param point 要转换的小数
   */
  static toPercent(point?: number) {
    let str = '--'
    if (point !== undefined) {
      point *= 100
      str = (point > 0 ? point.toFixed(2) : 0) + '%'
    }
    return str
  }
}
