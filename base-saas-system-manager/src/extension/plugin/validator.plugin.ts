import Vue from 'vue'
import { ValidatorService } from '~/utils/validator.service'
export default {
  install() {
    Vue.prototype.$validator = ValidatorService
  }
}
