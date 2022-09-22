
// const createReminderMessageService = () => import('~/services/reminder-service/reminder-message.service')

export default {
  namespaced: true,
  state: {
    currentTab: '', // 当前选中的tab
    currentTabs: [], // 当前显示的tabs
    unReadCount: 0,
    taskList: [],
    messageBoxVisible: false
  },
  mutations: {
    updateMessageBoxVisible(state, visible) {
      state.messageBoxVisible = visible
    },
    updateTaskList(state, list) {
      state.taskList = list
    },
    updateCurrentTab(state, tab) {
      state.currentTab = tab
    },
    updateCurrentTabs(state, tabs) {
      state.currentTabs = tabs
    },
    /**
     * 更新未读消息数
     * @param state
     * @param count
     */
    updateUnReadCount(state, count) {
      state.unReadCount = count
    }
  },
  actions: {
    /**
     * 查询未读消息树
     */
    async updateUnReadCount({ commit, rootState }) {
      // let { ReminderMessageService } = await createReminderMessageService()
      // if (rootState.userToken) {
      //   ReminderMessageService
      //     .getInstance()
      //     .getUnReadCount()
      //     .subscribe(count => {
      //         commit('updateUnReadCount', count)
      //       }
      //     );
      // }

    },
    updateTabs({ state, commit, rootState }, parentId) {
      // 获取tabs标签页
      let tabs = rootState.menuResource
        .filter((x: any) => x.type === 18)
        .filter((x: any) => x.parentId === parentId)
        .sort((x: any, y: any) => x.sort - y.sort);
      // 更新tabs标签页
      commit('updateCurrentTabs', tabs)
    }
  }
}
