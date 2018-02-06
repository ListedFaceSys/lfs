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
    public List<Object> getWarningTop10(String dataStart, String dateEnd);

    /**
     * 查询监测预警TOP10公司相关公告详情
     * @param compyList
     * @return
     */
    public List<Object> getWarningTop10Content(String compyList);

    /**
     * 查询最近七年相关公告条数
     * @return
     */
    public List<Object> getWarningYearCount(String startDate, String endDate);

    /**
     * 查询当月相关公告条数
     * @return
     */
    public Object getWarningMonthCount(String date);

}
