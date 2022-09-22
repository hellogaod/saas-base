import Vue from 'vue'
import { HelperService } from '~/utils/helper.service'

export default {
  install() {
    Vue.prototype.$helper = HelperService
  }
}
