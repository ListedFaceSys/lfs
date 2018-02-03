package com.cscs.listedfacesys.dto;

public class TendencyChartInfoData {

    private int newCount;           //当天新闻总数量
    private int negativeNewsCount;      //当天负面新闻数量
    private String postDt;              //当天日期
    private String ratio;      //当日（总/负新闻）占比

    public int getNewCount() {
        return newCount;
    }

    public void setNewCount(int newCount) {
        this.newCount = newCount;
    }

    public int getNegativeNewsCount() {
        return negativeNewsCount;
    }

    public void setNegativeNewsCount(int negativeNewsCount) {
        this.negativeNewsCount = negativeNewsCount;
    }

    public String getPostDt() {
        return postDt;
    }

    public void setPostDt(String postDt) {
        this.postDt = postDt;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    @Override
    public String toString() {
        return "TendencyChartInfoData{" +
                "newCount=" + newCount +
                ", negativeNewsCount=" + negativeNewsCount +
                ", postDt='" + postDt + '\'' +
                ", ratio='" + ratio + '\'' +
                '}';
    }
}
