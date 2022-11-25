//webpack 开发环境配置

'use strict'
// 引入配置文件
const utils = require('./utils')
const webpack = require('webpack')
const config = require('../config')
const merge = require('webpack-merge')
const baseWebpackConfig = require('./webpack.base.conf')

// 自动生成 html 的插件，能够把资源自动加载到 html 文件中
const HtmlWebpackPlugin = require('html-webpack-plugin')
// 把 webpack 的错误和日志收集起来，更友好展示错误日志的插件
const FriendlyErrorsPlugin = require('friendly-errors-webpack-plugin')
// 自动打开可用端口的包
const portfinder = require('portfinder')

// 当前环境的 host
const HOST = process.env.HOST
// 当前环境的 port
const PORT = process.env.PORT && Number(process.env.PORT)

// 开发环境的配置
const devWebpackConfig = merge(baseWebpackConfig, {
  module: {
    // loader 的配置，具体内容可以参考 utils 文件
    rules: utils.styleLoaders({
      sourceMap: config.dev.cssSourceMap,
      usePostCSS: true
    })
  },
  // cheap-module-eval-source-map is faster for development
  devtool: config.dev.devtool,

  // these devServer options should be customized in /config/index.js
  devServer: {
    // 重新加载 server 时，控制台对一些错误以 warning 的方式提示
    clientLogLevel: 'warning',
    historyApiFallback: true,
    // 启用 webpack 的模块热替换特性
    hot: true,
    // 是否压缩
    compress: true,
    host: HOST || config.dev.host,
    port: PORT || config.dev.port,
    // 是否自动打开浏览器
    open: config.dev.autoOpenBrowser,
    // 编译出错时是否有提示
    overlay: config.dev.errorOverlay ? {
      warnings: false,
      errors: true
    } : false,
    // 静态内容的路径,此路径下的打包文件可在浏览器中访问
    publicPath: config.dev.assetsPublicPath,
    // 接口的代理
    proxy: config.dev.proxyTable,
    // 启用 quiet 后，除了初始启动信息之外的任何内容都不会被打印到控制台
    // 即来自 webpack 的错误或警告在控制台不可见
    quiet: true, // necessary for FriendlyErrorsPlugin
    // 监视文件的选项
    watchOptions: {
      poll: config.dev.poll,
    }
  },
  plugins: [
    // DefinePlugin 允许创建一个在编译时可以配置的全局常量。这里生成了一个当前环境的常量
    new webpack.DefinePlugin({
      'process.env': require('../config/dev.env')
    }),
    // 模块热替换插件，修改模块时不需要刷新页面
    new webpack.HotModuleReplacementPlugin(),
    // 在编译出现错误时，使用 NoEmitOnErrorsPlugin 来跳过输出阶段。这样可以确保输出资源不会包含错误。
    new webpack.NamedModulesPlugin(), // HMR shows correct file names in console on update.
    new webpack.NoEmitOnErrorsPlugin(),
    // https://github.com/ampedandwired/html-webpack-plugin
    new HtmlWebpackPlugin({
      filename: 'index.html',
      template: 'index.html',
      // 打包后 js 文件放在 body 的最后
      inject: true
    }),
  ]
})

// webpack 将运行由配置文件导出的函数，并且等待 promise 返回，便于需要异步地加载所需的配置变量。
module.exports = new Promise((resolve, reject) => {
  portfinder.basePort = process.env.PORT || config.dev.port
  portfinder.getPort((err, port) => {
    if (err) {
      reject(err)
    } else {
      // publish the new Port, necessary for e2e tests
      process.env.PORT = port
      // add port to devServer config
      devWebpackConfig.devServer.port = port
      // 获取IP地址，
      let ipaddress = devWebpackConfig.devServer.host
      if (ipaddress === '0.0.0.0') {
        ipaddress = 'localhost'
      }
      // Add FriendlyErrorsPlugin
      // 出错友好处理插件
      devWebpackConfig.plugins.push(new FriendlyErrorsPlugin({
        compilationSuccessInfo: {
          messages: [`Your application is running here: http://${ipaddress}:${port}`],
        },
        onErrors: config.dev.notifyOnErrors ?
          utils.createNotifierCallback() : undefined
      }))

      resolve(devWebpackConfig)
    }
  })
})
