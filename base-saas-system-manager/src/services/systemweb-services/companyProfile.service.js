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
*systemweb 合同管理 service
*/
import { systemwebService } from '~/config/server';
import { NetService } from '~/utils/net.service';
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service";
var companyProfile = /** @class */ (function (_super) {
    __extends(companyProfile, _super);
    function companyProfile() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
     * 保存
     */
    companyProfile.prototype.saveCompanyProfile = function (content) {
        return this.netService.send({
            server: systemwebService.syswebCompanyProfileController.saveCompanyProfile,
            data: { content: content },
            loading: true
        });
    };
    /**
      * 获取
      */
    companyProfile.prototype.getCompanyProfile = function (_a) {
        return this.netService.send({
            server: systemwebService.syswebCompanyProfileController.getCompanyProfile,
            data: {},
            loading: true
        });
    };
    __decorate([
        Inject(NetService)
    ], companyProfile.prototype, "netService", void 0);
    __decorate([
        Debounce()
    ], companyProfile.prototype, "saveCompanyProfile", null);
    __decorate([
        Debounce()
    ], companyProfile.prototype, "getCompanyProfile", null);
    return companyProfile;
}(Service));
export { companyProfile };
