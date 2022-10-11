import helperPlugin from './helper.plugin'
import dateutilPlugin from './dateutil.plugin'
//当前设置是为了可以在html代码片段中调用其方法，例如：$helper.getColumnWidth(3)
export default ({store}) => ({
  helperPlugin,
  dateutilPlugin,
})
