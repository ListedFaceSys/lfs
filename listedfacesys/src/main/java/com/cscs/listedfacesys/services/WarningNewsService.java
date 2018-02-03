package com.cscs.listedfacesys.services;

import com.cscs.listedfacesys.dto.base.BaseOutData;

import java.util.List;

/**
 * Create by wzy on 2018/2/1
 * 公司预警评估TOP10查询
 */
public interface WarningNewsService {

    /**
     * 查询监测预警TOP10公司信息
     * @return
     */
    public List<Object> getWarningTop10();

    /**
     * 负面时间跟踪
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    public BaseOutData getLastingBondViolationNews(int page, int pageSize) throws Exception;
}
