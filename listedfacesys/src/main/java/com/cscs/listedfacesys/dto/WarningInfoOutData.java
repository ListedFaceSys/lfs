package com.cscs.listedfacesys.dto;

import com.cscs.listedfacesys.dto.base.BaseOutData;

import java.util.List;

/**
 * Create by wzy 2018/02/01
 */
public class WarningInfoOutData extends BaseOutData {

    private List<WarningInfoData> warningInfoDataList;

    public List<WarningInfoData> getWarningInfoDataList() {
        return warningInfoDataList;
    }

    public void setWarningInfoDataList(List<WarningInfoData> warningInfoDataList) {
        this.warningInfoDataList = warningInfoDataList;
    }
}
