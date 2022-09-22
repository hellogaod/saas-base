const NetService = () => require('~/utils/net.service')
const PageService = () => require('~/utils/page.service')

// const DataDictSerivce = () => require('~/services/business-services/data-dict.service')
// const ResourceSerivce = () => require('~/services/business-services/resource.service')
// const LoginSerivce = () => require('~/services/business-services/login.service')

export default function () {
  return {
    'netService': [NetService, 'none'],
    'pageService': [PageService, 'none'],
    // 'dataDictSerivce': DataDictSerivce,
    // 'resourceSerivce': ResourceSerivce,
    // 'loginSerivce': LoginSerivce
  }
}
