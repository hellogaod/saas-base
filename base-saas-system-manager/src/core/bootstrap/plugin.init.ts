import Vue from 'vue'
import ElementUI from 'element-ui'
import VueImg from 'v-img';
import 'element-ui/lib/theme-chalk/index.css'

export default async function ({ store }) {
  //安装v-img插件
  Vue.use(VueImg);
  // 安装ElementUI
  Vue.use(ElementUI)
}
