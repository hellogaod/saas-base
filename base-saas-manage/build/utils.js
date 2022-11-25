//构想相关工具

'use strict'
// 引入nodejs路径模块
const path = require('path')
// 引入 config 目录下的 index.js 配置文件
const config = require('../config')
// 引入 extract-text-webpack-plugin 插件，用来将 css 提取到单独的 css 文件中
const ExtractTextPlugin = require('extract-text-webpack-plugin')
// 引入 package.json
const packageConfig = require('../package.json')

// exports 其实就是一个对象，用来导出方法的最终还是使用 module.exports，此处导出 assetsPath
exports.assetsPath = function (_path) {
  const assetsSubDirectory = process.env.NODE_ENV === 'production'
    ? config.build.assetsSubDirectory
    : config.dev.assetsSubDirectory

  // 返回一个相对根路径
  return path.posix.join(assetsSubDirectory, _path)
}

// 导出 cssLoaders 的相关配置
exports.cssLoaders = function (options) {
  options = options || {}

  // cssLoader 的基本配置
  const cssLoader = {
    loader: 'css-loader',
    options: {
      sourceMap: options.sourceMap
    }
  }

  // 为 css 自动生成兼容性前缀
  const postcssLoader = {
    loader: 'postcss-loader',
    options: {
      sourceMap: options.sourceMap
    }
  }

  // generate loader string to be used with extract text plugin
  function generateLoaders (loader, loaderOptions) {
    // 将上面的基础 cssLoader 配置、兼容性前缀配置、自适应配置放在一个数组里面
    const loaders = options.usePostCSS ? [cssLoader, postcssLoader] : [cssLoader]

    // 如果该函数传递了单独的 loader 就加到这个 loaders 数组里面，这个 loader 可能是 less,sass 之类的
    if (loader) {
      loaders.push({
        // 加载对应的 loader
        loader: loader + '-loader',
        // Object.assign 是 es6 的方法，主要用来合并对象的，浅拷贝
        options: Object.assign({}, loaderOptions, {
          sourceMap: options.sourceMap
        })
      })
    }

    // Extract CSS when that option is specified
    // (which is the case during production build)
    // extract 是自定义的属性，可以定义在 options 里面
    // 主要作用就是当配置为 true 就把文件单独提取，false表示不单独提取，这个可以在使用的时候单独配置
    // 用来返回最终读取和导入 loader，来处理对应类型的文件
    if (options.extract) {
      return ExtractTextPlugin.extract({
        use: loaders,
        fallback: 'vue-style-loader'
      })
    } else {
      return ['vue-style-loader'].concat(loaders)
    }
  }

  // https://vue-loader.vuejs.org/en/configurations/extract-css.html
  return {
    // css 对应 vue-style-loader 和 css-loader
    css: generateLoaders(),
    // postcss 对应 vue-style-loader 和 css-loade
    postcss: generateLoaders(),
    // less 对应 vue-style-loader 和 less-loader
    less: generateLoaders('less'),
    // sass 对应 vue-style-loader 和 sass-loader
    sass: generateLoaders('sass', { indentedSyntax: true }),
    // scss 对应 vue-style-loader 和 sass-loader
    scss: generateLoaders('sass'),
    // stylus 对应 vue-style-loader 和 stylus-loader
    stylus: generateLoaders('stylus'),
    // styl 对应 vue-style-loader 和 styl-loader
    styl: generateLoaders('stylus')
  }
}

// Generate loaders for standalone style files (outside of .vue)
// 下面这个主要处理 import 这种方式导入的文件类型的打包，上面的 exports.cssLoaders 是为这一步服务的
exports.styleLoaders = function (options) {
  const output = []
  // 下面就是生成的各种 css 文件的 loader 对象
  const loaders = exports.cssLoaders(options)

  for (const extension in loaders) {
    // 把每一种文件的 laoder 都提取出来
    const loader = loaders[extension]
    output.push({
      // 把最终的结果都 push 到 output 数组中
      test: new RegExp('\\.' + extension + '$'),
      use: loader
    })
  }

  return output
}

// node-notifier 是一个跨平台系统通知的页面，当遇到错误时，它能用系统原生的推送方式推送信息
exports.createNotifierCallback = () => {
  const notifier = require('node-notifier')

  return (severity, errors) => {
    if (severity !== 'error') return

    const error = errors[0]
    const filename = error.file && error.file.split('!').pop()

    notifier.notify({
      title: packageConfig.name,
      message: severity + ': ' + error.name,
      subtitle: filename || '',
      icon: path.join(__dirname, 'logo.png')
    })
  }
}
