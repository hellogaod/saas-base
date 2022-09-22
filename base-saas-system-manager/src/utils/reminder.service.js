import app from '~/config/app.config';
import store from '~/store';
import Stomp from '@stomp/stompjs';
import { Observable } from "rxjs";
import { Notification } from 'element-ui';
import { namespace } from "vuex-class";
import SockJS from 'sockjs-client';
var workspaceModule = namespace("workspace");
var ReminderService = /** @class */ (function () {
    /**
     * 初始化65
     */
    function ReminderService() {
        if (app.rabbitmq.socket.startsWith('ws://')) {
            this.client = Stomp.client(app.rabbitmq.socket);
        }
        else {
            var ws = new SockJS(app.rabbitmq.socket);
            this.client = Stomp.over(ws);
        }
        // this.client.debug = () => { };  // 禁用日志消息
        this.client.connect(app.rabbitmq.username, app.rabbitmq.password, this.onConnectHandle.bind(this), this.onErrorHandle.bind(this), this.onCloseHandle.bind(this), app.rabbitmq.vhost);
    }
    ReminderService.connect = function () {
        ReminderService.instance = new ReminderService();
    };
    ReminderService.disConnect = function () {
    };
    /**
     * 处理消息open事件
     */
    ReminderService.prototype.onConnectHandle = function () {
        this.client.subscribe(app.rabbitmq.exchange + "/" + store.state.userData.id, this.onReminderHandle.bind(this));
    };
    /**
     * 处理消息message事件
     */
    ReminderService.prototype.onReminderHandle = function (_a) {
        var body = _a.body;
        console.log(body);
        try {
            var _b = JSON.parse(body), messageType = _b.messageType, data = _b.data;
            switch (messageType) {
                case "TaskBox":
                    this.onTaskHandle(data);
                    break;
                case "Reminder":
                    this.onMessageHandle(data);
                    break;
                default:
                    console.log("\u672A\u5904\u7406\u7684\u6D88\u606F\u7C7B\u578B:" + messageType);
                    break;
            }
        }
        catch (ex) {
            console.log(ex.message);
        }
    };
    ReminderService.prototype.onTaskHandle = function (data) {
        ReminderService.taskListenerList.forEach(function (x) {
            x.observer.next(data);
        });
    };
    ReminderService.prototype.onMessageHandle = function (data) {
        ReminderService.messageListenerList.forEach(function (x) {
            if (x.type instanceof Array != true) {
                x.type = [x.type];
            }
            // 空元素时监听所有事件
            if (!x.type.length) {
                return x.observer.next(data);
            }
            if (x.type.includes(data.type)) {
                return x.observer.next(data);
            }
        });
    };
    /**
     * 处理消息close事件
     */
    ReminderService.prototype.onCloseHandle = function () {
        var a = [];
        for (var _i = 0; _i < arguments.length; _i++) {
            a[_i] = arguments[_i];
        }
        console.log('close', a);
    };
    /**
     * 处理消息error事件
     */
    ReminderService.prototype.onErrorHandle = function () {
        var a = [];
        for (var _i = 0; _i < arguments.length; _i++) {
            a[_i] = arguments[_i];
        }
        console.log('error', a);
    };
    ReminderService.addMessageListener = function (_a) {
        var type = (_a === void 0 ? {} : _a).type;
        return Observable.create(function (observer) {
            ReminderService.messageListenerList.push({
                type: type || [],
                observer: observer
            });
        });
    };
    ReminderService.addTaskListener = function () {
        return Observable.create(function (observer) {
            ReminderService.taskListenerList.push({
                // type,
                observer: observer
            });
        });
    };
    ReminderService.notify = function (createElement) {
        var notify;
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
        });
        ReminderService.notifyList.push(notify);
    };
    ReminderService.notifyClean = function () {
        ReminderService.notifyList.forEach(function (item) {
            item.close();
        });
    };
    // 消息服务监听列表
    ReminderService.messageListenerList = [];
    // 任务服务监听列表
    ReminderService.taskListenerList = [];
    ReminderService.notifyList = [];
    return ReminderService;
}());
export { ReminderService };
