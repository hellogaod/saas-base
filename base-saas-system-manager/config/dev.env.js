'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')
const devEnv = require('../environment/dev.env')

let targetServer = process.env.server
let tempEnv

// 是否使用临时地址
if (targetServer) {
  tempEnv = {
    URL: {
      SERVER: JSON.stringify("http://" + targetServer)
    }
  }
}

module.exports = merge({
  NODE_ENV: '"development"'
}, devEnv, tempEnv)
