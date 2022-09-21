import Vue from 'vue'

export default ({ store }): Object => ({
  /**
   * 资源认证
   */
  auth: {
    bind(el, binding, vnode) {
      // 获取权限编码
      let authCode = binding.expression
      authCode=authCode.substring(1,authCode.length-1)
      // 验证权限码
      if (!binding.expression) {
        console.log('未传入权限')
        return
      }
      let hasAuth: boolean = (store.state.controlResource || []).includes(authCode)
      // 验证权限
      // TODO 开发期间，去掉权限认证
      if (!hasAuth) {
        el.style.display = 'none'
      }
    }
  }
})
