import Vue from 'vue'
import injector from 'vue-inject';
import createProvide from '~/core/provide'
import createPlugins from '~/extension/plugin'

export default async function ({store}) {
  // 创建提供器，提供netService和pageUtil
  if (createProvide) {
    Vue.use(injector)
    Object.entries(createProvide()).forEach(([key, value]) => {
      let lifecycle: any = "class"
      let provide: any

      if (value instanceof Array) {
        lifecycle = value[1]
        provide = value[0]
      } else {
        provide = value
      }

      let [target] = Object.values(provide())
      injector.service(key, target).lifecycle[lifecycle]();
    })
  }

  // 安装插件:$help和$filter两个属性就可以使用了，可自行在/extension/plugin中扩展其他属性
  if (createPlugins) {
    Object.entries(createPlugins({store})).forEach(([key, plugin]: [string, any]) => {
      Vue.use(plugin)
    })
  }
}
