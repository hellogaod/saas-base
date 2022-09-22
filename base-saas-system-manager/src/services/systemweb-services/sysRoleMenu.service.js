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
*systemweb 角色权限管理 service
*/
import { systemwebService } from '~/config/server';
import { NetService } from '~/utils/net.service';
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service";
var sysRoleMenuService = /** @class */ (function (_super) {
    __extends(sysRoleMenuService, _super);
    function sysRoleMenuService() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
     * 根据角色编号查询已有的菜单编号
     */
    sysRoleMenuService.prototype.getMenuByRoleId = function (_a) {
        var roleId = _a.roleId, sysCode = _a.sysCode;
        return this.netService.send({
            server: systemwebService.syswebRoleMenuController.getMenuByRoleId,
            data: {
                roleId: roleId,
                sysCode: sysCode
            },
            loading: true
        });
    };
    /**
     * 添加角色权限
     */
    sysRoleMenuService.prototype.addRoleMenu = function (data) {
        return this.netService.send({
            server: systemwebService.syswebRoleMenuController.addRoleMenu,
            data: data,
            loading: true
        });
    };
    /**
    * 获取菜单-用户管理（角色分配）
    */
    sysRoleMenuService.prototype.getMenuListByRoleId = function (_a) {
        var roleId = _a.roleId, sysCode = _a.sysCode;
        return this.netService.send({
            server: systemwebService.syswebRoleMenuController.getMenuListByRoleId,
            data: { roleId: roleId, sysCode: sysCode },
            loading: true
        });
    };
    __decorate([
        Inject(NetService)
    ], sysRoleMenuService.prototype, "netService", void 0);
    __decorate([
        Debounce()
    ], sysRoleMenuService.prototype, "getMenuByRoleId", null);
    __decorate([
        Debounce()
    ], sysRoleMenuService.prototype, "addRoleMenu", null);
    __decorate([
        Debounce()
    ], sysRoleMenuService.prototype, "getMenuListByRoleId", null);
    return sysRoleMenuService;
}(Service));
export { sysRoleMenuService };
