package com.cscs.listedfacesys.dto;

/**
 * 负面新闻跟踪入参
 */
public class negativeNewsInData {
    private int page;
    private int pageSize;
    private String startDate;
    private String endDate;

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
