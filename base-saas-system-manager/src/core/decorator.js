import "reflect-metadata";
import { createDecorator } from 'vue-class-component';
import injector from 'vue-inject';
import { Observable } from "rxjs";
/**
 * 组件内依赖注入
 * @param target
 */
export function Dependencies(target) {
    return createDecorator(function (componentOptions, key) {
        if (!injector.$$factories[key] && target) {
            injector.service(key, target);
        }
        if (typeof componentOptions['dependencies'] === 'undefined') {
            componentOptions['dependencies'] = [];
        }
        if (Array.isArray(componentOptions['dependencies'])) {
            componentOptions['dependencies'].push(key);
        }
    });
}
/**
 * 直接依赖注入
 */
export function Inject(target) {
    return function (container, key) {
        if (!injector.$$factories[key] && target) {
            injector.service(key, target).lift;
        }
        try {
            container[key] = injector.get(key);
        }
        catch (ex) {
            console.warn(ex);
        }
    };
}
/**
 * 函数去抖动
 * @param time
 */
export function Debounce(time) {
    if (time === void 0) { time = 500; }
    return function (target, name, descriptor) {
        var oldValue = descriptor.value;
        var flag;
        descriptor.value = function () {
            if (flag) {
                return Observable.empty();
            }
            else {
                flag = setTimeout(function () {
                    flag = null;
                }, time);
            }
            return oldValue.apply(target, arguments);
        };
        return descriptor;
    };
}
/**
 * 设置布局
 * @param target
 */
export function Layout(layout) {
    return function (target) {
        target.$layout = layout;
        return target;
    };
}
/**
 * 权限码中间件
 * @param target
 */
export function Auth(code) {
    return function (target) {
        target.$auth = code;
        return target;
    };
}
/**
 * 设置扩展属性
 * @param extend
 */
export function Extend(data) {
    return function (target) {
        Object.entries(data).forEach(function (_a) {
            var key = _a[0], value = _a[1];
            target[key] = value;
        });
        return target;
    };
}
