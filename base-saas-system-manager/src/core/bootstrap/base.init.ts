import Vue from 'vue'
import injector from 'vue-inject';
import createProvide from '~/core/provide'
import createFilters from '~/extension/filter'
import createDirectives from '~/extension/directive'
import createPlugins from '~/extension/plugin'

export default async function ({ store }) {
  // 创建提供器
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

  // 安装过滤器
  if (createFilters) {
    Object.entries(createFilters({ store })).forEach(([key, fun]) => {
      Vue.filter(key, fun)
    })
  }

  // 安装指令
  if (createDirectives) {
    Object.entries(createDirectives({ store })).forEach(([key, fun]) => {
      Vue.directive(key, fun)
    })
  }

  // 安装插件
  if (createPlugins) {
    Object.entries(createPlugins({ store })).forEach(([key, plugin]: [string, any]) => {
      Vue.use(plugin)
    })
  }
}
