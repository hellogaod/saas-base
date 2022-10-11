import Vue from 'vue'
import Vuex from 'vuex'
import state from './state'
import mutations from './mutations'
import actions from './actions'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

const filterList = ['ready', "layout"]

const store = new Vuex.Store({
  state,
  mutations,
  actions,
  plugins: [
    // 持久化存储插件
    createPersistedState({
      key: "vuex",
      reducer: (state, paths) => {
        return Object.assign({}, state, { ready: false, layout: "default" })
      },
      storage: sessionStorage,
      filter: ({ type, payload }) => {
        return !filterList.includes(type)
      }
    })

  ]
})

export default store
