import Vue from 'vue';
import Vuex from 'vuex';
import state from './state';
import mutations from './mutations';
// import actions from './actions'
import getters from './getters';
import createPersistedState from 'vuex-persistedstate';
// 引入模块
// import workspace from './modules/workspace.store'
// import message from './modules/message.store'
// import inrushCase from './modules/inrush-case.store'
// import taticManage from './modules/tatic-manage.store'
// import accManage from './modules/acc-manage.store'
// import outSourceManage from './modules/outsource-manage.store'
import systemManage from './modules/system-manage.store';
Vue.use(Vuex);
var filterList = ['ready', "layout"];
var store = new Vuex.Store({
    // 子模块
    modules: {
        // "workspace": workspace,
        // "message": message,
        // "inrush-case": inrushCase,
        // "tatic-manage": taticManage,
        // "acc-manage": accManage,
        // "outsource-manage": outSourceManage,
        "system-manage": systemManage,
    },
    state: state,
    getters: getters,
    mutations: mutations,
    // actions,
    plugins: [
        // 持久化存储插件
        createPersistedState({
            key: "vuex",
            reducer: function (state, paths) {
                return Object.assign({}, state, { ready: false, layout: "default" });
            },
            // storage: localStorage,
            storage: sessionStorage,
            filter: function (_a) {
                var type = _a.type, payload = _a.payload;
                return !filterList.includes(type);
            }
        })
    ]
});
export default store;
