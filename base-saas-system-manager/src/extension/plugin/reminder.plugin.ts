import Vue from 'vue'
import { ReminderService } from '~/utils/reminder.service'

export default {
  install() {
    Vue.prototype.$reminder = ReminderService
  }
}
