import Vue from 'vue'

// const creatDataDictService = () => import('~/services/business-services/data-dict.services')
// const createLoginService = () => import('~/services/business-services/login.services')
// const createCompanyService = () => import('~/services/business-services/company.services')
// import { MessageService } from '~/utils/message.services'

export default async function ({ store, router }) {
  // let { DataDictService } = await creatDataDictService()
  // let { LoginService } = await createLoginService()
  // let { CompanyService } = await createCompanyService()

  // let dataDictService = new DataDictService()
  // let loginService = new LoginService()
  // let companyService = new CompanyService()

  // 启动数据初始化
  let flag = await Promise.all([
    // updateDictData(),
    // updateUserData()
  ]).then(() => {
    return true
  }).catch(ex => {
    // 基础数据初始化失败
    console.log('基础数据初始化失败')
    return false
  })

  // 初始化业务数据
  if (flag) {
    // await updateBusinessData()
  }

  store.commit('ready', true)

  /**
   * 检测用户数据
   */
  async function updateUserData() {
    return new Promise((reslove, reject) => {
      // 登录页面不更新用户数据
      if (!store.state.userToken && window.location.pathname == "/") {
        reslove()
        return
      }

      // 不存在token不更新用户数据
      if (!store.state.userToken && window.location.pathname != "/") {
        store.commit("updateTokenExpire", true)
        reject()
        return
      }

      // 更新用户数据
      // loginService.getUserByToken().subscribe(({ user }) => {
      //   // 更新用户控件资源
      //   store.dispatch('updateUserLoginData', { user })
      //   reslove()
      // }, () => {
      //   // 用户数据获取异常
      //   store.commit("updateTokenExpire", true)
      //   reject()
      // })
    })
  }

  /**
   * 检查数据字典
   * @param reslove
   */
  function updateDictData() {
    return new Promise((reslove, reject) => {
      if (!store.state.dictDataHash) {
        return getDictData(reslove, reject)
      }

      // dataDictService.getDictHash().subscribe(({ dataDictHashCode }) => {
      //   if (store.state.dictDataHash! == store.state.dictDataHash) {
      //     getDictData(reslove, reject)
      //   } else {
      //     reslove()
      //   }
      // }, () => {
      //   reject()
      // })
    })
  }

  /**
   * 获取数据字典
   * @param reslove
   */
  function getDictData(reslove, reject) {
    // return dataDictService.getDictData().subscribe((data) => {
    //   store.commit('updateDictData', data)
    //   reslove()
    // }, () => {
    //   reject()
    // })
  }

  /**
   * 更新业务数据
   * 更新公司列表
   * 更新委托方列表
   * 更新部门列表
   */
  function updateBusinessData() {
    return new Promise(async (reslove, reject) => {
      // 全局业务数据 - 与用户无关
      await store.dispatch('getCompanyList')
      reslove()
    })
  }
}
