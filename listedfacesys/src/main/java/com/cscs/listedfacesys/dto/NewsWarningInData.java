package com.cscs.listedfacesys.dto;

/**
 * Created by hj on 2018/2/2.
 * 热点新闻入参
 */
public class NewsWarningInData {
    //关键字
    public String keyword;
    //显示条数
    public int page;
    //页码
    public int pageSize;
    public Long compyId;
    //相关性
    public int relevance;
    //事件分类
    public String warningType;
    //时间分类(1:日/2:月/3:季)
    public int time;
    public Long userId;
    //新闻评价(正:1/负:2/中性:3)
    public int score;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getCompyId() {
        return compyId;
    }

    public void setCompyId(Long compyId) {
        this.compyId = compyId;
    }

    public int getRelevance() {
        return relevance;
    }

    public void setRelevance(int relevance) {
        this.relevance = relevance;
    }

    public String getWarningType() {
        return warningType;
    }

    public void setWarningType(String warningType) {
        this.warningType = warningType;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
