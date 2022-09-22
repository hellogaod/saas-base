var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = y[op[0] & 2 ? "return" : op[0] ? "throw" : "next"]) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [0, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
var _this = this;
import Vue from 'vue';
import Router from 'vue-router';
import store from '~/store';
import storeInit from '~/core/bootstrap/store.init';
import DashboardRoute from './dashboard.route'; //根页面
import systemManage from './system-manage.route'; //系统管理
import { Message } from "element-ui";
var manLogin = function () { return import('~/pages/manage/man-login.vue'); }; //管理台登录
var sysLogin = function () { return import('~/pages/system-web/sys-login.vue'); }; //企业端登录
var NotFound = function () { return import('~/pages/not-found.vue'); };
Vue.use(Router);
// 生成路由配置
var routes = [
    {
        path: '/',
        name: 'sysDevLogin',
        component: sysLogin
    },
    {
        path: '/manage',
        name: 'managerLogin',
        component: manLogin
    },
    {
        path: '/system-web',
        name: 'sysLogin',
        component: sysLogin
    }
].concat(DashboardRoute, systemManage, [
    {
        path: '*',
        name: 'not-found',
        component: NotFound
    }
]);
// 生成路由实体
var router = new Router({
    // mode: 'history',
    mode: 'hash',
    routes: routes
});
router.beforeEach(function (to, from, next) { return __awaiter(_this, void 0, void 0, function () {
    return __generator(this, function (_a) {
        switch (_a.label) {
            case 0:
                if (!(to.path === "/" || to.path === "/system-web" || to.path === "/system-web/")) return [3 /*break*/, 2];
                return [4 /*yield*/, store.dispatch("clearUserLoginData")];
            case 1:
                _a.sent();
                _a.label = 2;
            case 2:
                if (store.state.tokenExpire && to.path !== "/" && to.path !== "/system-web" && to.path !== "/system-web/" && to.path !== "/manage") {
                    // 重置用户过期状态
                    store.commit('updateTokenExpire', false);
                    Message.info("用户登录过期,请重新登录");
                    next("/");
                }
                if (!!store.state.ready) return [3 /*break*/, 4];
                return [4 /*yield*/, storeInit({
                        store: store,
                        router: router
                    })];
            case 3:
                _a.sent();
                _a.label = 4;
            case 4:
                next();
                return [2 /*return*/];
        }
    });
}); });
/**
 * 路由前置守卫
 * 权限检测
 */
router.beforeResolve(function (_a, from, next) {
    // let component = getComponent(matched)
    var matched = _a.matched, path = _a.path;
    // if (component && authCheck(component)) {
    //   next('/404')
    // } else {
    //   next()
    // }
    next();
});
/**
 * 路由后置守卫
 * 布局检测
 */
router.afterEach(function (to, from) {
    //  布局检测
    layoutCheck(to.matched);
    // Tab更新检测
    tabsCheck(to.path, from.path);
});
function authCheck(component) {
    var auth = component['$auth'];
    if (auth) {
        var target = store.state.menuResource.find(function (x) { return x.id === auth || auth < 0; });
        return !target;
    }
}
// 布局监测
function layoutCheck(matched) {
    var component = getComponent(matched);
    if (component) {
        var targetLayout = component['$layout'] || 'default';
        if (store.state.layout !== targetLayout) {
            store.commit('updateLayout', targetLayout);
        }
    }
}
function getComponent(matched) {
    if (matched && matched.length > 0) {
        var components = matched[0].components;
        return components.default;
    }
}
/**
 * Tabs更新检测
 * @param toPath
 * @param fromPath
 */
function tabsCheck(toPath, fromPath) {
    if (!toPath || toPath == '/') {
        return;
    }
    var toItem = store.state.menuResource.find(function (x) { return x.url === toPath; });
    var fromItem = store.state.menuResource.find(function (x) { return x.url === fromPath; });
    if (!toItem) {
        return;
    }
    if (store.state.workspace.currentTabs.length == 0 || !fromItem) {
        store.dispatch('workspace/updateTabs', toItem.parentId);
    }
    if (toItem.url !== store.state.workspace.currentTab) {
        // 更新当前选中tab
        store.commit('workspace/updateCurrentTab', toItem.url);
    }
}
export default router;
