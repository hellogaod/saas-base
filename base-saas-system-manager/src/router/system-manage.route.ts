// 平台
const systemModular = () => import('~/pages/manager/system-manage/system-modular.vue')//系统模块管理
const systemOther = () => import('~/pages/manager/system-manage/system-other.vue')//参数维护
const bussManage = () => import('~/pages/manager/system-manage/business-manage.vue')//企业管理
const modularPermission = () => import('~/pages/manager/system-manage/module-permission.vue')//业务功能
//企业
const RoleManage = () => import('~/pages/system-web/system-manage/role-manage.vue')//角色管理
const UserManage = () => import('~/pages/system-web/system-manage/user-manage.vue')//用户管理
const systemLog = () => import('~/pages/system-web/system-manage/system-log.vue')//登录日志
const systemOperationLog = () => import('~/pages/system-web/system-manage/system-operationLog.vue')//操作日志
const systemViewLog = () => import('~/pages/system-web/system-manage/system-view.vue')//操作日志查看
const systemCompany = () => import('~/pages/system-web/system-manage/system-company.vue')//公司简介
const dictManage = () => import('~/pages/system-web/system-manage/dict-manage.vue')//字典管理
const organizationManage = () => import('~/pages/system-web/system-manage/organization-manage.vue')// 组织架构管理
const systemParams = () => import('~/pages/system-web/system-manage/system-params.vue')// 系统参数配置

export default [
  {
    path: '/system-manage/role-manage',
    name: 'role-manage',
    component: RoleManage
  },
  {
    path: '/system-manage/user-manage',
    name: 'user-manage',
    component: UserManage
  },
  {
    path: '/system-manage/system-log',
    name: 'system-log',
    component: systemLog
  },
  {
    path: '/system-manage/system-modular',
    name: 'system-modular',
    component: systemModular,
    // children:[
    //   {
    //     path: '/system-manage/system-modular/modular-permission',
    //     name: 'modular-permission',
    //     component: modularPermission
    //   },
    // ]
  },
  {
    path: '/system-manage/modular-permission/:sysCode',
    name: 'modular-permission',
    component: modularPermission
  },

  {
    path: '/system-manage/business-manage',
    name: 'buss-manage',
    component: bussManage
  },
  {
    path: '/system-manage/system-company',
    name: 'system-company',
    component: systemCompany
  },
  {
    path: '/system-manage/system-other',
    name: 'system-other',
    component: systemOther
  },
  {
    path: '/system-manage/dict-manage',
    name: 'dict-manage',
    component: dictManage
  },
  {
    path: '/system-web/organization-manage',
    name: 'organization-manage',
    component: organizationManage
  },
  {
    path: '/system-manage/params-manage',
    name: 'system-params',
    component: systemParams
  },

  {
    path: '/system-manage/system-operationLog',
    name: 'system-operationLog',
    component:systemOperationLog
  },
  {
    path: '/system-manage/system-view',
    name: 'system-view',
    component:systemViewLog
  },

]
