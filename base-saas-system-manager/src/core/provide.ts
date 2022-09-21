const NetService = () => require('~/utils/net.services')
const PageService = () => require('~/utils/page.services')

// const DataDictSerivce = () => require('~/services/business-services/data-dict.services')
// const ResourceSerivce = () => require('~/services/business-services/resource.services')
// const LoginSerivce = () => require('~/services/business-services/login.services')

export default function () {
  return {
    'netService': [NetService, 'none'],
    'pageService': [PageService, 'none'],
    // 'dataDictSerivce': DataDictSerivce,
    // 'resourceSerivce': ResourceSerivce,
    // 'loginSerivce': LoginSerivce
  }
}
