import axios from 'axios';
import Qs from 'qs';
import app from '~/config/app.config';
import store from '~/store';
import { Observable } from "rxjs";
import { StorageService } from '~/utils/storage.service';
import { SortService } from '~/utils/sort.service';
import { Message, MessageBox, Loading } from 'element-ui';
import router from "~/router";
var getType = ['GET', 'DELETE']; // 使用GET请求类型
var errorMessage = {
    systemError: '系统异常，请稍后重试.',
    serverError: "服务端异常,请稍后重试.",
    netError: '服务端连接异常，请检查服务端状态.'
};
var NetService = /** @class */ (function () {
    function NetService() {
        this.loading = {};
        this.axiosInstance = axios.create({
            baseURL: app.url.server,
            timeout: app.timeout,
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        });
    }
    NetService.generateRequestUrl = function (_a, append, sort) {
        var service = _a.service, controller = _a.controller, action = _a.action, url = _a.url;
        if (append === void 0) { append = []; }
        // 自定义url优先级最高
        if (url)
            return url;
        // 进行url拼接
        if (controller) {
            var targetUrl = [
                service,
                'api',
                controller,
                action
            ].concat(append).filter(function (x) { return x; }).join('/');
            // 开启排序
            if (sort) {
                targetUrl += "?" + SortService.stringify(sort);
            }
            return targetUrl;
        }
        else {
            throw new Error('server配置异常,请检查对应server配置');
        }
    };
    /**
     * 生成头部信息
     */
    // private generateRequestHeader(headers): Object {
    //   let token = StorageService.getStorage('session').getItem('userToken') || ''
    //   if (token) {
    //     return Object.assign({
    //       'X-UserToken': token
    //     }, headers)
    //   } else {
    //     return headers || {}
    //   }
    // }
    NetService.prototype.generateRequestHeader = function (headers) {
        var token = StorageService.getStorage('session').getItem('userToken') || '';
        var tempHeaders = headers;
        if (token) {
            tempHeaders = Object.assign({
                'X-UserToken': token
            }, headers);
        }
        return Object.assign({
            'Accept': '*/*',
            'Access-Control-Allow-Origin': '*'
        }, tempHeaders);
    };
    /**
     * 过滤空数据
     * @param data
     */
    NetService.prototype.filterEmptyData = function (data) {
        Object.entries(data)
            .filter(function (_a) {
            var key = _a[0], value = _a[1];
            // 过滤空字符串
            if (value === undefined || value === "") {
                return true;
            }
            // 过滤空数组
            if (value instanceof Array && (value.length === 0 || value.every(function (x) { return x === ''; }))) {
                return true;
            }
        })
            .forEach(function (_a) {
            var key = _a[0], value = _a[1];
            delete data[key];
        });
        return data;
    };
    /**
     * 异常处理
     */
    NetService.prototype.catchHandle = function (options, observer) {
        var _this = this;
        return function (ex) {
            if (options.loading) {
                setTimeout(function () {
                    _this.loading.close();
                }, 500);
            }
            var error = {};
            try {
                console.log(ex);
                if (ex) {
                    // 错误信息
                    error.msg = "";
                    error.params = "";
                    // error.data = ex.response.data
                }
                else {
                    return false;
                }
            }
            catch (err) {
                console.log(err);
            }
            // 逻辑异常检测
            if (!ex.response && !ex.request) {
                error.msg = ex.message;
                error.params = ex.stack;
                console.log(ex.stack);
                Message.error(errorMessage.systemError);
                return Observable.empty();
            }
            // 网络异常检测
            if (!ex.response && ex.request) {
                error.msg = errorMessage.netError;
            }
            if (ex.response) {
                error.msg = errorMessage.netError;
                // 错误类型检测
                switch (ex.response.status) {
                    case 400: {
                        error.msg = decodeURIComponent(ex.response.headers['x-saas-server-alert'] || errorMessage.serverError);
                        error.params = ex.response.headers['x-saas-server-params'] || "";
                        break;
                    }
                    case 401: {
                        if (!store.state.tokenExpire) {
                            store.commit("updateTokenExpire", true);
                        }
                        else {
                            break;
                        }
                        if (!options.skipAuth) {
                            alert(222);
                            MessageBox.alert('用户登录过期,请重新登录', '提示', {
                                confirmButtonText: '确定',
                                callback: function () {
                                    router.push("/");
                                    if (sessionStorage.getItem("loginType") === "sys") {
                                        router.push("/system-web");
                                    }
                                    else {
                                        router.push("/manage");
                                    }
                                }
                            });
                            return;
                        }
                    }
                    case 403: {
                        if (!store.state.tokenExpire) {
                            store.commit("updateTokenExpire", true);
                        }
                        else {
                            break;
                        }
                    }
                    case 500: {
                        error.msg = errorMessage.serverError;
                        break;
                    }
                }
            }
            observer.error(error);
        };
    };
    /**
     * 发送网络请求信息
     * @param param0
     */
    NetService.prototype.send = function (options) {
        var _this = this;
        var jsonData = {};
        // if(!options.hasOwnProperty('data')) options.data = {}
        // if(JSON.stringify(options.data).indexOf("%")!=-1){
        //   jsonData=JSON.parse(JSON.stringify(options.data).replace("%","%\/"));
        // }else if(JSON.stringify(options.data).indexOf("_")!=-1){
        //   jsonData=JSON.parse(JSON.stringify(options.data).replace("_","_\/"));
        // }else{
        jsonData = options.data;
        // }
        var data = Object.assign({}, jsonData);
        var postData;
        var getData;
        // let postData = options.data
        // let getData = options.params
        var url = NetService.generateRequestUrl(options.server, options.append, options.sort);
        var method = options.server.type || 'GET';
        var headers = this.generateRequestHeader(options.headers);
        // 分页检测
        if (options.page) {
            data = Object.assign(data, options.page.getConfig());
        }
        // 判断参数类型
        getType.indexOf(method) > -1 ? (getData = this.filterEmptyData(data)) : (postData = data);
        // options.loading=false
        if (options.loading) {
            this.loading = Loading.service({
                fullscreen: true,
                lock: true,
                text: '加载中...'
            });
        }
        // 创建待观察对象
        var observable = Observable.create(function (observer) {
            // 添加响应拦截器
            _this.axiosInstance.interceptors.response.use(function (res) {
                // res 响应结果
                // 响应拦成功拦截
                if (res.headers != undefined) {
                    var headers = res.headers;
                    var msg = decodeURIComponent(headers['x-saas-server-alert']);
                    if (headers['x-saas-server-alert'] != undefined && ((msg.indexOf("成功") == -1 && msg.indexOf("success") == -1))) {
                        // Message.error(msg)
                        console.log("msg：", msg);
                        var data = {
                            exceptionStackMsg: msg
                        };
                        res.data = data;
                        console.log("响应数据", res);
                    }
                }
                return res;
            }, function (err) {
                // 响应拦失败拦截
                return observer.error(err);
            });
            _this.axiosInstance.request({
                method: method,
                url: url,
                headers: headers,
                data: postData,
                params: getData,
                paramsSerializer: function (params) {
                    return Qs.stringify(params, {
                        arrayFormat: 'repeat',
                        skipNulls: true,
                        allowDots: true
                    });
                }
            }).then(function (_a) {
                var data = _a.data;
                // 分页数据处理
                if (options.page && data) {
                    options.page.update(data.responseBody === undefined ? data : data.responseBody);
                    data = data;
                }
                // 关闭等待
                if (options.loading) {
                    setTimeout(function () {
                        _this.loading.close();
                    }, 500);
                }
                if (data.exceptionStackMsg != undefined) {
                    // let error: any = {}
                    // error.msg = data.exceptionStackMsg
                    Message.error(data.exceptionStackMsg);
                    // observer.error(error)
                }
                else {
                    observer.next(data.responseBody === undefined ? data : data.responseBody);
                }
            }).catch(_this.catchHandle(options, observer));
        });
        return observable;
    };
    return NetService;
}());
export { NetService };
