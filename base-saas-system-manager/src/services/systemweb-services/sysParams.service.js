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
*systemweb systemParams系统参数配置 service
*/
import { systemwebService } from '~/config/server';
import { NetService } from '~/utils/net.service';
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service";
var webSystemParamsService = /** @class */ (function (_super) {
    __extends(webSystemParamsService, _super);
    function webSystemParamsService() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
     * 查询数据
     */
    webSystemParamsService.prototype.getCompnaySysPara = function () {
        return this.netService.send({
            server: systemwebService.syswebParamsController.getCompnaySysPara,
            data: {},
            loading: true
        });
    };
    /**
     * 保存
     */
    webSystemParamsService.prototype.saveSysPara = function (data) {
        return this.netService.send({
            server: systemwebService.syswebParamsController.saveSysPara,
            data: data,
            loading: true
        });
    };
    __decorate([
        Inject(NetService)
    ], webSystemParamsService.prototype, "netService", void 0);
    __decorate([
        Debounce()
    ], webSystemParamsService.prototype, "getCompnaySysPara", null);
    __decorate([
        Debounce()
    ], webSystemParamsService.prototype, "saveSysPara", null);
    return webSystemParamsService;
}(Service));
export { webSystemParamsService };
