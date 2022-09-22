import Vue from 'vue';
import { ReminderService } from '~/utils/reminder.service';
export default {
    install: function () {
        Vue.prototype.$reminder = ReminderService;
    }
};
