import Vue from 'vue'
import { HelperService } from '~/utils/helper.services'

export default {
  install() {
    Vue.prototype.$helper = HelperService
  }
}
