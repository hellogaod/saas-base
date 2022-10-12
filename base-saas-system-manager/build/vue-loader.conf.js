//CSS 加载器配置

'use strict'
// 引入配置文件
const utils = require('./utils')
const config = require('../config')
// 判断是不是生产环境
const isProduction = process.env.NODE_ENV === 'production'
// 根据环境来获取相应的 productionSourceMap 或者 cssSourceMap
const sourceMapEnabled = isProduction
  ? config.build.productionSourceMap
  : config.dev.cssSourceMap

module.exports = {
  // 配置在 .vue 文件中的 css 相关处理规则
  loaders: utils.cssLoaders({
    sourceMap: sourceMapEnabled,
    extract: isProduction
  }),
  // 记录压缩的代码，用来找到源码位置
  cssSourceMap: sourceMapEnabled,
  // 是否缓存破坏
  cacheBusting: config.dev.cacheBusting,
  // transformToRequire 的作用是在模块编译的过程中，编译器可以将某些属性，比如 src 转换为 require 调用
  transformToRequire: {
    video: ['src', 'poster'],
    source: 'src',
    img: 'src',
    image: 'xlink:href'
  }
}
