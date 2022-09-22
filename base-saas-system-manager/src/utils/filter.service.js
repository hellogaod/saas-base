import store from '~/store';
import * as enumData from "~/config/enum.config";
import appConfig from "~/config/app.config";
var FilterService = /** @class */ (function () {
    function FilterService() {
    }
    /**
     * 转换枚举数据
     * @param value
     * @param key
     */
    FilterService.enumConvert = function (value, key) {
        // 排除空字典或者空key
        if (!enumData || !key || !enumData[key]) {
            return '';
        }
        var data = enumData[key] instanceof Array ? enumData[key] : Object.values(enumData[key]);
        var target = data.find(function (x) { return x.value === value; });
        return target ? target.name : "";
    };
    /**
     * 转换字典数据
     * @param id
     */
    FilterService.dictConvert = function (id) {
        var dictData = store.state.dictData;
        var target;
        if (dictData) {
            target = dictData.find(function (x) { return x.id === id; });
        }
        return target ? target.name : "";
    };
    /**
     * 日期范围转换
     * @param dateRange
     * @param fmt
     */
    FilterService.dateRanageFormat = function (dateRange, fmt) {
        if (fmt === void 0) { fmt = "yyyy-MM-dd hh:mm:ss"; }
        var target = {
            start: '',
            end: ''
        };
        // 检测非法输入
        if (!dateRange || dateRange.length === 0 || !(dateRange instanceof Array)) {
            return target;
        }
        target.start = FilterService.dateFormat(dateRange[0], fmt);
        target.end = FilterService.dateFormat(dateRange[1], fmt);
        return target;
    };
    /**
     * 日期时间格式化
     * @param date
     * @param fmt 默认值为长日期格式
     */
    FilterService.dateTimeFormat = function (date, fmt) {
        if (fmt === void 0) { fmt = "yyyy-MM-dd hh:mm:ss"; }
        return FilterService.dateFormat(date, fmt);
    };
    /**
     * 日期格式化
     * @param date
     * @param fmt 默认值为短日期格式
     */
    FilterService.dateFormat = function (date, fmt) {
        if (fmt === void 0) { fmt = "yyyy-MM-dd"; }
        // 空数据处理
        if (date === null || date === undefined || date === '') {
            return '';
        }
        // 如果是时间戳则转化为时间
        if (typeof date === 'number') {
            date = new Date(date);
        }
        var o = {
            'M+': date.getMonth() + 1,
            'd+': date.getDate(),
            'h+': date.getHours(),
            'm+': date.getMinutes(),
            's+': date.getSeconds(),
            'q+': Math.floor((date.getMonth() + 3) / 3),
            'S': date.getMilliseconds() // 毫秒
        };
        if (/(y+)/.test(fmt))
            fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
        for (var k in o) {
            if (new RegExp('(' + k + ')').test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)));
        }
        return fmt;
    };
    /**
     * 千分位转换
     * @param number
     */
    FilterService.toThousands = function (number, fixed) {
        if (fixed === void 0) { fixed = 2; }
        var num = '';
        if (number === null || number === '') {
            num = number;
        }
        else {
            num = Number(number).toFixed(fixed);
            if (isNaN(num) || num === '' || num === undefined || num === null) {
                return '';
            }
            num = num + '';
            if (/^.*\..*$/.test(num)) {
                var pointIndex = num.lastIndexOf('.');
                var intPart = num.substring(0, pointIndex);
                var pointPart = num.substring(pointIndex + 1, num.length);
                intPart = intPart + '';
                var re = /(-?\d+)(\d{3})/;
                while (re.test(intPart)) {
                    intPart = intPart.replace(re, '$1,$2');
                }
                num = intPart + '.' + pointPart;
            }
            else {
                num = num + '';
                var re = /(-?\d+)(\d{3})/;
                while (re.test(num)) {
                    num = num.replace(re, '$1,$2');
                }
            }
        }
        return num;
    };
    /**
     * 手机号脱敏显示转换器
     */
    FilterService.encryptPhone = function (value) {
        if (!appConfig.encryptSwitch)
            false;
        if (!value || value === '') {
            return '';
        }
        return value.substr(0, 3) + '****' + value.substr(7);
    };
    /**
     * 身份证脱敏显示转换器
     */
    FilterService.encryptIDCardFour = function (value) {
        if (!appConfig.encryptSwitch)
            false;
        if (!value || value === '') {
            return '';
        }
        // return value.substr(0, 3).padEnd(value.length - 4, '*') + value.substr(-4)
        return value.substr(0, 3) + '****' + value.substr(-4);
    };
    /**
     * 长度过长省略显示
     */
    FilterService.ellipsis = function (value, length) {
        if (!value) {
            return '';
        }
        var data = String(value).trim();
        if (data.length <= length) {
            return data;
        }
        else {
            return data.substr(0, length) + '...';
        }
    };
    /**
     * 小数转换为百分比
     * @param point 要转换的小数
     */
    FilterService.toPercent = function (point) {
        var str = '--';
        if (point !== undefined) {
            point *= 100;
            str = (point > 0 ? point.toFixed(2) : 0) + '%';
        }
        return str;
    };
    return FilterService;
}());
export { FilterService };
