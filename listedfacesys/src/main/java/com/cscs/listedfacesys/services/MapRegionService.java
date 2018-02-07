package com.cscs.listedfacesys.services;

import java.util.List;

/**
 * create by wzy on 2018/02/07
 * 地图类查询
 */
public interface MapRegionService {

    /**
     * 查询区域内公司数量
     * @return
     */
    public List<Object> getRegionCompanyCount();
}
