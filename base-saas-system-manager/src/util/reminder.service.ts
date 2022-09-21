import app from '~/config/app.config'
import store from '~/store'
import Stomp from '@stomp/stompjs'
import { Observable } from "rxjs";
import { Notification } from 'element-ui'
import { namespace } from "vuex-class";
import SockJS from 'sockjs-client'
const workspaceModule = namespace("workspace");

export class ReminderService {
  // 消息服务监听列表
  static messageListenerList: Array<any> = []
  // 任务服务监听列表
  static taskListenerList: Array<any> = []
  static instance
  private client
  static notifyList: any[] = []
  /**
   * 初始化65
   */
  constructor() {
    if (app.rabbitmq.socket.startsWith('ws://')) {
      this.client = Stomp.client(app.rabbitmq.socket);
    } else {
      var ws = new SockJS(app.rabbitmq.socket);
      this.client = Stomp.over(ws);
    }

    // this.client.debug = () => { };  // 禁用日志消息
    this.client.connect(
      app.rabbitmq.username,
      app.rabbitmq.password,
      this.onConnectHandle.bind(this),
      this.onErrorHandle.bind(this),
      this.onCloseHandle.bind(this),
      app.rabbitmq.vhost);
  }

  static connect() {
    ReminderService.instance = new ReminderService()
  }

  static disConnect() {

  }

  /**
   * 处理消息open事件
   */
  private onConnectHandle() {
    this.client.subscribe(`${app.rabbitmq.exchange}/${store.state.userData.id}`, this.onReminderHandle.bind(this));
  }

  /**
   * 处理消息message事件
   */
  private onReminderHandle({ body }) {
    console.log(body)
    try {
      let { messageType, data } = JSON.parse(body)
      switch (messageType) {
        case "TaskBox":
          this.onTaskHandle(data)
          break;
        case "Reminder":
          this.onMessageHandle(data)
          break;
        default:
          console.log(`未处理的消息类型:${messageType}`)
          break;
      }
    } catch (ex) {
      console.log(ex.message)
    }
  }

  private onTaskHandle(data) {
    ReminderService.taskListenerList.forEach(x => {
      x.observer.next(data)
    })
  }

  private onMessageHandle(data) {
    ReminderService.messageListenerList.forEach(x => {
      if (x.type instanceof Array != true) {
        x.type = [x.type]
      }

      // 空元素时监听所有事件
      if (!x.type.length) {
        return x.observer.next(data)
      }

      if (x.type.includes(data.type)) {
        return x.observer.next(data)
      }
    })
  }

  /**
   * 处理消息close事件
   */
  private onCloseHandle(...a) {
    console.log('close', a)
  }

  /**
   * 处理消息error事件
   */
  private onErrorHandle(...a) {
    console.log('error', a)
  }

  public static addMessageListener({ type }: { type?: String | Array<string> } = {}) {
    return Observable.create(function (observer) {
      ReminderService.messageListenerList.push({
        type: type || [],
        observer
      })
    })
  }

  public static addTaskListener() {
    return Observable.create(function (observer) {
      ReminderService.taskListenerList.push({
        // type,
        observer
      })
    })
  }

  public static notify(createElement) {
    let notify
    notify = Notification({
      title: '消息提醒',
      duration: 0,
      position: 'bottom-right',
      dangerouslyUseHTMLString: true,
      message: createElement({
        store: workspaceModule,
        props: {
          notify: notify
        }
      })
    })

    ReminderService.notifyList.push(notify)
  }

  public static notifyClean() {
    ReminderService.notifyList.forEach(item => {
      item.close()
    })
  }
}
