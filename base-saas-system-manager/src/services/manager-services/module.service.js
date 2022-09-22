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
*manage 系统模块管理 service
*/
import { managerService } from '~/config/server';
import { NetService } from '~/utils/net.service';
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service";
var ModuleService = /** @class */ (function (_super) {
    __extends(ModuleService, _super);
    function ModuleService() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
     * 查询已启用的模块列表
     */
    ModuleService.prototype.getEffectiveModule = function () {
        return this.netService.send({
            server: managerService.moduleController.getEffectiveModule,
            data: {},
            loading: true
        });
    };
    /*根据主键查询模块信息
     */
    ModuleService.prototype.getModuleInfo = function (sysCode) {
        return this.netService.send({
            server: managerService.moduleController.getModuleInfo,
            data: { sysCode: sysCode },
            loading: true
        });
    };
    /*查询模块列表
     */
    ModuleService.prototype.getModuleList = function (data, page) {
        return this.netService.send({
            server: managerService.moduleController.getModuleList,
            data: data,
            page: page,
            loading: true
        });
    };
    /*根据模块主键查询模块相关的菜单
     */
    ModuleService.prototype.getModuleMenuListBySysCode = function (_a) {
        return this.netService.send({
            server: managerService.moduleController.getModuleMenuListBySysCode,
            data: {},
            loading: true
        });
    };
    /*保存系统模块
     */
    ModuleService.prototype.saveSysModule = function (data) {
        return this.netService.send({
            server: managerService.moduleController.saveSysModule,
            data: data,
            loading: true
        });
    };
    /*修改系统状态
     */
    ModuleService.prototype.updateStatus = function (data) {
        return this.netService.send({
            server: managerService.moduleController.updateStatus,
            data: data,
            loading: true
        });
    };
    /*修改系统模块
     */
    ModuleService.prototype.updateSysModule = function (data) {
        return this.netService.send({
            server: managerService.moduleController.updateSysModule,
            data: data,
            loading: true
        });
    };
    __decorate([
        Inject(NetService)
    ], ModuleService.prototype, "netService", void 0);
    __decorate([
        Debounce()
    ], ModuleService.prototype, "getEffectiveModule", null);
    return ModuleService;
}(Service));
export { ModuleService };
