const customConfig = require("app-config")
const env: any = process.env

let appConfig = {
  name: 'saas基础平台',
  version: 'V1.0',
  url: {
    server: env.URL.SERVER,
  },

  timeout: 30000,
  debug: env.ENV === 'dev',

}


export default appConfig
