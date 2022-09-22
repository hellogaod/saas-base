export default (function (_a) {
    var store = _a.store;
    return ({
        /**
         * 资源认证
         */
        auth: {
            bind: function (el, binding, vnode) {
                // 获取权限编码
                var authCode = binding.expression;
                authCode = authCode.substring(1, authCode.length - 1);
                // 验证权限码
                if (!binding.expression) {
                    console.log('未传入权限');
                    return;
                }
                var hasAuth = (store.state.controlResource || []).includes(authCode);
                // 验证权限
                // TODO 开发期间，去掉权限认证
                if (!hasAuth) {
                    el.style.display = 'none';
                }
            }
        }
    });
});
