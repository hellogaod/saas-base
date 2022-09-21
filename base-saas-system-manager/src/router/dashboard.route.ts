const User = () => import('~/pages/dashboard/user.vue')
const Admin = () => import('~/pages/dashboard/admin.vue')

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
]
