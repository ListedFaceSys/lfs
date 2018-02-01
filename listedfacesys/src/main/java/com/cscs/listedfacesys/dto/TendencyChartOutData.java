package com.cscs.listedfacesys.dto;

import java.util.List;

public class TendencyChartOutData {

    private List<TendencyChartInfoData> singleNews;     //每天新闻数量信息
    private int totalCount;     //当月正面新闻总数
    private int negativeTotalCount;     //当月负面新闻总数
    private int totalRatio;     //当月（总/负新闻）总占比

    public List<TendencyChartInfoData> getSingleNews() {
        return singleNews;
    }

    public void setSingleNews(List<TendencyChartInfoData> singleNews) {
        this.singleNews = singleNews;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getNegativeTotalCount() {
        return negativeTotalCount;
    }

    public void setNegativeTotalCount(int negativeTotalCount) {
        this.negativeTotalCount = negativeTotalCount;
    }

    public int getTotalRatio() {
        return totalRatio;
    }

    public void setTotalRatio(int totalRatio) {
        this.totalRatio = totalRatio;
    }
}
