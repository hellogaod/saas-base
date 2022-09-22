import Vue from 'vue';
import { HelperService } from '~/utils/helper.service';
export default {
    install: function () {
        Vue.prototype.$helper = HelperService;
    }
};
