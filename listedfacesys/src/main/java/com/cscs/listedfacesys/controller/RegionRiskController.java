package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.dto.TendencyChartInData;
import com.cscs.listedfacesys.dto.WarningInfoData;
import com.cscs.listedfacesys.dto.WarningOutData;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.services.WarningNewsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cscs.listedfacesys.services.NewsCountService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    NewsCountService newsCountService;
    @Autowired
    WarningNewsService warningNewsService;

    //查询预警趋势TOP10公司信息
    @RequestMapping(value = "/monitorWarning/{userId}", method = RequestMethod.GET)
    public BaseOutData getWarningTop10(@PathVariable Long userId) {
        BaseOutData outData = new BaseOutData();
        Map<String, List<WarningInfoData>> data = new HashMap<>();
        List<WarningInfoData> warningInfoDataList = new LinkedList<>();


//        for (int k = 0; k < 11; k++) {
//            WarningInfoData wi = new WarningInfoData();
//            Map<String,Object> list = new HashMap<String, Object>();
//            List<String> typeName = new LinkedList<>();
//            List<String> title = new LinkedList<>();
//
//            wi.setCompanyId("12321");
//            wi.setCompanyNm("巴啦啦魔仙公司");
//
//            for(int i = 0; i < Math.random()*9+1; i++) {
//                typeName.add("小标题" + i);
//            }
//            logger.info(typeName);
//            for(int j = 0; j < 6; j++) {
//                title.add("大标题" + j);
//            }
//            list.put("typeName",typeName);
//            list.put("title",title);
//            wi.setTypeMap(list);
//            warningInfoDataList.add(wi);
//        }
//
//        data.put("warningTop", warningInfoDataList);
//        outData.setData(data);
//        outData.setCode("1");
//        outData.setMessage("成功");


        return outData;
    }

    //热点新闻趋势图(组合)
    @RequestMapping(value = "/newsChart", method = RequestMethod.POST)
    public BaseOutData getNewsChart(@RequestBody TendencyChartInData inData) {
        BaseOutData outData = new BaseOutData();

        return outData;
    }

    //负面新闻跟踪
    @RequestMapping(value = "/lastingBondViolation/{page}", method = RequestMethod.GET)
    public BaseOutData getViolation(@PathVariable int page) {
        BaseOutData out = new BaseOutData();
        try {
            out =  warningNewsService.getLastingBondViolationNews(page, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

}
