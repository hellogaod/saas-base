# vue项目的运行流程
在工程化项目中，vue要做的事情很单纯：通过main.js把App.vue渲染到index.html的指定区域中,

# 构建配置入口

package.json中

      "scripts": {
        "dev": "cross-env ENV=dev webpack-dev-server --inline --progress --config build/webpack.dev.conf.js",
        "start": "npm run dev",
        "build": "node build/build.js"
      },

# base-saas-manage

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).

# 管理端访问路径

## 系统管理端

http://localhost:8080/sys-manage

## 企业管理端

默认：http://localhost:8080/

或：http://localhost:8080/ent-manager
