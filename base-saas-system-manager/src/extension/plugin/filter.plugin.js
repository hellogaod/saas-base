import Vue from 'vue';
import { FilterService } from '~/utils/filter.service';
export default {
    install: function () {
        Vue.prototype.$filter = FilterService;
    }
};
