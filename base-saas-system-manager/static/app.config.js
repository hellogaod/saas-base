(window || global).appConfig = {
  enable: false,
  config: {
    url: {
      server: "http://117.36.75.166:50002", // 服务端地址
      attach: "http://116.236.220.210:8081" // 附件服务地址
    },
    rabbitmq: {
      socket: "ws://117.36.75.166:5014/ws",
      vhost: "zzl",
      username: "zzl",
      password: "zzl",
      exchange: "/exchange/oss"
    }
  }
}
