const customConfig = require("app-config")
const env: any = process.env

let appConfig = {
  name: 'saas基础平台',
  version: 'V1.0',
  url: {
    server: env.URL.SERVER,
    attach: env.URL.ATTACH
  },
  rabbitmq: {
    socket: env.RABBITMQ.SOCKET,
    vhost: env.RABBITMQ.VHOST,
    username: env.RABBITMQ.USERNAME,
    password: env.RABBITMQ.PASSWORD,
    exchange: env.RABBITMQ.EXCHANGE
  },
  timeout: 30000,
  debug: env.ENV === 'dev',
  // 身份证号和手机号脱敏开关
  encryptSwitch: true
}

// 如果存在自定义配置则启用自定义配置
if (customConfig && customConfig.enable) {
  appConfig = Object.assign(appConfig, customConfig.config)
}

export default appConfig
