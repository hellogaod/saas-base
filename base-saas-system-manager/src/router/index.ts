import Vue from 'vue'
import Router from 'vue-router'
import store from '~/store'
import storeInit from '~/core/bootstrap/store.init'

import DashboardRoute from './dashboard.route'//根页面
import systemManage from './system-manage.route'//系统管理

import { Message } from "element-ui"
const manLogin = () => import('~/pages/manager/man-login.vue')//管理台登录
const sysLogin = () => import('~/pages/system-web/sys-login.vue')//企业端登录
const NotFound = () => import('~/pages/not-found.vue')

Vue.use(Router)

// 生成路由配置
const routes = [
    {
        path: '/',//默认进入企业端
        name: 'sysDevLogin',
        component: sysLogin
    },
    {
        path: '/manager',
        name: 'managerLogin',
        component: manLogin
    },
    {
        path: '/system-web',
        name: 'sysLogin',
        component: sysLogin
    },
    ...DashboardRoute,
    ...systemManage,
    {
        path: '*',
        name: 'not-found',
        component: NotFound
    }
]

// 生成路由实体
const router = new Router({
    // mode: 'history',
    mode: 'hash',
    routes
})

router.beforeEach(async (to, from, next) => {
    if (to.path === "/"||to.path === "/system-web"||to.path==="/system-web/") {
        await store.dispatch("clearUserLoginData")
    }

    if (store.state.tokenExpire && to.path !== "/" && to.path !== "/system-web"&& to.path !== "/system-web/" && to.path !== "/manager"  ) {
        // 重置用户过期状态
        store.commit('updateTokenExpire', false)
        Message.info("用户登录过期,请重新登录")
        next("/")
    }

    if (!store.state.ready) {
        await storeInit({
            store,
            router
        })
    }

    next()
})

/**
 * 路由前置守卫
 * 权限检测
 */
router.beforeResolve(({ matched, path }, from, next) => {
    // let component = getComponent(matched)

    // if (component && authCheck(component)) {
    //   next('/404')
    // } else {
    //   next()
    // }
    next()
})

/**
 * 路由后置守卫
 * 布局检测
 */
router.afterEach((to, from) => {
    //  布局检测
    layoutCheck(to.matched)
    // Tab更新检测
    tabsCheck(to.path, from.path)
})

function authCheck(component) {
    let auth = component['$auth']
    if (auth) {
        let target = store.state.menuResource.find(x => x.id === auth || auth < 0)
        return !target
    }
}

// 布局监测
function layoutCheck(matched) {
    let component = getComponent(matched)
    if (component) {
        let targetLayout = component['$layout'] || 'default'
        if (store.state.layout !== targetLayout) {
            store.commit('updateLayout', targetLayout )
        }
    }
}

function getComponent(matched) {
    if (matched && matched.length > 0) {
        let [{ components }] = matched
        return components.default
    }
}

/**
 * Tabs更新检测
 * @param toPath
 * @param fromPath
 */
function tabsCheck(toPath, fromPath) {
    if (!toPath || toPath == '/') {
        return
    }

    let toItem: any = store.state.menuResource.find((x: any) => x.url === toPath);
    let fromItem: any = store.state.menuResource.find((x: any) => x.url === fromPath);

    if (!toItem) {
        return
    }
    if (store.state.workspace.currentTabs.length == 0 || !fromItem) {
        store.dispatch('workspace/updateTabs', toItem.parentId)
    }

    if (toItem.url !== store.state.workspace.currentTab) {
        // 更新当前选中tab
        store.commit('workspace/updateCurrentTab', toItem.url)
    }
}

export default router
