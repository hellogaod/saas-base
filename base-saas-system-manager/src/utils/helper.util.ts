
const unit = 25

//用于计算表格，每个列的宽度
export class HelperUtil {
  static getColumnWidth(count) {
    return count * unit
  }
}
