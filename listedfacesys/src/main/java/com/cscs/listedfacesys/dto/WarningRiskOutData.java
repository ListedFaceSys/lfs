package com.cscs.listedfacesys.dto;

import java.util.List;

public class WarningRiskOutData {

    private List<WarningRiskInfoData> warningRiskInfoDataList; //每年的数据量
    private int risk1Count;     //治理风险总数量
    private int risk2Count;     //财务风险总数量
    private int risk3Count;     //经营风险总数量
    private int risk4Count;     //市场风险总数量
    private int risk5Count;     //法律法规风险总数量
    private String date;        //年日期（YYYY）

}
