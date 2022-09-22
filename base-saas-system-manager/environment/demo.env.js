module.exports = {
  URL: {
    //SERVER: JSON.stringify("http://192.168.3.2:22301")
    SERVER: JSON.stringify("http://192.168.3.2:9000") //测试

  },
  RABBITMQ: {
    SOCKET: JSON.stringify("ws://192.168.3.2:15674/ws"),
    VHOST: JSON.stringify("2.2.3"),
    USERNAME: JSON.stringify("2.2.3"),
    PASSWORD: JSON.stringify("2.2.3"),
    EXCHANGE: JSON.stringify("/exchange/oss")
  }
}
