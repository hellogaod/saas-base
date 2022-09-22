import Vue from 'vue';
import { ValidatorService } from '~/utils/validator.service';
export default {
    install: function () {
        Vue.prototype.$validator = ValidatorService;
    }
};
