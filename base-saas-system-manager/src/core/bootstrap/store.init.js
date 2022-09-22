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
// const creatDataDictService = () => import('~/services/business-services/data-dict.service')
// const createLoginService = () => import('~/services/business-services/login.service')
// const createCompanyService = () => import('~/services/business-services/company.service')
// import { MessageService } from '~/utils/message.service'
export default function (_a) {
    var store = _a.store, router = _a.router;
    return __awaiter(this, void 0, void 0, function () {
        /**
         * 检测用户数据
         */
        function updateUserData() {
            return __awaiter(this, void 0, void 0, function () {
                return __generator(this, function (_a) {
                    return [2 /*return*/, new Promise(function (reslove, reject) {
                            // 登录页面不更新用户数据
                            if (!store.state.userToken && window.location.pathname == "/") {
                                reslove();
                                return;
                            }
                            // 不存在token不更新用户数据
                            if (!store.state.userToken && window.location.pathname != "/") {
                                store.commit("updateTokenExpire", true);
                                reject();
                                return;
                            }
                            // 更新用户数据
                            // loginService.getUserByToken().subscribe(({ user }) => {
                            //   // 更新用户控件资源
                            //   store.dispatch('updateUserLoginData', { user })
                            //   reslove()
                            // }, () => {
                            //   // 用户数据获取异常
                            //   store.commit("updateTokenExpire", true)
                            //   reject()
                            // })
                        })];
                });
            });
        }
        /**
         * 检查数据字典
         * @param reslove
         */
        function updateDictData() {
            return new Promise(function (reslove, reject) {
                if (!store.state.dictDataHash) {
                    return getDictData(reslove, reject);
                }
                // dataDictService.getDictHash().subscribe(({ dataDictHashCode }) => {
                //   if (store.state.dictDataHash! == store.state.dictDataHash) {
                //     getDictData(reslove, reject)
                //   } else {
                //     reslove()
                //   }
                // }, () => {
                //   reject()
                // })
            });
        }
        /**
         * 获取数据字典
         * @param reslove
         */
        function getDictData(reslove, reject) {
            // return dataDictService.getDictData().subscribe((data) => {
            //   store.commit('updateDictData', data)
            //   reslove()
            // }, () => {
            //   reject()
            // })
        }
        /**
         * 更新业务数据
         * 更新公司列表
         * 更新委托方列表
         * 更新部门列表
         */
        function updateBusinessData() {
            var _this = this;
            return new Promise(function (reslove, reject) { return __awaiter(_this, void 0, void 0, function () {
                return __generator(this, function (_a) {
                    switch (_a.label) {
                        case 0: 
                        // 全局业务数据 - 与用户无关
                        return [4 /*yield*/, store.dispatch('getCompanyList')];
                        case 1:
                            // 全局业务数据 - 与用户无关
                            _a.sent();
                            reslove();
                            return [2 /*return*/];
                    }
                });
            }); });
        }
        var flag;
        return __generator(this, function (_b) {
            switch (_b.label) {
                case 0: return [4 /*yield*/, Promise.all([]).then(function () {
                        return true;
                    }).catch(function (ex) {
                        // 基础数据初始化失败
                        console.log('基础数据初始化失败');
                        return false;
                    })
                    // 初始化业务数据
                ];
                case 1:
                    flag = _b.sent();
                    // 初始化业务数据
                    if (flag) {
                        // await updateBusinessData()
                    }
                    store.commit('ready', true);
                    return [2 /*return*/];
            }
        });
    });
}
