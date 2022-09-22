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
*systemweb organization机构管理 service
*/
import { systemwebService } from '~/config/server';
import { NetService } from '~/utils/net.service';
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service";
var webOrganizationService = /** @class */ (function (_super) {
    __extends(webOrganizationService, _super);
    function webOrganizationService() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
     * 查询table数据列表
     */
    webOrganizationService.prototype.getOrgList = function () {
        return this.netService.send({
            server: systemwebService.syswebOrganizationController.getOrgList,
            data: {},
            loading: true
        });
    };
    /**
     * 用户管理数据权限中获取组织架构的菜单树
     */
    webOrganizationService.prototype.getOrgByOrgId = function (orgId, userId, menuId) {
        return this.netService.send({
            server: systemwebService.syswebOrganizationController.getOrgByOrgId,
            data: { orgId: orgId, userId: userId, menuId: menuId },
            loading: true
        });
    };
    /**
     * 添加
     */
    webOrganizationService.prototype.saveOrg = function (data) {
        return this.netService.send({
            server: systemwebService.syswebOrganizationController.saveOrg,
            data: data,
            loading: true
        });
    };
    /**
     * 修改弹框 根据orgId回显数据
     */
    webOrganizationService.prototype.getOrgById = function (orgId) {
        return this.netService.send({
            server: systemwebService.syswebOrganizationController.getOrgById,
            data: { orgId: orgId },
            loading: true
        });
    };
    /**
    * 修改
    */
    webOrganizationService.prototype.updateOrg = function (data) {
        return this.netService.send({
            server: systemwebService.syswebOrganizationController.updateOrg,
            data: data,
            loading: true
        });
    };
    /**
     * 启用/停用
     */
    webOrganizationService.prototype.updateOrgStatus = function (data) {
        return this.netService.send({
            server: systemwebService.syswebOrganizationController.updateOrgStatus,
            data: data,
            loading: true
        });
    };
    __decorate([
        Inject(NetService)
    ], webOrganizationService.prototype, "netService", void 0);
    __decorate([
        Debounce()
    ], webOrganizationService.prototype, "getOrgList", null);
    __decorate([
        Debounce()
    ], webOrganizationService.prototype, "getOrgByOrgId", null);
    __decorate([
        Debounce()
    ], webOrganizationService.prototype, "saveOrg", null);
    __decorate([
        Debounce()
    ], webOrganizationService.prototype, "getOrgById", null);
    __decorate([
        Debounce()
    ], webOrganizationService.prototype, "updateOrg", null);
    __decorate([
        Debounce()
    ], webOrganizationService.prototype, "updateOrgStatus", null);
    return webOrganizationService;
}(Service));
export { webOrganizationService };
