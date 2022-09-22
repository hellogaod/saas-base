var defaultStorage = 'local';
var storageTypes = ['local', 'session'];
var StorageService = /** @class */ (function () {
    function StorageService(type) {
        // 设置storage类型
        this.storageType = type || defaultStorage;
        // storage类型检测
        if (!storageTypes.includes(this.storageType)) {
            console.warn('storage类型错误');
            this.storageType = defaultStorage;
        }
        // 设置storage
        this.storage = {
            'local': localStorage,
            'session': sessionStorage
        }[this.storageType];
    }
    /**
     * 获取当前存储类型对象
     * @param {*} type
     */
    StorageService.getStorage = function (type) {
        var storage;
        switch (type) {
            case 'local': {
                storage = StorageService.instance['local'];
                if (!storage) {
                    storage = new StorageService('local');
                    StorageService.instance['local'] = storage;
                }
                return storage;
            }
            case 'session':
                {
                    storage = StorageService.instance['session'];
                    if (!storage) {
                        storage = new StorageService('session');
                        StorageService.instance['session'] = storage;
                    }
                    return storage;
                }
            default:
                {
                    console.warn('请输入storage类型');
                    storage = StorageService.instance[defaultStorage];
                    if (!storage) {
                        storage = new StorageService('defaultStorage');
                        StorageService.instance['defaultStorage'] = storage;
                    }
                    return storage;
                }
        }
    };
    /* 数据存储
    * @param { String } key
    * @param { Object } value
    * @param { Object } extend 数据扩展标识
    */
    StorageService.prototype.setItem = function (key, value) {
        // key必须为字符串
        if (!key || typeof key !== 'string') {
            console.log('key参数错误.');
            return;
        }
        var data;
        // 取消对扩展的支持
        // 统一将数据转换为json格式进行存储
        if (value === undefined) {
            // 返回空数据
            data = JSON.stringify("");
        }
        else if (typeof value === 'object') {
            data = JSON.stringify(value);
        }
        else {
            data = value;
        }
        this.storage.setItem(key, data);
    };
    /**
     * 获取存储数据
     * @param {Object} key
     */
    StorageService.prototype.getItem = function (key) {
        if (!key || typeof key !== 'string') {
            console.error('key参数错误.');
            return;
        }
        var data = this.storage.getItem(key);
        // 统一讲数据转换为json格式进行存储
        try {
            if (data !== '' && data !== undefined) {
                return JSON.parse(data);
            }
            else {
                return '';
            }
        }
        catch (ex) {
            return data;
        }
    };
    /**
     * 删除数据对象
     * @param {Object} key
     */
    StorageService.prototype.removeItem = function (key) {
        this.storage.removeItem(key);
    };
    StorageService.getItem = function (key) {
        return this.getStorage(defaultStorage).getItem(key);
    };
    StorageService.setItem = function (key, value) {
        return this.getStorage(defaultStorage).setItem(key, value);
    };
    StorageService.removeItem = function (key) {
        this.getStorage(defaultStorage).removeItem(key);
    };
    StorageService.instance = {};
    return StorageService;
}());
export { StorageService };
