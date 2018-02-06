package com.cscs.listedfacesys.dto;

/**
 * Created by hj on 2018/2/2.
 * 热点新闻入参
 */
public class TendencyChartInData {

    public Long userId;
    //热点新闻发布日期
    public String pos_Dt;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPos_Dt() {
        return pos_Dt;
    }

    public void setPos_Dt(String pos_Dt) {
        this.pos_Dt = pos_Dt;
    }
}
