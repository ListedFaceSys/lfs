package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.dto.TendencyChartInData;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.services.WarningNewsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cscs.listedfacesys.services.NewsTableService;

/**
 * Create by wzy on 2018/2/1
 * 区域风险总览
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/regionRisk")
public class RegionRiskController {
    final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    NewsTableService newsTableService;
    @Autowired
    WarningNewsService warningNewsService;

    //查询预警趋势TOP10公司信息
    @RequestMapping(value = "/monitorWarning", method = RequestMethod.POST)
    public BaseOutData getWarningTop10(@RequestBody Long userId) {
        BaseOutData outData = new BaseOutData();

        return outData;
    }

    //新闻趋势图(组合)
    @RequestMapping(value = "/newsChart", method = RequestMethod.POST)
    public BaseOutData getNewsChart(@RequestBody TendencyChartInData inData) {
        BaseOutData outData = new BaseOutData();

        return outData;
    }

    //违约事件跟踪
    @RequestMapping(value = "/lastingBondViolation/{page}", method = RequestMethod.GET)
    public BaseOutData getViolation(@PathVariable int page) {
        BaseOutData out = new BaseOutData();
        try {
            out =  newsTableService.getLastingBondViolationNews(page, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

}
