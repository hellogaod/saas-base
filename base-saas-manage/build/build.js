//生产环境构建配置

// 此文件是 webpack 的基本项目配置文件
'use strict'
// 版本检查 node 的版本号  版本有要求 "engines": {"node": ">= 4.0.0","npm": ">= 3.0.0"}
// 立即执行
require('./check-versions')()

// process 是 node 中的 global 全局对象的属性，process 是 node 中的全局变量，env 设置环境变量
process.env.NODE_ENV = 'production'

// ora 是一个命令行转圈圈动画插件，好看用的
const ora = require('ora')
// rimraf 插件是用来执行 UNIX 命令 rm 和 -rf 的用来删除文件夹和文件，清空旧的文件
const rm = require('rimraf')
// node.js 路径模块 连接路径
const path = require('path')
// chalk 插件，用来在命令行中显示彩色文字
const chalk = require('chalk')
// 引入 webpack 模块使用内置插件和 webpack 方法
const webpack = require('webpack')
// 引入 config 目录下的 index.js 配置文件
const config = require('../config')
const webpackConfig = require('./webpack.prod.conf')

// 开启转圈圈动画
const spinner = ora('building for production...')
spinner.start()

// 调用 rm 方法，第一个参数的结果就是 绝对/工程名/dist/static，表示删除这个路径下面的所有文件
rm(path.join(config.build.assetsRoot, config.build.assetsSubDirectory), err => {
  // 如果删除的过程中出现错误，就抛出这个错误，同时程序终止
  if (err) throw err
  // 没有错误，就执行 webpack 编译
  webpack(webpackConfig, (err, stats) => {
    // 这个回调函数是 webpack 编译过程中执行

    // 停止转圈圈动画
    spinner.stop()

    // 如果有错误就抛出错误
    if (err) throw err

    // process.stdout 用来控制标准输出，stats对象中保存着编译过程中的各种消息
    process.stdout.write(stats.toString({
      // 增加控制台颜色开关
      colors: true,
      // 不增加内置模块信息
      modules: false,
      // 不增加子级信息
      children: false, // if you are using ts-loader, setting this to true will make tyescript errors show up during build
      // 允许较少的输出
      chunks: false,
      // 不将内置模块的信息加到包信息
      chunkModules: false
    }) + '\n\n')

    if (stats.hasErrors()) {
      console.log(chalk.red('  Build failed with errors.\n'))
      process.exit(1)
    }

    // 编译成功的消息
    console.log(chalk.cyan('  Build complete.\n'))
    console.log(chalk.yellow(
      '  Tip: built files are meant to be served over an HTTP server.\n' +
      '  Opening index.html over file:// won\'t work.\n'
    ))
  })
})
