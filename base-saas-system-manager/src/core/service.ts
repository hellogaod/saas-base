import injector from 'vue-inject';

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
