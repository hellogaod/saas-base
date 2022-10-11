//在tsconfig.json中配置
//作用：支持ts文件引入vue文件
// e.g.类似import('~/pages/system-manage/system-manage-login.vue')，vue文件的引用不会出现红色波浪线
declare module '*.vue' {
  import Vue from 'vue'
  export default Vue
}
