package com.cscs.listedfacesys.services;

import java.util.List;

/**
 * Create by wzy on 2018/2/1
 * 公司预警评估TOP10查询
 */
public interface WarningTopService {

    /**
     * 查询监测预警TOP10公司信息
     * @return
     */
    public List<Object> getWarningTop10();
}
