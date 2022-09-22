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
*systemweb 日志管理 service
*/
import { systemwebService } from '~/config/server';
import { NetService } from '~/utils/net.service';
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service";
var WeblogService = /** @class */ (function (_super) {
    __extends(WeblogService, _super);
    function WeblogService() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
     * 系统日志列表
     */
    WeblogService.prototype.getLogList = function (data, page) {
        return this.netService.send({
            server: systemwebService.syswebLogController.getLogList,
            data: data,
            page: page,
            loading: true
        });
    };
    /**
   * 系统日志查看
   */
    WeblogService.prototype.getErrorLogById = function (id) {
        return this.netService.send({
            server: systemwebService.syswebLogController.getErrorLogById,
            data: { id: id },
            loading: true
        });
    };
    __decorate([
        Inject(NetService)
    ], WeblogService.prototype, "netService", void 0);
    __decorate([
        Debounce()
    ], WeblogService.prototype, "getLogList", null);
    __decorate([
        Debounce()
    ], WeblogService.prototype, "getErrorLogById", null);
    return WeblogService;
}(Service));
export { WeblogService };
