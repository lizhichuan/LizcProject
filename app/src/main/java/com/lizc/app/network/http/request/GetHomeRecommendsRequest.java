package com.lizc.app.network.http.request;

public class GetHomeRecommendsRequest {
    /**
     * customerId : 2556
     * pageNo : 1
     * pageSize : 10
     */

    private String customerId;
    private String authorId;
    private int pageNo;
    private int pageSize;

    public String getAuthorId() {
        return authorId == null ? "" : authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
