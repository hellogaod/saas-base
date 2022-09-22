module.exports = {
  URL: {
    // SERVER: JSON.stringify("http://106.15.121.231:9000")
    SERVER: JSON.stringify("http://36.33.216.230:9000")
  },
  RABBITMQ: {
    SOCKET: JSON.stringify("ws://106.15.121.231:15674/ws"),
    VHOST: JSON.stringify("reminder"),
    USERNAME: JSON.stringify("zzlReminder"),
    PASSWORD: JSON.stringify("zzlEPan357!"),
    EXCHANGE: JSON.stringify("/exchange/oss")
  }
}
