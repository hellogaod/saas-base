var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
/*
*systemweb 系统登录 service
*/
import { systemwebService } from '~/config/server';
import { NetService } from '~/utils/net.service';
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service";
import md5 from "md5";
var webLoginService = /** @class */ (function (_super) {
    __extends(webLoginService, _super);
    function webLoginService() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
     * 登录
     */
    webLoginService.prototype.doLoginRsa = function (_a) {
        var keyScript = _a.keyScript;
        return this.netService.send({
            server: systemwebService.syswebLoginController.doLogin,
            data: {
                keyScript: keyScript
            },
            loading: true
        });
    };
    /**
   * @description 加载图形验证码
   */
    webLoginService.prototype.captchaApi = function () {
        return this.netService.send({
            server: systemwebService.syswebValidateController.captchaApi,
            data: {},
            loading: true
        });
    };
    /**
     * 登录
     */
    webLoginService.prototype.doLogin = function (_a) {
        var userName = _a.userName, password = _a.password, companyCode = _a.companyCode, code = _a.code, key = _a.key;
        return this.netService.send({
            server: systemwebService.syswebLoginController.doLogin,
            data: {
                userName: userName,
                password: md5(password),
                companyCode: companyCode,
                code: code,
                key: key
            },
            loading: true
        });
    };
    /**
     * 获取菜单和模块
     */
    webLoginService.prototype.index = function () {
        return this.netService.send({
            server: systemwebService.syswebLoginController.index,
            data: {},
            loading: true
        });
    };
    /**
     * 修改密码
     */
    webLoginService.prototype.updateUserPwd = function (_a) {
        var oldPwd = _a.oldPwd, newPwd = _a.newPwd;
        return this.netService.send({
            server: systemwebService.syswebLoginController.updateUserPwd,
            data: {
                oldPwd: oldPwd,
                newPwd: newPwd
            },
            loading: true
        });
    };
    /**
     * 退出
     */
    webLoginService.prototype.logout = function () {
        return this.netService.send({
            server: systemwebService.syswebLoginController.logout,
            data: {},
            loading: true
        });
    };
    __decorate([
        Inject(NetService)
    ], webLoginService.prototype, "netService", void 0);
    __decorate([
        Debounce()
    ], webLoginService.prototype, "doLoginRsa", null);
    __decorate([
        Debounce()
    ], webLoginService.prototype, "doLogin", null);
    return webLoginService;
}(Service));
export { webLoginService };
