import axios from 'axios'
import Qs from 'qs'
import AppConfig from '~/config/app.config'
import store from '~/store'
import {Observable} from "rxjs";
import {StorageUtil} from '~/utils/storage.util'
import {SortUtil} from '~/utils/sort.util'
import {Message, MessageBox, Loading} from 'element-ui'
import router from "~/router"
import {error} from "util";

const getType = ['GET', 'DELETE'] // 使用GET请求类型

export class NetService {
  // 使用GET请求类型
  private axiosInstance
  private loading: any = {};

  constructor() {

    this.axiosInstance = axios.create({
      baseURL: AppConfig.url.server,
      timeout: AppConfig.timeout,
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      }
    })
  }


  //请求url拼接 "api" 字符串
  public static generateRequestUrl({service, controller, action, url}: { service: string, controller: string, action: string, url?: string }, append = [], sort?): String {
    // 自定义url优先级最高
    if (url) return url
    // 进行url拼接
    if (controller) {
      let targetUrl = [
        service,
        'api',
        controller,
        action,
        ...append].filter(x => x).join('/')

      // 开启排序
      if (sort) {
        targetUrl += `?${SortUtil.stringify(sort)}`
      }

      return targetUrl
    } else {
      throw new Error('server配置异常,请检查对应server配置')
    }
  }

  //请求header拼接X-UserGToken
  private generateRequestHeader(headers): Object {
    let token = StorageUtil.getStorage('session').getItem('userToken') || ''
    let tempHeaders = headers
    if (token) {
      tempHeaders = Object.assign({
        'X-UserToken': token
      }, headers)
    }
    return Object.assign({
      'Accept': '*/*',
      'Access-Control-Allow-Origin': '*'
    }, tempHeaders)

  }

  /**
   * 过滤空数据
   * @param data
   */
  private filterEmptyData(data) {
    Object.entries(data)
      .filter(([key, value]) => {
        // 过滤空字符串
        if (value === undefined || value === "") {
          return true
        }

        // 过滤空数组
        if (value instanceof Array && (value.length === 0 || value.every(x => x === ''))) {
          return true
        }
      })
      .forEach(([key, value]) => {
        delete data[key]
      });
    return data
  }

  /**
   * 发送网络请求信息
   * @param param0
   */
  public send(options: any): Observable<any> {
    let jsonData = {};
    jsonData = options.data
    let data = Object.assign({}, jsonData)
    let postData
    let getData
    // let postData = options.data
    // let getData = options.params

    let url = NetService.generateRequestUrl(options.server, options.append, options.sort)
    let method = options.server.type || 'GET'
    let headers = this.generateRequestHeader(options.headers)

    // 分页检测
    if (options.page) {
      data = Object.assign(data, options.page.getConfig())
    }

    // 判断参数类型
    getType.indexOf(method) > -1 ? (getData = this.filterEmptyData(data)) : (postData = data)
    // options.loading=false
    if (options.loading) {
      this.loading = Loading.service({
        fullscreen: true,
        lock: true,
        text: '加载中...'
      })
    }

    // 创建待观察对象
    var observable = Observable.create((observer) => {

      // 添加响应拦截器
      this.axiosInstance.interceptors.response.use(
        res => {
          return res
        },
        error => {//请求失败处理
          return error.response
        }
      )

      //请求成功处理
      this.axiosInstance.request({
        method,
        url,
        headers,
        data: postData,
        params: getData,
        paramsSerializer: (params) =>
          Qs.stringify(params, {
            arrayFormat: 'repeat',
            skipNulls: true,
            allowDots: true
          })
      }).then((res) => {

        console.log("响应数据" + JSON.stringify(res));

        // 关闭等待
        if (options.loading) {
          setTimeout(() => {
            this.loading.close()
          }, 500);
        }

        if (res.status != 200) {
          let err: any = {}
          let msg = decodeURIComponent(res.headers['saas-error-message'])
          if (msg === undefined) {
            msg = "服务端异常,请稍后重试."
          }
          err.msg = msg;
          observer.error(err)
          return
        }

        let data = res.data

        // 分页数据处理
        if (options.page && data) {
          options.page.update(data)
        }
        let token = res.headers['saas-token'];
        if (token != undefined) {
          data = {token: token, userInfo: data}
        }
        observer.next(data)

      })
    })

    return observable
  }
}
