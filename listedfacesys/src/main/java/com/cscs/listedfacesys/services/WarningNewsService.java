package com.cscs.listedfacesys.services;

import com.cscs.listedfacesys.dto.base.BaseOutData;

import java.util.List;

/**
 * Create by wzy on 2018/2/1
 * 新闻类详情查询
 */
public interface WarningNewsService {

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

    /**
     * 负面新闻跟踪
     * 负面时间跟踪
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    public BaseOutData getLastingBondViolationNews(int page, int pageSize) throws Exception;
}
