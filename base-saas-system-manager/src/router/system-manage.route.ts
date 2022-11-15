// 系统管理端
const systemModular = () => import('~/pages/system-manage/sys-module.vue')//模块管理
const systemOther = () => import('~/pages/system-manage/sys-other.vue')//三方参数维护
const businessManage = () => import('~/pages/system-manage/sys-enterprise.vue')//企业管理
const modularPermission = () => import('~/pages/system-manage/sys-module-operation.vue')//业务功能（模块管理内）

export default [
  {
    path: '/sys-manage/modular',
    name: 'systemModular',
    component: systemModular,
  },
  {
    path: '/sys-manage/modular-permission/:sysCode',
    name: 'modularPermission',
    component: modularPermission
  },

  {
    path: '/sys-manage/business',
    name: 'businessManage',
    component: businessManage
  },

  {
    path: '/sys-manage/other',
    name: 'system-other',
    component: systemOther
  },

]
