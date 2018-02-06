package com.cscs.listedfacesys.dto;

public class WarningRiskInfoData {

    private int risk1;       //治理风险数量
    private int risk2;       //财务风险数量
    private int risk3;       //经营风险数量
    private int risk4;       //市场风险数量
    private int risk5;       //法律法规风险数量
    private int dataMonth;        //月日期

    public int getRisk1() {
        return risk1;
    }

    public void setRisk1(int risk1) {
        this.risk1 = risk1;
    }

    public int getRisk2() {
        return risk2;
    }

    public void setRisk2(int risk2) {
        this.risk2 = risk2;
    }

    public int getRisk3() {
        return risk3;
    }

    public void setRisk3(int risk3) {
        this.risk3 = risk3;
    }

    public int getRisk4() {
        return risk4;
    }

    public void setRisk4(int risk4) {
        this.risk4 = risk4;
    }

    public int getRisk5() {
        return risk5;
    }

    public void setRisk5(int risk5) {
        this.risk5 = risk5;
    }

    public int getDataMonth() {
        return dataMonth;
    }

    public void setDataMonth(int dataMonth) {
        this.dataMonth = dataMonth;
    }
}
