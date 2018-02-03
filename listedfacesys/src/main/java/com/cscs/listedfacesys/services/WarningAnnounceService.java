package com.cscs.listedfacesys.services;

import com.cscs.listedfacesys.dto.base.BaseOutData;

import java.util.List;

/**
 * Create by wzy on 2018/2/1
 * 公告类相关
 */
public interface WarningAnnounceService {

    /**
     * 查询监测预警TOP10公司ID
     * @return
     */
    public List<Object> getWarningTop10();

    /**
     * 查询监测预警TOP10公司相关新闻详情
     * @param compyList
     * @return
     */
    public List<Object> getWarningTop5Content(String compyList);

}
