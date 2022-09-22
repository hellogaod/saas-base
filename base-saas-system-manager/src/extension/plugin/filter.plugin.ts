import Vue from 'vue'
import { FilterService } from '~/utils/filter.service'

export default {
  install() {
    Vue.prototype.$filter = FilterService
  }
}
