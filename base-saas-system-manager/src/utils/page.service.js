var PageService = /** @class */ (function () {
    function PageService() {
        this.total = 0;
        this.pageIndex = 1;
        this.pageSize = 10;
        this.loading = false;
        this.pageSizeOpts = [10, 30, 50, 100, 200];
        this.layout = 'total, sizes, prev, pager, next, jumper';
    }
    /**
     * 获取分页配置信息
     */
    PageService.prototype.getConfig = function () {
        return {
            pageIndex: this.pageIndex,
            pageSize: this.pageSize
        };
    };
    /**
     * 更新分页配置信息
     * @param param
     */
    PageService.prototype.update = function (data) {
        this.total = parseInt(data.total);
        this.totalPage = parseInt(data.pageSize);
    };
    /**
     * 重置分页数据
     */
    PageService.prototype.reset = function () {
        this.total = 0;
        this.pageIndex = 1;
        this.loading = false;
    };
    return PageService;
}());
export { PageService };
