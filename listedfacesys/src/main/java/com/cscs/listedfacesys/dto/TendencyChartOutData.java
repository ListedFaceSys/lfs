package com.cscs.listedfacesys.dto;

import java.util.List;

public class TendencyChartOutData {

    private List<TendencyChartInfoData> singleNews;     //当月新闻信息
    private int totalCount;     //某年某月新闻总数
    private int negativeTotalCount;     //某年某月负面新闻总数
    private String totalRatio;     //某年某月（总/负新闻）总占比
    private String countDate;   //时间2018-01（表示这是哪年哪月的数据）
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

    public String getTotalRatio() {
        return totalRatio;
    }

    public void setTotalRatio(String totalRatio) {
        this.totalRatio = totalRatio;
    }

    public String getCountDate() {
        return countDate;
    }

    public void setCountDate(String countDate) {
        this.countDate = countDate;
    }

    @Override
    public String toString() {
        return "TendencyChartOutData{" +
                "singleNews=" + singleNews +
                ", totalCount=" + totalCount +
                ", negativeTotalCount=" + negativeTotalCount +
                ", totalRatio='" + totalRatio + '\'' +
                ", countDate='" + countDate + '\'' +
                '}';
    }
}
