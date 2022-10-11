import Vue from 'vue'
import { DateTransferUtil } from '~/utils/dateTransfer.util'

export default {
  install() {
    Vue.prototype.$dateutils = DateTransferUtil
  }
}
