import Vue from 'vue'
import { DictService } from '~/utils/dict.service'

export default {
  install() {
    Vue.prototype.$dict = DictService
  }
}
