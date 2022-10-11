import {requestType, servicePreName} from '~/server/enum.config'
/*
* 后台服务名称
*/
const SERVICE = servicePreName +'system-web' //本地
const CONTROLLER = 'rich'

export default {
  /**
   * 上传文件
   */
  upload: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'upload',
    type: requestType.Post
  },
  /**
   * 上传压缩文件，后台进行解压缩
   */
  unZipCaseFile: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'unZipCaseFile',
    type: requestType.Post
  },
  /**
   * 上传文件(新)
   */
  uploadFileGrid: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'uploadFileGrid',
    type: requestType.Post
  },
  /**
   * 下载文件
   */
  file: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'file',
    type: requestType.Get
  },
  /**
   * 文件在线查看
   */
  view: {
    service: SERVICE,
    controller: CONTROLLER,
    action: 'view',
    type: requestType.Get
  }
}
