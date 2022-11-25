import {requestType, commonServiceName} from '~/server/enum.config'
/*
* 后台服务名称
*/
const CONTROLLER = 'file'

export default {
  /**
   * 上传文件
   */
  upload: {
    service: commonServiceName,
    controller: CONTROLLER,
    action: 'upload',
    type: requestType.Post
  }
}
