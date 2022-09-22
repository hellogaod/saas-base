var User = function () { return import('~/pages/dashboard/user.vue'); };
var Admin = function () { return import('~/pages/dashboard/admin.vue'); };
export default [
    {
        path: '/dashboard/user',
        name: 'user',
        component: User
    },
    {
        path: '/dashboard/admin',
        name: 'admin',
        component: Admin
    }
];
