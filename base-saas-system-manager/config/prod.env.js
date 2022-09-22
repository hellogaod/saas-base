'use strict'

const merge = require('webpack-merge')

// 获取编译环境
let build_env = process.env.env || "prod"
const prodEnv = require(`../environment/${build_env}.env`)

module.exports = merge({
  NODE_ENV: '"production"', // 编译模式
  BUILD_ENV: JSON.stringify(build_env), // 编译环境
  BUILD_TIME: JSON.stringify(new Date().toLocaleString()) // 编译环境
}, prodEnv)
