package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.dto.*;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.services.NewsClassesService;
import com.cscs.listedfacesys.services.WarningAnnounceService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

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
    NewsClassesService newsClassService;
    @Autowired
    WarningAnnounceService warningAnnounceService;

    //地图分布一览查询
    @RequestMapping(value = "/companyMap/{userId}", method = RequestMethod.GET)
    public BaseOutData getCompanyMap(@PathVariable Long userId) {
        BaseOutData outData = new BaseOutData();

        return outData;
    }

    //上市公司预警趋势图
    @RequestMapping(value = "/warningChart", method = RequestMethod.POST)
    public BaseOutData getWarningChart(@RequestBody WarningInData inData) {
        BaseOutData outData = new BaseOutData();

        return outData;
    }

    //查询预警趋势TOP10公司信息
    @RequestMapping(value = "/warningTop/{userId}", method = RequestMethod.GET)
    public BaseOutData getWarningTop10(@PathVariable Long userId) {
        BaseOutData outData = new BaseOutData();

        return outData;
    }

    //热点新闻趋势图(组合)
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
            out =  newsClassService.getLastingBondViolationNews(page, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    //新闻热点
    @RequestMapping(value = "/chartGroup", method = RequestMethod.POST)
    public BaseOutData getChartGroup(@RequestBody NewsWarningInData inData) {
        int newsCount = 0;
        int negativeNewsCount = 0;
        BaseOutData out = new BaseOutData();
        List<Object> itemList = new ArrayList<Object>();
        //根据当前日期，生成包含当前日期前7个月的年月
        Date dt = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDateStr = sdf.format(dt);
        String[] dateStr = new String[7];
        int year = Integer.parseInt(nowDateStr.substring(0,4));
        int month = Integer.parseInt(nowDateStr.substring(5,7));
        for (int w = 0; w < 7; w++) {
            if((month-1)<=0){
                month=12;
                year=year-1;
                dateStr[w] = year+"-"+month;
            }else{
                String mon = (month-1)+"";
                if(mon.length()==1){
                    dateStr[w] = year+"-0"+mon;
                }else{
                    dateStr[w] = year+"-"+mon;
                }
                month = month-1;
            }
        }
        try {
            itemList = newsClassService.findchart(inData);
            List<TendencyChartOutData>  outList = new ArrayList<TendencyChartOutData>();
            for (int j=0;j<dateStr.length;j++){
                List<TendencyChartInfoData> list = new ArrayList<TendencyChartInfoData>();
                TendencyChartOutData outData = new TendencyChartOutData();
                for (int i = 0; i < itemList.size(); i++) {
                    TendencyChartInfoData info = new TendencyChartInfoData();
                    Object[] item = (Object[]) itemList.get(i);
                    info.setNewCount(Integer.parseInt(item[0] != null ? item[0].toString() : "0"));
                    info.setNegativeNewsCount(Integer.parseInt(item[1] != null ? item[1].toString() : "0"));
                    info.setPostDt(item[2] != null ? item[2].toString() : "");
                    info.setRatio(String.format("%.2f", (double) info.getNegativeNewsCount() / info.getNewCount() * 100) + "%");
                    //按年月对数据进行分组
                    String postDt = item[2] != null ? item[2].toString() : "";
                    if(null != postDt && !"".equals(postDt)){
                        int year_Month = Integer.parseInt(postDt.substring(0,7));
                        if(dateStr[j].equals(year_Month)){
                            list.add(info);
                        }
                    }
                    newsCount += item[0] != null ? Integer.valueOf(item[0].toString()) : 0;
                    negativeNewsCount += item[1] != null ? Integer.valueOf(item[1].toString()) : 0;
                }
                outData.setNegativeTotalCount(negativeNewsCount);
                outData.setTotalCount(newsCount);
                outData.setTotalRatio(String.format("%.2f", (double) negativeNewsCount / newsCount * 100) + "%");
                outData.setCountDate(dateStr[j]);
                outData.setSingleNews(list);

                outList.add(outData);
            }
            Map<String,List<TendencyChartOutData>> map = new HashMap<String,List<TendencyChartOutData>>();
            map.put("conent",outList);
            out.setData(map);
        } catch (Exception e) {
            logger.error("热点新闻，获取数据异常！异常信息："+e.getMessage());
            e.printStackTrace();
        }

        return out;


        //---------------------------- 以下为mock数据代码 ------------------------------------------
//        //根据当前日期，生成包含当前日期前7个月的年月
//        Date dt = new Date(System.currentTimeMillis());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String nowDateStr = sdf.format(dt);
//        String[] dateStr = new String[7];
//        int year = Integer.parseInt(nowDateStr.substring(0,4));
//        int month = Integer.parseInt(nowDateStr.substring(5,7));
//        for (int w = 0; w < 7; w++) {
//            if((month-1)<=0){
//                month=12;
//                year=year-1;
//                dateStr[w] = year+"-"+month;
//            }else{
//                String mon = (month-1)+"";
//                if(mon.length()==1){
//                    dateStr[w] = year+"-0"+mon;
//                }else{
//                    dateStr[w] = year+"-"+mon;
//                }
//                month = month-1;
//            }
//        }
//        //mock生成数据
//        List<TendencyChartOutData>  outList = new ArrayList<TendencyChartOutData>();
//        BaseOutData out = new BaseOutData();
//        for (int j=0;j<dateStr.length;j++){
//            int newsCount = 0;
//            int negativeNewsCount = 0;
//            List<TendencyChartInfoData> list = new ArrayList<TendencyChartInfoData>();
//            TendencyChartOutData outData = new TendencyChartOutData();
//            for (int i=1;i<=10;i++){
//                TendencyChartInfoData info = new TendencyChartInfoData();
//                info.setNegativeNewsCount(3+i);
//                info.setNewCount(10+i);
//                info.setPostDt(dateStr[j]+"-0"+i);
//                info.setRatio(String.format("%.2f", (double) info.getNegativeNewsCount() / info.getNewCount() * 100) + "%");
//                newsCount += info.getNewCount();
//                negativeNewsCount += info.getNegativeNewsCount();
//                list.add(info);
//            }
//            outData.setNegativeTotalCount(negativeNewsCount);
//            outData.setTotalCount(newsCount);
//            outData.setTotalRatio(String.format("%.2f", (double) negativeNewsCount / newsCount * 100) + "%");
//            outData.setCountDate(dateStr[j]);
//            outData.setSingleNews(list);
//
//            outList.add(outData);
//        }
//        Map<String,List<TendencyChartOutData>> map = new HashMap<String,List<TendencyChartOutData>>();
//        map.put("conent",outList);
//        out.setData(map);
//
//        return out;
    }



}
