var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = y[op[0] & 2 ? "return" : op[0] ? "throw" : "next"]) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [0, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
import { dashboardTimeType, monthList, weekList } from "~/config/enum.config";
import moment from "moment";
import appConfig from "~/config/app.config";
/**
 * 公共函数
 */
var CommonService = /** @class */ (function () {
    function CommonService() {
    }
    /**
     * 获取日期列表
     */
    CommonService.getDateList = function (date, type) {
        var result = [];
        switch (type) {
            case dashboardTimeType.year:
                result = monthList;
                break;
            case dashboardTimeType.month:
                var days = moment(date).daysInMonth();
                for (var i = 1; i <= days; i++) {
                    result.push(i.toString().padStart(2, "0") + "\u65E5");
                }
                break;
            case dashboardTimeType.week:
                result = weekList;
                break;
            default:
                break;
        }
        return result;
    };
    /**
     * 返回此时系统的年月日数组
     */
    CommonService.getDate = function () {
        var myDate = new Date();
        return [myDate.getFullYear(), myDate.getMonth() + 1, myDate.getDate()];
    };
    /**
     * 获取以某时间偏移之后的日期数组
     * @param offset 偏移量 (天)
     * @param currentDate 基准日期,不传则为当前日期
     */
    CommonService.getDateArrayOnOffset = function (offset, currentDate) {
        if (offset === void 0) { offset = 0; }
        currentDate = currentDate || new Date();
        var beforeDate = moment(currentDate).add('days', offset).toDate();
        return [beforeDate, currentDate];
    };
    CommonService.base64ConvertBlob = function (dataURI, mimeString) {
        var byteString = atob(dataURI.split(",")[1]);
        if (!mimeString) {
            mimeString = dataURI
                .split(",")[0]
                .split(":")[1]
                .split(";")[0];
        }
        else {
            mimeString =
                mimeString + "/" + dataURI
                    .split(",")[0]
                    .split(":")[1]
                    .split(";")[0]
                    .split("/")[1];
        }
        var ab = new ArrayBuffer(byteString.length);
        var ia = new Uint8Array(ab);
        for (var i = 0; i < byteString.length; i++) {
            ia[i] = byteString.charCodeAt(i);
        }
        return new Blob([ab], { type: mimeString });
    };
    /**
     * 下载文件
     */
    CommonService.downloadFile = function (url, filename) {
        if (filename === void 0) { filename = ''; }
        console.log(url);
        var a = document.createElement('a');
        a.href = url;
        a.setAttribute("download", "");
        a.click();
    };
    /**
     * 批量下载文件
     * @param pathList 需要下载的文件列表，需要包含 url,filename
     */
    CommonService.downloadAll = function (pathList) {
        var _this = this;
        var download = function () { return __awaiter(_this, void 0, void 0, function () {
            var _loop_1, index;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        _loop_1 = function (index) {
                            var v;
                            return __generator(this, function (_a) {
                                switch (_a.label) {
                                    case 0:
                                        v = pathList[index];
                                        return [4 /*yield*/, new Promise(function (reslove) {
                                                CommonService.downloadFile(v, '');
                                                setTimeout(function () {
                                                    reslove();
                                                }, 100);
                                            })];
                                    case 1:
                                        _a.sent();
                                        return [2 /*return*/];
                                }
                            });
                        };
                        index = 0;
                        _a.label = 1;
                    case 1:
                        if (!(index < pathList.length)) return [3 /*break*/, 4];
                        return [5 /*yield**/, _loop_1(index)];
                    case 2:
                        _a.sent();
                        _a.label = 3;
                    case 3:
                        index++;
                        return [3 /*break*/, 1];
                    case 4: return [2 /*return*/];
                }
            });
        }); };
        download();
    };
    /**
     * 下载文件
     */
    CommonService.downloadImage = function (url, filename) {
        if (filename === void 0) { filename = ''; }
        return new Promise(function (reslove, reject) {
            var img = new Image();
            img.onload = function () {
                // 创建接受image的canvas
                var canvas = document.createElement("canvas");
                canvas.width = img.naturalWidth;
                canvas.height = img.naturalHeight;
                // 获取canvas
                var ctx = canvas.getContext("2d");
                ctx.drawImage(img, 0, 0);
                // 得到图片的base64编码数据
                var base64 = canvas.toDataURL("application/png");
                var blob = CommonService.base64ConvertBlob(base64, "application");
                var aTag = document.createElement("a");
                var objectURL = URL.createObjectURL(blob);
                aTag.href = objectURL;
                aTag.click();
                URL.revokeObjectURL(objectURL);
            };
            var appUrl = appConfig.url;
            if (appUrl.image) {
                img.setAttribute("crossOrigin", appUrl.image);
            }
            img.src = url;
        });
    };
    /**
     * 浏览器类型判断
     */
    CommonService._mime = function (option, value) {
        var mimeTypes = navigator.mimeTypes;
        for (var mt in mimeTypes) {
            if (mimeTypes[mt][option] === value) {
                return true;
            }
        }
        return false;
    };
    CommonService.getBrowserType = function () {
        var ua = navigator.userAgent.toLowerCase();
        var is360 = CommonService._mime('type', 'application/vnd.chromium.remoting-viewer');
        var isOpera = ua.indexOf("Opera") > -1;
        if (isOpera) {
            return 'opera';
        }
        else if (ua.indexOf("compatible") > -1 && ua.indexOf("MSIE") > -1 && !isOpera) {
            return 'ie';
        }
        else if (ua.indexOf('firefox') !== -1) {
            return 'firefox';
        }
        else if (ua.indexOf('safari') !== -1 && ua.indexOf('Version') !== -1) {
            return 'safari';
        }
        else if (ua.indexOf('chrome') > 1 && is360) {
            return '360';
        }
        else if (ua.indexOf('chrome') > 1) {
            return 'chrome';
        }
    };
    /**
     * 生成树型结构数据
     * @param sourceList 包含id 和 pid 的线性数据
     * @param options 数据项配置 配置 keyName: 主键名称,parentKeyName: 父级键名称
     */
    CommonService.generateTreeData = function (sourceList, options) {
        if (!sourceList) {
            return [];
        }
        // key
        var keyName = options ? options.keyName : 'id';
        // parentKey
        var parentKeyName = options ? options.parentKeyName : 'pid';
        /**
         * 用当前节点去生成他的children节点
         * @param any node
         */
        var fun = function (node) {
            // 用找到的孩子节点去递归查找孙子节点
            var children = sourceList.filter(function (x) { return x[parentKeyName] === node[keyName]; }).map(fun);
            // 如果当前节点有孩子节点
            if (children && children.length) {
                // 就返回当包含孩子节点的对象
                return Object.assign({}, node, { children: children });
            }
            else {
                // 否则就返回当前对象
                return node;
            }
        };
        var rootList = [];
        // 遍历列表中的每一行数据
        sourceList.forEach(function (current) {
            // 如果当前行数据不包含parentKey 那么就当作root节点之一
            if (!current[parentKeyName]) {
                return true;
            }
            // 用当前指定的数据去判断，列表中是否含有他的子节点数据
            var result = sourceList.find(function (item) { return item[keyName] === current[parentKeyName]; });
            // 如果没有找到子节点，当前这条数据就是根节点数据之一
            if (!result)
                rootList.push(current);
        });
        return rootList.map(fun);
    };
    /**
     * 字符串左补零
     */
    CommonService.padLeft = function (num, length, ch) {
        if (ch === void 0) { ch = "0"; }
        var str = (num || "").toString();
        for (var index = str.length; index < length; index++) {
            str = ch + str;
        }
        return str;
    };
    /**
     * 字符串左补零
     */
    CommonService.padRight = function (num, length, ch) {
        if (ch === void 0) { ch = "0"; }
        var str = (num || "").toString();
        for (var index = str.length; index < length; index++) {
            str += "0";
        }
        return str;
    };
    return CommonService;
}());
export { CommonService };
