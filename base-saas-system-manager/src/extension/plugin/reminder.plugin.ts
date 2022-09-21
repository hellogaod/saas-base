import Vue from 'vue'
import { ReminderService } from '~/utils/reminder.services'

export default {
  install() {
    Vue.prototype.$reminder = ReminderService
  }
}
