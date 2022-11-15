import Vue from 'vue'
import Router from 'vue-router'
import store from '~/store'

import {Message} from "element-ui"

import SystemManage from './system-manage.route'//系统管理
import EnterpraiseManage from './enterprise-manage.route'//企业管理

const systemManageLogin = () => import('~/pages/system-manage/sys-login.vue')//系统管理台登录
const enterpriseManageLogin = () => import('~/pages/enterprise-manage/base/ent-login.vue')//企业管理端登录
const NotFound = () => import('~/pages/not-found.vue')//页面丢失

Vue.use(Router)

// 生成路由配置
const routes = [
  {
    path: '/',//默认进入企业管理端
    name: 'enterpriseLogin',
    component: enterpriseManageLogin
  },
  {
    path: '/sys-manage',//系统管理端路径
    name: 'systemLogin',
    component: systemManageLogin
  },
  {
    path: '/ent-manage',//企业管理端路径
    name: 'entLogin',
    component: enterpriseManageLogin
  },
  ...SystemManage,
  ...EnterpraiseManage,
  {
    path: '*',
    name: 'not-found',
    component: NotFound
  }
]

// 生成路由实体
const router = new Router({
  mode: 'history',
  // mode: 'hash',
  routes
})

//路由访问前
router.beforeEach(async (to, from, next) => {

  // 如果访问企业管理端登录页，那么清楚当前企业登录数据
  if (to.path === "/" || to.path === "/ent-manager") {
    await store.dispatch("clearUserLoginData")
  }

  //用户登录过期，跳转到企业管理端登录页面
  if (store.state.tokenExpire && to.path !== "/" && to.path !== "/ent-manager" && to.path !== "/sys-manage") {
    // 重置用户过期状态
    store.commit('updateTokenExpire', false)
    Message.info("用户登录过期,请重新登录")
    next("/")
  }

  //路由访问时，如果当前启动状态是false
  if (!store.state.ready) {
    //store启动状态设置为true
    store.commit('ready', true)
  }

  next()
})

/**
 * 路由后置守卫
 * 布局检测
 */
router.afterEach((to, from) => {
  //  布局检测
  layoutCheck(to.matched)

})

// 布局监测
function layoutCheck(matched) {
  let component = getComponent(matched)
  if (component) {
    let targetLayout = component['$layout'] || 'default'

    if (store.state.layout !== targetLayout) {
      store.commit('updateLayout', targetLayout)
    }
  }
}

function getComponent(matched) {
  if (matched && matched.length > 0) {
    let [{components}] = matched
    return components.default
  }
}


export default router
