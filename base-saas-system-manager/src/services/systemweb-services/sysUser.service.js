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
*systemweb suer管理 service
*/
import { systemwebService } from '~/config/server';
import { NetService } from '~/utils/net.service';
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service";
var sysUserService = /** @class */ (function (_super) {
    __extends(sysUserService, _super);
    function sysUserService() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
     * 添加用户
     */
    sysUserService.prototype.addUser = function (data) {
        return this.netService.send({
            server: systemwebService.syswebUserController.addUser,
            data: data,
            loading: true
        });
    };
    /**
     * 查询单个用户
     */
    sysUserService.prototype.getUserById = function (userId) {
        return this.netService.send({
            server: systemwebService.syswebUserController.getUserById,
            data: { userId: userId },
            loading: true
        });
    };
    /**
     * 获取用户列表
     */
    sysUserService.prototype.getUserList = function (data, page) {
        return this.netService.send({
            server: systemwebService.syswebUserController.getUserList,
            data: data,
            page: page,
            loading: true
        });
    };
    /**
     * 重置密码
     */
    sysUserService.prototype.resetPassword = function (userId) {
        return this.netService.send({
            server: systemwebService.syswebUserController.resetPassword,
            data: { userId: userId },
            loading: true
        });
    };
    /**
     * 修改用户信息
     */
    sysUserService.prototype.updateUser = function (data) {
        return this.netService.send({
            server: systemwebService.syswebUserController.updateUser,
            data: data,
            loading: true
        });
    };
    /**
     * 用户启用停用
     */
    sysUserService.prototype.updateStatus = function (_a) {
        var status = _a.status, userId = _a.userId;
        return this.netService.send({
            server: systemwebService.syswebUserController.updateStatus,
            data: {
                status: status,
                userId: userId
            },
            loading: true
        });
    };
    __decorate([
        Inject(NetService)
    ], sysUserService.prototype, "netService", void 0);
    __decorate([
        Debounce()
    ], sysUserService.prototype, "addUser", null);
    __decorate([
        Debounce()
    ], sysUserService.prototype, "getUserById", null);
    __decorate([
        Debounce()
    ], sysUserService.prototype, "getUserList", null);
    __decorate([
        Debounce()
    ], sysUserService.prototype, "resetPassword", null);
    __decorate([
        Debounce()
    ], sysUserService.prototype, "updateUser", null);
    __decorate([
        Debounce()
    ], sysUserService.prototype, "updateStatus", null);
    return sysUserService;
}(Service));
export { sysUserService };
