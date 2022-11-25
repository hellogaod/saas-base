// https://github.com/michael-ciniawsky/postcss-load-config
// 众所周知为兼容所有浏览器，有的CSS属性需要对不同的浏览器加上前缀，然而有时添加一条属性，需要添加3~4条类似的   属性只是为了满足浏览器的兼容，这不仅会增加许多的工作量，还会使得你的思路被打断。
// 如何解决这个问题? 处理CSS前       缀问题的神器——AutoPrefixer。

module.exports = {
  "plugins": {
    // to edit target browsers: use "browserslist" field in package.json
    "postcss-import": {},//用于@import导入css文件
    "autoprefixer": {}//Autoprefixer是一个后处理程序，你可以同Sass，Stylus或LESS等预处理器共通使用。它适用于普通的CSS，而你无需关心要 为哪些浏览器加前缀，只需全新关注于实现，并使用W3C最新的规范。
  }
}
