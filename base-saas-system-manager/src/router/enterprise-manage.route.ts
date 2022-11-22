//企业管理端
const RoleManage = () => import('~/pages/enterprise-manage/base/ent-role.vue')//角色管理
const UserManage = () => import('~/pages/enterprise-manage/base/ent-user.vue')//用户管理
const enterpriseCompany = () => import('~/pages/enterprise-manage/base/ent-profile.vue')//公司简介
const dictManage = () => import('~/pages/enterprise-manage/base/ent-dict-manage.vue')//字典管理
const organizationManage = () => import('~/pages/enterprise-manage/base/ent-organization.vue')// 组织架构管理
const systemParams = () => import('~/pages/enterprise-manage/base/ent-params.vue')// 系统参数配置

const sysLoginLog = () => import('~/pages/enterprise-manage/base/ent-login-log.vue')//登录日志
const systemOperationLog = () => import('~/pages/enterprise-manage/base/ent-operation-log.vue')//操作日志
const systemOperationViewLog = () => import('~/pages/enterprise-manage/base/ent-operation-log-detail.vue')//操作日志查看

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
    name: 'enterpriseCompany',
    component: enterpriseCompany
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
