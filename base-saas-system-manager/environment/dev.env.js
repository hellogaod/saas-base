module.exports = {
  URL: {
   // SERVER: JSON.stringify("http://192.168.3.5:32774")
    SERVER: JSON.stringify("http://192.168.102.19:9000")  //高强服务器
    // SERVER: JSON.stringify("http://36.33.216.230:9000")

    // SERVER: JSON.stringify("http://36.33.216.230:1000")  //測試
  },
  RABBITMQ: {
    SOCKET: JSON.stringify("ws://192.168.3.2:15674/ws"),
    VHOST: JSON.stringify("server"),
    USERNAME: JSON.stringify("server"),
    PASSWORD: JSON.stringify("server"),
    EXCHANGE: JSON.stringify("/exchange/oss")
  }
}
