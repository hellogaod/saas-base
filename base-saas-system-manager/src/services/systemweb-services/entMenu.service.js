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
*systemweb 系统菜单 service
*/
import { systemwebService } from '~/config/server';
import { NetService } from '~/utils/net.service';
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service";
var entMenuService = /** @class */ (function (_super) {
    __extends(entMenuService, _super);
    function entMenuService() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
     * 获取菜单树的集合
     */
    entMenuService.prototype.getAllMenuTree = function (sysCode) {
        return this.netService.send({
            server: systemwebService.syswebEntmenuController.getAllMenuTree,
            data: { sysCode: sysCode },
            loading: true
        });
    };
    /**
     * 获取企业模块对应下的菜单
     */
    entMenuService.prototype.getEntMenuByCode = function (entModuleCode) {
        return this.netService.send({
            server: systemwebService.syswebEntmenuController.getEntMenuByCode,
            data: { entModuleCode: entModuleCode },
            loading: true
        });
    };
    /**
     * 获取菜单详情
     */
    entMenuService.prototype.getMenuDetail = function (data) {
        return this.netService.send({
            server: systemwebService.syswebEntmenuController.getMenuDetail,
            data: {
                parentId: data.parentId,
                entMenuId: data.entMenuId,
                menuId: data.menuId,
                userId: data.userId,
                sysCode: data.sysCode,
                roleId: data.roleId
            },
            loading: true
        });
    };
    __decorate([
        Inject(NetService)
    ], entMenuService.prototype, "netService", void 0);
    __decorate([
        Debounce()
    ], entMenuService.prototype, "getAllMenuTree", null);
    __decorate([
        Debounce()
    ], entMenuService.prototype, "getEntMenuByCode", null);
    __decorate([
        Debounce()
    ], entMenuService.prototype, "getMenuDetail", null);
    return entMenuService;
}(Service));
export { entMenuService };
