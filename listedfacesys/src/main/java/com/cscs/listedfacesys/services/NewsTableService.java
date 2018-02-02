package com.cscs.listedfacesys.services;

import com.cscs.listedfacesys.dto.base.BaseOutData;

/**
 * Created by hj on 2018/02/01.
 * 新闻表格类
 */
public interface NewsTableService {

    /**
     * 违约事件跟踪
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    public BaseOutData getLastingBondViolationNews(int page, int pageSize) throws Exception;

}
