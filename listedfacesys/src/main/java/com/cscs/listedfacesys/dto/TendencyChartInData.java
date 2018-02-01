package com.cscs.listedfacesys.dto;

public class TendencyChartInData {

    private int time;        //时间分类(1:日/2:月/3:季)
    private Long userId;     //用户ID

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
}
