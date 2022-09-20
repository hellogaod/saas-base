package com.base.saas.common.page;


import com.base.saas.common.constant.AppConstant;

import java.io.Serializable;

public class BasePageInfo implements Serializable {

    private static final long serialVersionUID = 7675092750266630611L;

    /**
     * 当前页
     */
    private Integer pageIndex = AppConstant.DEFAULT_PAGE;

    /**
     * 每页多少条
     */
    private Integer pageSize = AppConstant.DEFAULT_LIMIT;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
