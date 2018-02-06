package com.cscs.listedfacesys.services;

import com.cscs.listedfacesys.dto.TendencyChartInData;

import java.util.List;

/**
 * Created by hj on 2018/02/01.
 * 新闻类相关
 */
public interface NewsClassesService {


    /**
     * 负面时间跟踪
     * @param page 页数
     * @param pageSize 页量
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     * @throws Exception
     */
    public List<Object> getLastingBondViolationNews(int page, int pageSize,String startDate,String endDate) throws Exception;

    /**
     * 查询公司名称
     * @param compyId
     * @return
     */
    public List<Object> findCompanyNm(String compyId);
    /**
     * 热点新闻
     * @param inData
     * @return
     * @throws Exception
     */
    public List<Object> findchart(TendencyChartInData inData) throws  Exception;

    /**
     * 热点新闻，根据日期查询该日期当天的新闻信息
     * @param inData
     * @return
     * @throws Exception
     */
    public List<Object> findchartByDate(TendencyChartInData inData) throws Exception;
}
