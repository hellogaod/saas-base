// 平台
var systemModular = function () { return import('~/pages/manage/system-manage/system-modular.vue'); }; //系统模块管理
var systemOther = function () { return import('~/pages/manage/system-manage/system-other.vue'); }; //参数维护
var bussManage = function () { return import('~/pages/manage/system-manage/business-manage.vue'); }; //企业管理
var modularPermission = function () { return import('~/pages/manage/system-manage/module-permission.vue'); }; //业务功能
//企业
var RoleManage = function () { return import('~/pages/system-web/system-manage/role-manage.vue'); }; //角色管理
var UserManage = function () { return import('~/pages/system-web/system-manage/user-manage.vue'); }; //用户管理
var systemLog = function () { return import('~/pages/system-web/system-manage/system-log.vue'); }; //登录日志
var systemOperationLog = function () { return import('~/pages/system-web/system-manage/system-operationLog.vue'); }; //操作日志
var systemViewLog = function () { return import('~/pages/system-web/system-manage/system-view.vue'); }; //操作日志查看
var systemCompany = function () { return import('~/pages/system-web/system-manage/system-company.vue'); }; //公司简介
var dictManage = function () { return import('~/pages/system-web/system-manage/dict-manage.vue'); }; //字典管理
var organizationManage = function () { return import('~/pages/system-web/system-manage/organization-manage.vue'); }; // 组织架构管理
var systemParams = function () { return import('~/pages/system-web/system-manage/system-params.vue'); }; // 系统参数配置
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
        component: systemOperationLog
    },
    {
        path: '/system-manage/system-view',
        name: 'system-view',
        component: systemViewLog
    },
];
