import injector from 'vue-inject';

//所有的service都继承当前文件，但是当前文件并没有任何公共方法可供操作
export class Service {
  static getInstance() {
    let instance
    let key = this.name.replace(this.name.charAt(0), this.name.charAt(0).toLocaleLowerCase())

    if (!injector.$$factories[key]) {
      injector.service(key, this);
    }

    try {
      instance = injector.get(key)
    } catch (ex) {
      instance = new this()
    }
    return instance
  }
}
