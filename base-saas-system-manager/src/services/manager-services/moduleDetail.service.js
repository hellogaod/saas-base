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
*manage 系统模块菜单管理 service
*/
import { managerService } from '~/config/server';
import { NetService } from '~/utils/net.service';
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service";
var ModuledetailService = /** @class */ (function (_super) {
    __extends(ModuledetailService, _super);
    function ModuledetailService() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
     * 查询已启用的模块列表
     */
    ModuledetailService.prototype.getAllMenuTree = function (sysCode) {
        return this.netService.send({
            server: managerService.moduleDetailController.getAllMenuTree,
            data: { sysCode: sysCode },
            loading: true
        });
    };
    //表格数据
    ModuledetailService.prototype.getAllMenuDetailList = function (data) {
        return this.netService.send({
            server: managerService.moduleDetailController.getAllMenuDetailList,
            data: data,
            loading: true
        });
    };
    //获取一级菜单
    ModuledetailService.prototype.getOneMenu = function (flag, data) {
        return this.netService.send({
            server: managerService.moduleDetailController.getOneMenu,
            data: {
                flag: flag,
                sysCode: data
            },
            loading: true
        });
    };
    //添加菜单
    ModuledetailService.prototype.addMenu = function (data) {
        return this.netService.send({
            server: managerService.moduleDetailController.addMenu,
            data: data,
            loading: true
        });
    };
    //更改状态
    ModuledetailService.prototype.updateMenuStatus = function (data) {
        return this.netService.send({
            server: managerService.moduleDetailController.updateMenuStatus,
            data: data,
            loading: true
        });
    };
    //编辑获取信息
    ModuledetailService.prototype.getMenuById = function (id) {
        return this.netService.send({
            server: managerService.moduleDetailController.getMenuById,
            data: { id: id },
            loading: true
        });
    };
    //编辑保存信息
    ModuledetailService.prototype.editMenu = function (data) {
        return this.netService.send({
            server: managerService.moduleDetailController.editMenu,
            data: data,
            loading: true
        });
    };
    ModuledetailService.prototype.checkMenuChildStatus = function (data) {
        return this.netService.send({
            server: managerService.moduleDetailController.checkMenuChildStatus,
            data: data,
            loading: true
        });
    };
    __decorate([
        Inject(NetService)
    ], ModuledetailService.prototype, "netService", void 0);
    __decorate([
        Debounce()
    ], ModuledetailService.prototype, "getAllMenuTree", null);
    return ModuledetailService;
}(Service));
export { ModuledetailService };
