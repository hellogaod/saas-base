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
import { fileService } from '~/config/server';
import { NetService } from '~/utils/net.service';
import { Inject } from "~/core/decorator";
import { Service } from '~/core/service';
var FileUploadService = /** @class */ (function (_super) {
    __extends(FileUploadService, _super);
    function FileUploadService() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
     * 文件在线查看
     */
    FileUploadService.prototype.view = function () {
        return this.netService.send({
            server: fileService.fileUploadController.view
        });
    };
    /**
     * 上传压缩文件，后台进行解压缩
     */
    FileUploadService.prototype.unZipCaseFile = function (data) {
        return this.netService.send({
            server: fileService.fileUploadController.unZipCaseFile,
            data: data
        });
    };
    __decorate([
        Inject(NetService)
    ], FileUploadService.prototype, "netService", void 0);
    return FileUploadService;
}(Service));
export { FileUploadService };
