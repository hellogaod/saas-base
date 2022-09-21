import Vue from 'vue'
import { DictService } from '~/utils/dict.services'

export default {
  install() {
    Vue.prototype.$dict = DictService
  }
}
