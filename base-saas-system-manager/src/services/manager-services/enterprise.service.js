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
*manage 系统企业管理 service
*/
import { managerService } from '~/config/server';
import { NetService } from '~/utils/net.service';
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service";
var EnterpiseService = /** @class */ (function (_super) {
    __extends(EnterpiseService, _super);
    function EnterpiseService() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
     * 获取企业列表
     */
    EnterpiseService.prototype.getSysEnterpriseList = function (data, page) {
        return this.netService.send({
            server: managerService.enterpriseController.getSysEnterpriseList,
            data: data,
            page: page,
            loading: true
        });
    };
    /**
     * 添加企业信息
     */
    EnterpiseService.prototype.addSysEnterprise = function (data) {
        return this.netService.send({
            server: managerService.enterpriseController.addSysEnterprise,
            data: data,
            loading: true
        });
    };
    /**
     * 根据企业编码查询企业信息
     */
    EnterpiseService.prototype.getEnterpriseByCompanyCode = function (companyCode) {
        return this.netService.send({
            server: managerService.enterpriseController.getEnterpriseByCompanyCode,
            data: { companyCode: companyCode },
            loading: true
        });
    };
    /**
     * 修改企业关联三方列表
     */
    EnterpiseService.prototype.updateCompanyOtherConf = function (data) {
        return this.netService.send({
            server: managerService.enterpriseController.updateCompanyOtherConf,
            data: {
                data: data
            },
            loading: true
        });
    };
    /**
    * 修改状态
    */
    EnterpiseService.prototype.updateStatus = function (_a) {
        var status = _a.status, companyCode = _a.companyCode;
        return this.netService.send({
            server: managerService.enterpriseController.updateStatus,
            data: {
                status: status,
                companyCode: companyCode
            },
            loading: true
        });
    };
    /**
     * 修改企业信息
     */
    EnterpiseService.prototype.updateSysEnterprise = function (data) {
        return this.netService.send({
            server: managerService.enterpriseController.updateSysEnterprise,
            data: data,
            loading: true
        });
    };
    __decorate([
        Inject(NetService)
    ], EnterpiseService.prototype, "netService", void 0);
    __decorate([
        Debounce()
    ], EnterpiseService.prototype, "getSysEnterpriseList", null);
    return EnterpiseService;
}(Service));
export { EnterpiseService };
