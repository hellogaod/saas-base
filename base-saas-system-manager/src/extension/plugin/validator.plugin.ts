import Vue from 'vue'
import { ValidatorService } from '~/utils/validator.services'
export default {
  install() {
    Vue.prototype.$validator = ValidatorService
  }
}
