import { dashboardQueryType, dashboardTimeType, monthList, weekList } from "~/config/enum.config";
import moment from "moment";
import appConfig from "~/config/app.config"
/**
 * 公共函数
 */
export class CommonService {
  /**
   * 获取日期列表
   */
  static getDateList(date, type) {
    let result: Array<any> = [];
    switch (type) {
      case dashboardTimeType.year:
        result = monthList
        break;
      case dashboardTimeType.month:
        let days = moment(date).daysInMonth();
        for (let i = 1; i <= days; i++) {
          result.push(`${i.toString().padStart(2, "0")}日`);
        }
        break;
      case dashboardTimeType.week:
        result = weekList
        break;
      default:
        break;
    }

    return result;
  }
  /**
   * 返回此时系统的年月日数组
   */
  static getDate() {
    let myDate = new Date()
    return [myDate.getFullYear(), myDate.getMonth() + 1, myDate.getDate()]
  }

  /**
   * 获取以某时间偏移之后的日期数组
   * @param offset 偏移量 (天)
   * @param currentDate 基准日期,不传则为当前日期
   */
  static getDateArrayOnOffset(offset: number = 0, currentDate?: Date): Array<Date> {
    currentDate = currentDate || new Date()
    let beforeDate = moment(currentDate).add('days', offset).toDate()
    return [beforeDate, currentDate]
  }

  static base64ConvertBlob(dataURI, mimeString?): Blob {
    var byteString = atob(dataURI.split(",")[1]);

    if (!mimeString) {
      mimeString = dataURI
        .split(",")[0]
        .split(":")[1]
        .split(";")[0];
    } else {
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
  }

  /**
   * 下载文件
   */
  static downloadFile(url, filename: string = '') {
    console.log(url)
    let a = document.createElement('a');
    a.href = url;
    a.setAttribute("download","");
    a.click()
  }
  /**
   * 批量下载文件
   * @param pathList 需要下载的文件列表，需要包含 url,filename
   */
  static downloadAll(pathList) {
    let download = async () => {
      for (let index = 0; index < pathList.length; index++) {
        let v = pathList[index];
        await new Promise((reslove) => {
          CommonService.downloadFile(v, '')
          setTimeout(() => {
            reslove()
          }, 100);
        })

      }
    }
    download()
  }
  /**
   * 下载文件
   */
  static downloadImage(url, filename: string = '') {
    return new Promise((reslove, reject) => {
      let img = new Image();
      img.onload = () => {
        // 创建接受image的canvas
        const canvas = document.createElement("canvas");
        canvas.width = img.naturalWidth;
        canvas.height = img.naturalHeight;
        // 获取canvas
        const ctx: any = canvas.getContext("2d");
        ctx.drawImage(img, 0, 0);
        // 得到图片的base64编码数据
        let base64 = canvas.toDataURL("application/png");
        var blob = CommonService.base64ConvertBlob(base64, "application");
        var aTag = document.createElement("a");

        let objectURL = URL.createObjectURL(blob);
        aTag.href = objectURL;
        aTag.click();
        URL.revokeObjectURL(objectURL);
      };

      let appUrl: any = appConfig.url

      if (appUrl.image) {
        img.setAttribute("crossOrigin", appUrl.image);
      }

      img.src = url
    })
  }

  /**
   * 浏览器类型判断
   */
  static _mime(option, value) {
    var mimeTypes = navigator.mimeTypes
    for (var mt in mimeTypes) {
      if (mimeTypes[mt][option] === value) {
        return true
      }
    }
    return false
  }
  static getBrowserType() {
    var ua = navigator.userAgent.toLowerCase()
    var is360 = CommonService._mime('type', 'application/vnd.chromium.remoting-viewer');
    var isOpera = ua.indexOf("Opera") > -1;
    if (isOpera) {
      return 'opera'
    } else if (ua.indexOf("compatible") > -1 && ua.indexOf("MSIE") > -1 && !isOpera) {
      return 'ie'
    } else if (ua.indexOf('firefox') !== -1) {
      return 'firefox'
    } else if (ua.indexOf('safari') !== -1 && ua.indexOf('Version') !== -1) {
      return 'safari'
    } else if (ua.indexOf('chrome') > 1 && is360) {
      return '360'
    } else if (ua.indexOf('chrome') > 1) {
      return 'chrome'
    }
  }

  /**
   * 生成树型结构数据
   * @param sourceList 包含id 和 pid 的线性数据
   * @param options 数据项配置 配置 keyName: 主键名称,parentKeyName: 父级键名称
   */
  static generateTreeData(sourceList, options?: { keyName: string; parentKeyName: string; }): Array<any> {
    if (!sourceList) {
      return []
    }

    // key
    let keyName = options ? options.keyName : 'id';
    // parentKey
    let parentKeyName = options ? options.parentKeyName : 'pid';

    /**
     * 用当前节点去生成他的children节点
     * @param any node 
     */
    let fun = (node: any) => {
      // 用找到的孩子节点去递归查找孙子节点
      let children = sourceList.filter(x => x[parentKeyName] === node[keyName]).map(fun);

      // 如果当前节点有孩子节点
      if (children && children.length) {
        // 就返回当包含孩子节点的对象
        return Object.assign({}, node, { children })
      } else {
        // 否则就返回当前对象
        return node
      }
    };

    let rootList: Array<any> = [];
    // 遍历列表中的每一行数据
    sourceList.forEach(current => {
      // 如果当前行数据不包含parentKey 那么就当作root节点之一
      if (!current[parentKeyName]) {
        return true;
      }
      // 用当前指定的数据去判断，列表中是否含有他的子节点数据
      let result = sourceList.find(item => item[keyName] === current[parentKeyName])
      // 如果没有找到子节点，当前这条数据就是根节点数据之一
      if (!result) rootList.push(current)

    });

    return rootList.map(fun)
  }

  /**
   * 字符串左补零
   */
  static padLeft(num: number | string, length: number, ch: string = "0"): string {
    let str: string = (num || "").toString();
    for (let index: number = str.length; index < length; index++) {
      str = ch + str;
    }
    return str;
  }

  /**
   * 字符串左补零
   */
  static padRight(num: number | string, length: number, ch: string = "0"): string {
    let str: string = (num || "").toString();
    for (let index: number = str.length; index < length; index++) {
      str += "0";
    }
    return str;
  }
}
