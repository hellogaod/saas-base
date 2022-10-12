//webpack 生产环境配置

'use strict'
// 引入配置文件
const path = require('path')
const utils = require('./utils')
const webpack = require('webpack')
const config = require('../config')
const merge = require('webpack-merge')
const baseWebpackConfig = require('./webpack.base.conf')
// 导入 copy-webpack-plugin 用来复制
const CopyWebpackPlugin = require('copy-webpack-plugin')
// 导入 html-webpack-plugin 用来自动生成 html
const HtmlWebpackPlugin = require('html-webpack-plugin')
// 导入 extract-text-webpack-plugin 用来抽离 css 防止 css打包压缩到 js 中
const ExtractTextPlugin = require('extract-text-webpack-plugin')
// 导入 optimize-css-assets-webpack-plugin 用来压缩单独的 css 文件
const OptimizeCSSPlugin = require('optimize-css-assets-webpack-plugin')
// const UglifyJsPlugin = require('uglifyjs-webpack-plugin')

// 区分环境
const env = require('../config/prod.env')

const webpackConfig = merge(baseWebpackConfig, {
  module: {
    // 配置独立的css文件的解析规则
    rules: utils.styleLoaders({
      sourceMap: config.build.productionSourceMap,
      // 生成独立的文件
      extract: true,
      usePostCSS: true
    })
  },
  // 开发工具 用来调试
  devtool: config.build.productionSourceMap ? config.build.devtool : false,
  output: {
    // 打包后的文件放在 dist 目录下面
    path: config.build.assetsRoot,
    // 编译生成的 js 文件存放在根目录下的 js 目录下，如果 js 文件夹不存在就自动创建
    filename: utils.assetsPath('js/[name].[chunkhash].js'),
    // 用来打包 require.ensure 方法中引入的模块，如果该方法中没有引入任何模块，就不会生成 chunk 文件
    chunkFilename: utils.assetsPath('js/[id].[chunkhash].js')
  },
  plugins: [
    // http://vuejs.github.io/vue-loader/en/workflow/production.html
    // 自定义一个 plugin 生成当前环境下的一个变量
    new webpack.DefinePlugin({
      'process.env': env
    }),
    // 压缩
    // new UglifyJsPlugin({
    //   uglifyOptions: {
    //     ie8: false,
    //     ecma: 8,
    //     mangle: true,
    //     output: { comments: false },
    //     compress: { warnings: false }
    //   },
    //   sourceMap: config.build.productionSourceMap,
    //   parallel: true
    // }),
    // extract css into its own file
    // 独立的 css 文件夹插件
    new ExtractTextPlugin({
      filename: utils.assetsPath('css/[name].[contenthash].css'),
      // Setting the following option to `false` will not extract CSS from codesplit chunks.
      // Their CSS will instead be inserted dynamically with style-loader when the codesplit chunk has been loaded by webpack.
      // It's currently set to `true` because we are seeing that sourcemaps are included in the codesplit bundle as well when it's `false`,
      // increasing file size: https://github.com/vuejs-templates/webpack/issues/1110
      allChunks: true,
    }),
    // Compress extracted CSS. We are using this plugin so that possible
    // duplicated CSS from different components can be deduped.
    // 压缩 css
    new OptimizeCSSPlugin({
      cssProcessorOptions: config.build.productionSourceMap
        ? { safe: true, map: { inline: false } }
        : { safe: true }
    }),
    // generate dist index.html with correct asset hash for caching.
    // you can customize output by editing /index.html
    // see https://github.com/ampedandwired/html-webpack-plugin
    new HtmlWebpackPlugin({
      filename: config.build.index,
      template: 'index.html',
      inject: true,
      minify: {
        removeComments: true,
        collapseWhitespace: true,
        removeAttributeQuotes: true
        // more options:
        // https://github.com/kangax/html-minifier#options-quick-reference
      },
      // necessary to consistently work with multiple chunks via CommonsChunkPlugin
      chunksSortMode: function (chunk1, chunk2) {
        let order = [/manifest/, /^(?!.*(manifest|vendor|app))/, /vendor/, /app/]
        let order1 = order.findIndex(x => x.test(chunk1.names[0]))
        let order2 = order.findIndex(x => x.test(chunk2.names[0]))
        return order1 == order2 ? 1 : order1 - order2;
      }
    }),
    // keep module.id stable when vender modules does not change
    // 将各模块的 id 变成 hash 值
    new webpack.HashedModuleIdsPlugin(),
    // enable scope hoisting
    new webpack.optimize.ModuleConcatenationPlugin(),
    // 限定moment语言支持: zh,en
    new webpack.ContextReplacementPlugin(/moment[\\\/]locale$/, /^\.\/(zh-cn|en-gb)$/),
    // split vendor js into its own file
    new webpack.optimize.CommonsChunkPlugin({
      name: 'vendor',
      minChunks({ resource }) {
        // any required modules inside node_modules are extracted to vendor
        return resource &&
          resource.includes('node_modules') &&
          resource.match(/\.js$/)

      }
    }),
    // 抽取第三方库到 app.j
    new webpack.optimize.CommonsChunkPlugin({
      async: 'vendor-async',
      children: true,
      minChunks: function ({ resource }) {
        return resource &&
          resource.includes('node_modules') &&
          resource.match(/\.js$/)
      }
    }),
    new webpack.optimize.CommonsChunkPlugin({
      async: 'common-async',
      children: true,
      minChunks: ({ resource }, count) => {
        return resource &&
          resource.includes('src') &&
          resource.match(/\.(js|ts|tsx|vue)$/) &&
          count >= 2
      }
    }),

    new webpack.optimize.CommonsChunkPlugin({
      name: 'rxjs',
      chunks: ['vendor'],
      minChunks: function ({ resource }) {
        return resource &&
          resource.includes('rxjs') &&
          resource.match(/\.js$/)
      }
    }),
    new webpack.optimize.CommonsChunkPlugin({
      name: 'element-ui',
      chunks: ['vendor'],
      minChunks: function ({ resource }) {
        return resource &&
          resource.includes('element-ui') &&
          resource.match(/\.js$/)
      }
    }),
    // extract webpack runtime and module manifest to its own file in order to
    // prevent vendor hash from being updated whenever app bundle is updated
    new webpack.optimize.CommonsChunkPlugin({
      name: 'manifest',
      minChunks: Infinity
    }),
    // copy custom static assets
    // 复制自定义的静态资源到生产环境
    new CopyWebpackPlugin([
      {
        from: path.resolve(__dirname, '../static'),
        to: config.build.assetsSubDirectory,
        ignore: ['.*']
      }
    ])
  ]
})

// 压缩
if (config.build.productionGzip) {
  const CompressionWebpackPlugin = require('compression-webpack-plugin')

  webpackConfig.plugins.push(
    new CompressionWebpackPlugin({
      // 目标资源名称 [path]会被替换成原始资源的路径 [query]会被替换成查询字符串
      asset: '[path].gz[query]',
      // 按照zlib的算法
      algorithm: 'gzip',
      // 所有匹配该正则的资源都会被处理 默认值是全部资源
      test: new RegExp(
        '\\.(' +
        config.build.productionGzipExtensions.join('|') +
        ')$'
      ),
      // 只有大小大于该值得资源会被处理，单位是 bytes
      threshold: 10240,
      // 压缩率小于这个值得资源才会被处理 默认值是 .8
      minRatio: 0.8
    })
  )
}

// 打包文件分析工具
if (config.build.bundleAnalyzerReport) {
  const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin
  webpackConfig.plugins.push(new BundleAnalyzerPlugin())
}

// 导出
module.exports = webpackConfig
