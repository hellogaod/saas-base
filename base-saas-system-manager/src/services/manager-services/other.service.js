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
*manage 系统参数 service
*/
import { managerService } from '~/config/server';
import { NetService } from '~/utils/net.service';
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service";
var OtherService = /** @class */ (function (_super) {
    __extends(OtherService, _super);
    function OtherService() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
     * 查询企业三方关联列表
     */
    OtherService.prototype.getCompanyConfigList = function (companyCode) {
        return this.netService.send({
            server: managerService.otherController.getCompanyConfigList,
            data: { companyCode: companyCode },
            loading: true
        });
    };
    /**
     * 查询参数配置
     */
    OtherService.prototype.getOtherConfigInfo = function (id) {
        return this.netService.send({
            server: managerService.otherController.getOtherConfigInfo,
            data: { id: id },
            loading: true
        });
    };
    /**
     * 查询启用的三方列表
     */
    OtherService.prototype.getOtherConfigList = function (companyCode) {
        return this.netService.send({
            server: managerService.otherController.getOtherConfigList,
            data: { companyCode: companyCode },
            loading: true
        });
    };
    /**
    * 查询配置列表
    */
    OtherService.prototype.getOtherList = function (data, page) {
        return this.netService.send({
            server: managerService.otherController.getOtherList,
            data: data,
            page: page,
            loading: true
        });
    };
    /**
     * 保存参数配置
     */
    OtherService.prototype.saveOtherConfig = function (data) {
        return this.netService.send({
            server: managerService.otherController.saveOtherConfig,
            data: data,
            loading: true
        });
    };
    /**
     * 修改配置
     */
    OtherService.prototype.updateOther = function (_a) {
        return this.netService.send({
            server: managerService.otherController.updateOther,
            data: {},
            loading: true
        });
    };
    /**
     * 修改参数配置
     */
    OtherService.prototype.updateOtherConfig = function (data) {
        return this.netService.send({
            server: managerService.otherController.updateOtherConfig,
            data: data,
            loading: true
        });
    };
    /**
     * 修改状态
     */
    OtherService.prototype.updateStatus = function (_a) {
        var status = _a.status, id = _a.id;
        return this.netService.send({
            server: managerService.otherController.updateStatus,
            data: {
                status: status,
                id: id
            },
            loading: true
        });
    };
    __decorate([
        Inject(NetService)
    ], OtherService.prototype, "netService", void 0);
    __decorate([
        Debounce()
    ], OtherService.prototype, "getCompanyConfigList", null);
    return OtherService;
}(Service));
export { OtherService };
