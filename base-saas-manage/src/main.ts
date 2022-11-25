// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import bootstrap from '~/core/bootstrap'
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import VueKindEditor from 'vue-kindeditor'
import 'kindeditor/kindeditor-all-min.js'
import 'kindeditor/themes/default/default.css'

async function startUp() {
  // 启动主体业务逻辑
  await bootstrap({store, router})

  // 生成提示信息
  Vue.config.productionTip = false
  Vue.use(VueQuillEditor)
  Vue.use(VueKindEditor)
  // 全局混入store
  Vue.mixin({
    store: store
  })

  /* eslint-disable no-new */
  new Vue({
    el: '#app',
    router,
    template: '<App/>',
    components: {App}
  })
}

// 打印版本发布信息
if (process.env.BUILD_TIME) {
  console.log('当前环境:' + process.env.BUILD_ENV)
  console.log('版本发布时间:' + process.env.BUILD_TIME)
}

// 应用程序启动
startUp()


