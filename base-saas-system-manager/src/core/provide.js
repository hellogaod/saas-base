var NetService = function () { return require('~/utils/net.service'); };
var PageService = function () { return require('~/utils/page.service'); };
// const DataDictSerivce = () => require('~/services/business-services/data-dict.service')
// const ResourceSerivce = () => require('~/services/business-services/resource.service')
// const LoginSerivce = () => require('~/services/business-services/login.service')
export default function () {
    return {
        'netService': [NetService, 'none'],
        'pageService': [PageService, 'none'],
    };
}
