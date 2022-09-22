import Vue from 'vue';
import { DictService } from '~/utils/dict.service';
export default {
    install: function () {
        Vue.prototype.$dict = DictService;
    }
};
