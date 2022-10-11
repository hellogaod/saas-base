
export class DateTransferUtil {

  /**
   * 日期时间格式化
   * @param date 
   * @param fmt 默认值为长日期格式
   */
  static dateTimeFormat(date, fmt = "yyyy-MM-dd hh:mm:ss"): string {
    return DateTransferUtil.dateFormat(date, fmt)
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

}
