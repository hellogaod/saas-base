//webpack 基础配置

'use strict'
// 引入配置文件
const path = require('path')
const utils = require('./utils')
const config = require('../config')
const vueLoaderConfig = require('./vue-loader.conf')
const ForkTsCheckerWebpackPlugin = require('fork-ts-checker-webpack-plugin');

// 封装函数返回 dir 的和 .. 和其绝对路径用 \ 拼接的路径
function resolve(dir) {
  return path.join(__dirname, '..', dir)
}

module.exports = {
  context: path.resolve(__dirname, '../'),
  entry: {
    // 入口文件 多入口在这里添加
    app: ['babel-polyfill', './src/main.ts']
  },
  // 输出
  output: {
    // 文件路径
    path: config.build.assetsRoot,
    // 文件名
    filename: '[name].js',
    // 静态资源路径 根据环境来改变
    publicPath: process.env.NODE_ENV === 'production'
      ? config.build.assetsPublicPath
      : config.dev.assetsPublicPath
  },
  externals: {
    'app-config': 'appConfig',
  },
  // 对模块进行解析
  resolve: {
    // 对模块的后缀进行解析 比如导入 index 没有写后缀名 那么会自动先匹配 .js > .vue > .json 后缀文件
    extensions: ['.ts', '.js', '.vue', '.json'],
    alias: {
      // 别名，@ 和~ 代表 src 就是在这里配置的
      'vue$': 'vue/dist/vue.esm.js',
      '@': resolve('src'),
      '~': resolve('src')
    }
  },
  // 解析不同的模块
  module: {
    rules: [
      {
        // 解析 .vue 后缀的文件
        test: /\.vue$/,
        // 用 vue-loader 去解析 .vue 后缀的文件
        loader: 'vue-loader',
        // 对解析文件的参数配置
        options: vueLoaderConfig
      },
      {
        test: /\.tsx?$/,
        loader: 'ts-loader',
        exclude: /node_modules/,
        options: {
          transpileOnly: true,
          appendTsSuffixTo: [/\.vue$/],
        }
      },
      {
        // 解析后缀 .js 的文件
        test: /\.js$/,
        loader: 'babel-loader',
        // 解析的包含的文件路径
        include: [resolve('src'), resolve('test')]
      },
      {
        test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
        // 限制在 10000 以内
        loader: 'url-loader',
        exclude: [resolve('src/assets/icons')],
        options: {
          limit: 10000,
          // 对输出的内容进行地址转换
          name: utils.assetsPath('img/[name].[hash:7].[ext]')
        }
      },
      {
        test: /\.svg$/,
        loader: 'svg-sprite-loader',
        include: [resolve('src/assets/icons')],
        options: {
          symbolId: 'icon-[name]'
        }
      },
      {
        test: /\.(mp4|webm|ogg|mp3|wav|flac|aac)(\?.*)?$/,
        loader: 'url-loader',
        options: {
          limit: 10000,
          name: utils.assetsPath('media/[name].[hash:7].[ext]')
        }
      },
      {
        test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
        loader: 'url-loader',
        options: {
          limit: 10000,
          name: utils.assetsPath('fonts/[name].[hash:7].[ext]')
        }
      }
    ]
  },
  plugins: [
    new ForkTsCheckerWebpackPlugin({
      vue: true,
      workers: ForkTsCheckerWebpackPlugin.TWO_CPUS_FREE
    })
  ],
  node: {
    // 阻止 webpack 注入无用的 setImmediate polyfill
    // prevent webpack from injecting useless setImmediate polyfill because Vue
    // source contains it (although only uses it if it's native).
    setImmediate: false,
    // prevent webpack from injecting mocks to Node native modules
    // that does not make sense for the client
    dgram: 'empty',
    fs: 'empty',
    net: 'empty',
    tls: 'empty',
    child_process: 'empty'
  }
}
