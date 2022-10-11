//企业管理端
const RoleManage = () => import('~/pages/enterprise-manage/base/role-manage.vue')//角色管理
const UserManage = () => import('~/pages/enterprise-manage/base/user-manage.vue')//用户管理
const systemCompany = () => import('~/pages/enterprise-manage/base/system-company.vue')//公司简介
const dictManage = () => import('~/pages/enterprise-manage/base/dict-manage.vue')//字典管理
const organizationManage = () => import('~/pages/enterprise-manage/base/organization-manage.vue')// 组织架构管理
const systemParams = () => import('~/pages/enterprise-manage/base/system-params.vue')// 系统参数配置

const sysLoginLog = () => import('~/pages/enterprise-manage/base/system-login-log.vue')//登录日志
const systemOperationLog = () => import('~/pages/enterprise-manage/base/system-operation-log.vue')//操作日志
const systemOperationViewLog = () => import('~/pages/enterprise-manage/base/system-view.vue')//操作日志查看

export default [
  {
    path: '/ent-manage/role',
    name: 'RoleManage',
    component: RoleManage
  },
  {
    path: '/ent-manage/user',
    name: 'UserManage',
    component: UserManage
  },
  {
    path: '/ent-manage/company',
    name: 'systemCompany',
    component: systemCompany
  },

  {
    path: '/ent-manage/dict',
    name: 'dictManage',
    component: dictManage
  },
  {
    path: '/ent-manage/organization',
    name: 'organizationManage',
    component: organizationManage
  },
  {
    path: '/ent-manage/params',
    name: 'systemParams',
    component: systemParams
  },
  {
    path: '/ent-manage/login-log',
    name: 'sysLoginLog',
    component: sysLoginLog
  },
  {
    path: '/ent-manage/operation-log',
    name: 'systemOperationLog',
    component:systemOperationLog
  },
  {
    path: '/ent-manage/operation-log-view',
    name: 'systemOperationViewLog',
    component:systemOperationViewLog
  },

]
