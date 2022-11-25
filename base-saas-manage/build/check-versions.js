//版本检查，npm 等

'use strict'
// chalk 插件，用来在命令行中显示彩色文字
const chalk = require('chalk')
// semver 插件，是用来判断特定的版本号
const semver = require('semver')
// 导入 package.json 文件，要使用里面的 engines
// 要注意 require 是直接可以导入 json 文件的，并且 requrie 返回的就是 json 对象
const packageConfig = require('../package.json')
// shelljs 插件，作用是用来执行 Unix 系统命令
const shell = require('shelljs')

function exec (cmd) {
  return require('child_process').execSync(cmd).toString().trim()
}

// 检查 node 版本信息
const versionRequirements = [
  {
    name: 'node',
    currentVersion: semver.clean(process.version),
    versionRequirement: packageConfig.engines.node
  }
]

// 检查 npm 版本信息
if (shell.which('npm')) {
  versionRequirements.push({
    name: 'npm',
    currentVersion: exec('npm --version'),
    versionRequirement: packageConfig.engines.npm
  })
}

// 输出提示
module.exports = function () {
  const warnings = []

  for (let i = 0; i < versionRequirements.length; i++) {
    const mod = versionRequirements[i]

    if (!semver.satisfies(mod.currentVersion, mod.versionRequirement)) {
      warnings.push(mod.name + ': ' +
        chalk.red(mod.currentVersion) + ' should be ' +
        chalk.green(mod.versionRequirement)
      )
    }
  }

  if (warnings.length) {
    console.log('')
    console.log(chalk.yellow('To use this template, you must update following to modules:'))
    console.log()

    for (let i = 0; i < warnings.length; i++) {
      const warning = warnings[i]
      console.log('  ' + warning)
    }

    console.log()
    process.exit(1)
  }
}
