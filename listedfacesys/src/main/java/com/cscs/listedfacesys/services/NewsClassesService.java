package com.cscs.listedfacesys.services;

import com.cscs.listedfacesys.dto.TendencyChartInData;
import com.cscs.listedfacesys.dto.base.BaseOutData;

import java.util.List;

/**
 * Created by hj on 2018/02/01.
 * 新闻类相关
 */
public interface NewsClassesService {


    /**
     * 负面时间跟踪
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    public BaseOutData getLastingBondViolationNews(int page, int pageSize) throws Exception;

    /**
     * 热点新闻
     * @param inData
     * @return
     * @throws Exception
     */
    public List<Object> findchart(TendencyChartInData inData) throws  Exception;
}
