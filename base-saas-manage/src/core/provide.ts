const NetService = () => require('~/utils/net.service')
const PageUtil = () => require('~/utils/page.util')
//通过base.init.ts初始化，这样通过就可以通过@Inject使用乐乐
export default function () {
  return {
    'netService': [NetService, 'none'],
    'pageUtil': [PageUtil, 'none'],
  }
}
