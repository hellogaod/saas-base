// 系统管理端
const sysModule = () => import('~/pages/system-manage/sys-module.vue')//模块管理
const systemOther = () => import('~/pages/system-manage/sys-other.vue')//三方参数维护
const sysEnterprise = () => import('~/pages/system-manage/sys-enterprise.vue')//企业管理
const sysMenu = () => import('~/pages/system-manage/sys-menu.vue')//业务功能（模块管理内）

export default [
  {
    path: '/sys-manage/modular',
    name: 'sysModule',
    component: sysModule,
  },
  {
    path: '/sys-manage/sys-menu/:moduleId',
    name: 'sysMenu',
    component: sysMenu
  },

  {
    path: '/sys-manage/business',
    name: 'sysEnterprise',
    component: sysEnterprise
  },

  {
    path: '/sys-manage/other',
    name: 'system-other',
    component: systemOther
  },

]
