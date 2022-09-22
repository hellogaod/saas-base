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
*manage 系统登录 service
*/
import { managerService } from '~/config/server';
import { NetService } from '~/utils/net.service';
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service";
import md5 from "md5";
var LoginService = /** @class */ (function (_super) {
    __extends(LoginService, _super);
    function LoginService() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
 * @description 加载图形验证码
 */
    LoginService.prototype.captchaApi = function () {
        return this.netService.send({
            server: managerService.sysValidateController.captchaApi,
            data: {},
            loading: true
        });
    };
    /**
     * 登录
     */
    LoginService.prototype.login = function (_a) {
        var username = _a.username, password = _a.password, code = _a.code, key = _a.key;
        return this.netService.send({
            server: managerService.loginController.login,
            data: {
                username: username,
                password: md5(password),
                code: code,
                key: key
            },
            loading: true
        });
    };
    /*获取菜单列表
     */
    LoginService.prototype.getMenu = function (_a) {
        var userId = _a.userId;
        return this.netService.send({
            server: managerService.loginController.getMenu,
            data: {
                userId: userId
            },
            loading: true
        });
    };
    /**
     * 修改密码
     */
    LoginService.prototype.updateUserPwd = function (_a) {
        var oldPwd = _a.oldPwd, newPwd = _a.newPwd;
        return this.netService.send({
            server: managerService.loginController.updateUserPwd,
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
    LoginService.prototype.logout = function () {
        return this.netService.send({
            server: managerService.loginController.logout,
            data: {},
            loading: true
        });
    };
    __decorate([
        Inject(NetService)
    ], LoginService.prototype, "netService", void 0);
    __decorate([
        Debounce()
    ], LoginService.prototype, "login", null);
    return LoginService;
}(Service));
export { LoginService };
