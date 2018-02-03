package com.cscs.listedfacesys.services;

import com.cscs.listedfacesys.dto.NewsWarningInData;

import java.util.List;

/**
 * Created by hj on 2018/02/01.
 * 与查询新闻数量有关的数据层
 */
public interface NewsCountService {
    /**
     * 热点新闻
     * @param inData
     * @return
     * @throws Exception
     */
    public List<Object> findchartGroup(NewsWarningInData inData) throws  Exception;
}
