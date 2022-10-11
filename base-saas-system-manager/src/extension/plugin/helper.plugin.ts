import Vue from 'vue'
import { HelperUtil } from '~/utils/helper.util'
//用于计算表格，每个列的宽度
export default {
  install() {
    Vue.prototype.$helper = HelperUtil
  }
}
