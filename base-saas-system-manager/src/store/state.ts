export default {
  //启动状态
  ready: false,
  // 页面布局:只有default和workspace两个布局；default目前用于notfund，workspace用于各个菜单管理页面
  layout: 'default',
  // 菜单折叠状态
  menuCollapse: false,
  // 当前主题样式
  theme: 'theme-default',

  // 用户token:在用户登录会设置userToken；用户退出会清理用户token
  //还会通过StorageService本地缓存
  userToken: '',
  // token是否过期
  tokenExpire: false,
  // 用户数据
  userData: '',
  // 公司编号
  companyCode: '',

  //公司名称
  // companyName: '',

  //当前所有的模块
  modules:[],
  //当前选中的模块
  selectedModule:'',
  //当前选中模块下的菜单集合
  selectedMenus:[],
}
