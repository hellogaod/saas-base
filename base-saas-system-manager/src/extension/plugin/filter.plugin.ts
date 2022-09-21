import Vue from 'vue'
import { FilterService } from '~/utils/filter.services'

export default {
  install() {
    Vue.prototype.$filter = FilterService
  }
}
