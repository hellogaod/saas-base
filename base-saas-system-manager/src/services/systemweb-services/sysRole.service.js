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
*systemweb 角色管理 service
*/
import { systemwebService } from '~/config/server';
import { NetService } from '~/utils/net.service';
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service";
var sysRoleService = /** @class */ (function (_super) {
    __extends(sysRoleService, _super);
    function sysRoleService() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
     * 添加角色
     */
    sysRoleService.prototype.addRole = function (data) {
        return this.netService.send({
            server: systemwebService.syswebRoleController.addRole,
            data: data,
            loading: true
        });
    };
    /**
     * 角色分配，获取角色
     */
    sysRoleService.prototype.getRole = function (userId) {
        return this.netService.send({
            server: systemwebService.syswebRoleController.getRole,
            data: { userId: userId },
            loading: true
        });
    };
    /**
     * 查询单个角色
     */
    sysRoleService.prototype.getRoleById = function (roleId) {
        return this.netService.send({
            server: systemwebService.syswebRoleController.getRoleById,
            data: { roleId: roleId },
            loading: true
        });
    };
    /**
     * 获取角色列表
     */
    sysRoleService.prototype.getRoleList = function (data, page) {
        return this.netService.send({
            server: systemwebService.syswebRoleController.getRoleList,
            data: data,
            page: page,
            loading: true
        });
    };
    /**
     * 修改角色信息
     */
    sysRoleService.prototype.updateRole = function (data) {
        return this.netService.send({
            server: systemwebService.syswebRoleController.updateRole,
            data: data,
            loading: true
        });
    };
    /**
     * 角色启用停用
     */
    sysRoleService.prototype.updateStatus = function (_a) {
        var status = _a.status, roleId = _a.roleId;
        return this.netService.send({
            server: systemwebService.syswebRoleController.updateStatus,
            data: {
                status: status,
                roleId: roleId
            },
            loading: true
        });
    };
    __decorate([
        Inject(NetService)
    ], sysRoleService.prototype, "netService", void 0);
    __decorate([
        Debounce()
    ], sysRoleService.prototype, "addRole", null);
    __decorate([
        Debounce()
    ], sysRoleService.prototype, "getRole", null);
    __decorate([
        Debounce()
    ], sysRoleService.prototype, "getRoleById", null);
    __decorate([
        Debounce()
    ], sysRoleService.prototype, "getRoleList", null);
    __decorate([
        Debounce()
    ], sysRoleService.prototype, "updateRole", null);
    __decorate([
        Debounce()
    ], sysRoleService.prototype, "updateStatus", null);
    return sysRoleService;
}(Service));
export { sysRoleService };
