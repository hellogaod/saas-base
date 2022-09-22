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
*systemweb dict字典管理 service
*/
import { systemwebService } from '~/config/server';
import { NetService } from '~/utils/net.service';
import { Inject, Debounce } from "~/core/decorator";
import { Service } from "~/core/service";
var webDictService = /** @class */ (function (_super) {
    __extends(webDictService, _super);
    function webDictService() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
     * 查询大类数据列表
     */
    webDictService.prototype.getDictItemList = function (data, page) {
        return this.netService.send({
            server: systemwebService.syswebDictController.getDictItemList,
            data: data,
            page: page,
            loading: true
        });
    };
    /**
     * 根据id查询字典明细
     */
    webDictService.prototype.getDictDetailById = function (id) {
        return this.netService.send({
            server: systemwebService.syswebDictController.getDictDetailById,
            data: { id: id },
            loading: true
        });
    };
    /**
     * 查询明细数据列表
     */
    webDictService.prototype.getDictDetailList = function (data, page) {
        return this.netService.send({
            server: systemwebService.syswebDictController.getDictDetailList,
            data: data,
            page: page,
            loading: true
        });
    };
    /**
     * 根据id获取字典大类信息
     */
    webDictService.prototype.getDictItemById = function (id) {
        return this.netService.send({
            server: systemwebService.syswebDictController.getDictItemById,
            data: { id: id },
            loading: true
        });
    };
    /**
     * 保存字典明细
     */
    webDictService.prototype.saveDictDetail = function (data) {
        return this.netService.send({
            server: systemwebService.syswebDictController.saveDictDetail,
            data: data,
            loading: true
        });
    };
    /**
     * 保存字典大类
     */
    webDictService.prototype.saveDictItem = function (data) {
        return this.netService.send({
            server: systemwebService.syswebDictController.saveDictItem,
            data: data,
            loading: true
        });
    };
    /**
    * 修改字典明细
    */
    webDictService.prototype.updateDictDetail = function (data) {
        return this.netService.send({
            server: systemwebService.syswebDictController.updateDictDetail,
            data: data,
            loading: true
        });
    };
    /**
     * 修改字典明细状态
     */
    webDictService.prototype.updateDictDetailStatus = function (_a) {
        var status = _a.status, id = _a.id;
        return this.netService.send({
            server: systemwebService.syswebDictController.updateDictDetailStatus,
            data: {
                status: status,
                id: id
            },
            loading: true
        });
    };
    /**
     * 修改字典大类
     */
    webDictService.prototype.updateDictItem = function (data) {
        return this.netService.send({
            server: systemwebService.syswebDictController.updateDictItem,
            data: data,
            loading: true
        });
    };
    /**
     * 修改字典大类状态
     */
    webDictService.prototype.updateDictItemStatus = function (_a) {
        var status = _a.status, id = _a.id;
        return this.netService.send({
            server: systemwebService.syswebDictController.updateDictItemStatus,
            data: {
                status: status,
                id: id
            },
            loading: true
        });
    };
    /**
     * 根据字典code 查询
   /**
     * 根据字典编码查询字典明细
     */
    // @Debounce()
    webDictService.prototype.getDictDetailByItemCode = function (data) {
        return this.netService.send({
            server: systemwebService.syswebDictController.getDictDetailByItemCode,
            data: data,
            loading: true
        });
    };
    /**
    * 根据字典编码查询字典明细（多个code’）
    */
    // @Debounce()
    webDictService.prototype.getDicDetailByItemList = function (data) {
        return this.netService.send({
            server: systemwebService.syswebDictController.getDicDetailByItemList,
            data: data,
            loading: true
        });
    };
    __decorate([
        Inject(NetService)
    ], webDictService.prototype, "netService", void 0);
    __decorate([
        Debounce()
    ], webDictService.prototype, "getDictItemList", null);
    __decorate([
        Debounce()
    ], webDictService.prototype, "getDictDetailById", null);
    __decorate([
        Debounce()
    ], webDictService.prototype, "getDictDetailList", null);
    __decorate([
        Debounce()
    ], webDictService.prototype, "getDictItemById", null);
    __decorate([
        Debounce()
    ], webDictService.prototype, "saveDictDetail", null);
    __decorate([
        Debounce()
    ], webDictService.prototype, "saveDictItem", null);
    __decorate([
        Debounce()
    ], webDictService.prototype, "updateDictDetail", null);
    __decorate([
        Debounce()
    ], webDictService.prototype, "updateDictDetailStatus", null);
    __decorate([
        Debounce()
    ], webDictService.prototype, "updateDictItem", null);
    __decorate([
        Debounce()
    ], webDictService.prototype, "updateDictItemStatus", null);
    return webDictService;
}(Service));
export { webDictService };
