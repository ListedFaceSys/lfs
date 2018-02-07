package com.cscs.listedfacesys.dto;

/**
 * create by wzy on 2018/02/07
 * 预警Top10入参类
 */
public class WarningRiskInData {

    private Long userId;        //用户ID
    private String year;        //年份（YYYY）
    private int pageSize;       //页码
    private int pageCount;      //单页数据量

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
