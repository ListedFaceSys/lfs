package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.busi.AnnounceBusiService;
import com.cscs.listedfacesys.dto.*;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.services.MapRegionService;
import com.cscs.listedfacesys.services.NewsClassesService;
import com.cscs.listedfacesys.services.UserAttentionService;
import com.cscs.listedfacesys.services.WarningAnnounceService;
import com.cscs.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Create by wzy on 2018/2/1
 * 区域风险总览
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/regionRisk")
public class RegionRiskController {
    final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    NewsClassesService newsClassService;
    @Autowired
    WarningAnnounceService warningAnnounceService;
    @Autowired
    UserAttentionService userAttentionService;
    @Autowired
    MapRegionService mapRegionService;

    //地图分布一览查询
    @RequestMapping(value = "/companyMap", method = RequestMethod.GET)
    public BaseOutData getCompanyMap() {
        BaseOutData outData = new BaseOutData();

        RegionMapInfoData regionMapInfoData = new RegionMapInfoData();

        List<Object> mapList = mapRegionService.getRegionCompanyCount();

        return outData;
    }

    //同比增长数据
    @RequestMapping(value = "/companyMapRatio", method = RequestMethod.GET)
    public BaseOutData getCompanyMapRation() {
        BaseOutData outData = new BaseOutData();

        SimpleDateFormat df = new SimpleDateFormat("YYYYMM");
        String date = df.format(new Date());

        return outData;
    }

    //上市公司预警趋势图
    @RequestMapping(value = "/warningChart/{startDate}/{endDate}", method = RequestMethod.GET)
    public BaseOutData getWarningChart(@PathVariable String startDate, @PathVariable String endDate) {
        BaseOutData outData = new BaseOutData();
        Map<String, List<WarningRiskOutData>> data = new HashMap<>();
        List<WarningRiskOutData> warningRiskList = new ArrayList<>();

        String startDateToMM = startDate + "01";
        String endDateToMM = endDate + "12";

        List<Object> sevYearDataList = warningAnnounceService.getWarningYearCount(startDateToMM, endDateToMM);

        if (sevYearDataList.size() == 0) {
            outData.setCode("1");
            outData.setMessage("The query fails!");
            logger.info("[未查询到风险数据信息]");
        }

        warningRiskList = AnnounceBusiService.convert(sevYearDataList, startDate);

        if (warningRiskList != null) {
            data.put("warningRiskOutDataList", warningRiskList);
            outData.setCode("0");
            outData.setMessage("The query is successful!");
            outData.setData(data);
            logger.info("[查询成功]"+warningRiskList);
        } else {
            outData.setCode("-1");
            outData.setMessage("The background anomaly!");
            logger.info("[公告数据处理异常]");
        }
        return outData;
    }

    //上市公司预警趋势图当前月显示
    @RequestMapping(value = "/warningChartSingle", method = RequestMethod.GET)
    public BaseOutData getWarningChartSingle() {
        BaseOutData outData = new BaseOutData();
        Map<String, WarningRiskInfoData> data = new HashMap<>();
        WarningRiskInfoData warningRiskInfoData = new WarningRiskInfoData();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        String month = df.format(new Date());
        String monthFormat = new SimpleDateFormat("yyyy-MM").format(new Date());
        List<Object> monthData = warningAnnounceService.getWarningMonthCount(month);

        if (monthData == null) {
            outData.setCode("1");
            outData.setMessage("The query fails!");
            logger.info("[未查询到本月风险数据信息]");
            return outData;
        } else {
            Object[] objs = (Object[]) monthData.get(0);
            List<Number> nb = new ArrayList<>();
            for (int i = 0;i < 5;i++) {
                nb.add((Number) objs[i]);
            }
            warningRiskInfoData.setRisk1(nb.get(0).intValue());
            warningRiskInfoData.setRisk2(nb.get(1).intValue());
            warningRiskInfoData.setRisk3(nb.get(2).intValue());
            warningRiskInfoData.setRisk4(nb.get(3).intValue());
            warningRiskInfoData.setRisk5(nb.get(4).intValue());
            warningRiskInfoData.setDataMonth(Integer.valueOf(monthFormat));
        }

        data.put("monthData", warningRiskInfoData);
        outData.setCode("0");
        outData.setMessage("The query is successful!");
        outData.setData(data);
        logger.info("[查询成功]"+warningRiskInfoData);

        return outData;
    }

    //查询预警趋势TOP10公司信息
    @RequestMapping(value = "/warningTop", method = RequestMethod.POST)
    public BaseOutData getWarningTop10(@RequestBody WarningRiskInData inData) {
        BaseOutData outData = new BaseOutData();

        List<WarningInfoData> warningInfoList = new ArrayList<>();
        String dateStart = inData.getYear();
        String dateEnd = "";
        String idList = "";

        dateStart = dateStart + "01";
        dateEnd = String.valueOf((Integer.parseInt(dateStart) + 11));

        List<Object> companyIdList = warningAnnounceService.getWarningTop10(dateStart, dateEnd, inData.getPageSize(), inData.getPageCount());

        if (companyIdList.size() == 0) {
            outData.setCode("1");
            outData.setMessage("The ID data is not queried!");
            logger.info("[未查询到公司ID数据]");
            return outData;
        }
        for (Object it : companyIdList) {
            Object[] focusItem = (Object[]) it;
            idList += focusItem[1].toString() + ",";
        }

        idList = idList.substring(0, idList.length() - 1);

        List<Object> contentList = warningAnnounceService.getWarningTop10Content(idList, dateStart, dateEnd);

        if (contentList.size() == 0) {
            outData.setCode("1");
            outData.setMessage("The Announce data is not queried!");
            logger.info("[未查询到公告数据]");
            return outData;
        }

        List<Object> countList = warningAnnounceService.getWarningCpCount(dateStart, dateEnd);
        logger.info("今年公司总数：" + countList);

        if (countList.get(0) == null) {
            outData.setCode("1");
            outData.setMessage("The Count data is not queried!");
            logger.info("[未查询到更多总数数据]");
        }

        BigDecimal bd = (BigDecimal) countList.get(0);
        int count = bd.intValue();

        Set<String> focusIds = userAttentionService.searchAllCompy(inData.getUserId());
        warningInfoList = AnnounceBusiService.getWarningInfoData(contentList, focusIds, null, null);

        if (warningInfoList != null){
            Map<String, List<WarningInfoData>> data = new HashMap<>();
            data.put("warningDataList",warningInfoList);
            outData.setData(data);
            outData.setCount(count);
            outData.setCode("0");
            outData.setMessage("The query is successful!");
            logger.info("[查询成功]"+warningInfoList);
        } else {
            outData.setCode("-1");
            outData.setMessage("The background anomaly!");
            logger.info("[公告数据处理异常]");
        }

        return outData;
    }

    //热点新闻趋势图(组合)
    @RequestMapping(value = "/newsChart", method = RequestMethod.POST)
    public BaseOutData getNewsChart(@RequestBody TendencyChartInData inData) {
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
        for (int w =dateStr.length-1 ; w >= 0; w--) {
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
        Map<String,List<TendencyChartOutData>> map = new HashMap<String,List<TendencyChartOutData>>();
        try {
            itemList = newsClassService.findchart(inData);
            List<TendencyChartOutData>  outList = new ArrayList<TendencyChartOutData>();
            if(itemList!=null && itemList.size()>0){
                for (int j=0;j<dateStr.length;j++){
                    List<TendencyChartInfoData> list = new ArrayList<TendencyChartInfoData>();
                    TendencyChartOutData outData = new TendencyChartOutData();
                    for (int i = 0; i < itemList.size(); i++) {
                        TendencyChartInfoData info = new TendencyChartInfoData();
                        Object[] item = (Object[]) itemList.get(i);
                        info.setNewCount(Integer.parseInt(item[0] != null ? item[0].toString() : "0"));
                        info.setNegativeNewsCount(Integer.parseInt(item[1] != null ? item[1].toString() : "0"));
                        info.setPostDt(item[2] != null ? item[2].toString() : "");
                        if(info.getNewCount()==0){
                            info.setRatio("0");
                        }else {
                            info.setRatio(String.format("%.2f", (double) info.getNegativeNewsCount() / info.getNewCount() * 100) + "%");
                        }
                        //按年月对数据进行分组
                        String postDt = item[2] != null ? item[2].toString() : "";
                        if(null != postDt && !"".equals(postDt)){
                            String year_Month = postDt.substring(0,7);
                            if(dateStr[j].equals(year_Month)){
                                list.add(info);
                            }
                        }
                   /* newsCount += item[0] != null ? Integer.valueOf(item[0].toString()) : 0;
                    negativeNewsCount += item[1] != null ? Integer.valueOf(item[1].toString()) : 0;*/
                    }
                /*outData.setNegativeTotalCount(negativeNewsCount);
                outData.setTotalCount(newsCount);
                if(newsCount==0){
                    outData.setTotalRatio("0");
                }else {
                    outData.setTotalRatio(String.format("%.2f", (double) negativeNewsCount / newsCount * 100) + "%");
                }*/
                    outData.setCountDate(dateStr[j]);

                    //根据日期，生成该日期月份的所有日期的数据
                    List<TendencyChartInfoData> reslist =getDaysStr(dateStr[j],list);

                    outData.setSingleNews(reslist);
                    outList.add(outData);
                }

                map.put("conent",outList);
                out.setData(map);
                out.setCode("0");
            }else{
                out.setCode("1");
                out.setMessage("热点新闻，获取数据为空");
            }

        } catch (Exception e) {
            out.setCode("-1");
            out.setMessage("热点新闻，获取数据异常！异常信息："+e.getMessage());
            logger.error("热点新闻，获取数据异常！异常信息："+e.getMessage());
            e.printStackTrace();
        }

        return out;

    }


    //热点新闻趋势图(根据日期查询)
    @RequestMapping(value = "/newsChartByDate", method = RequestMethod.POST)
    public BaseOutData getNewsChartByDate(@RequestBody TendencyChartInData inData) {
        BaseOutData out =  new BaseOutData();
        int newsCount = 0;
        int negativeNewsCount = 0;
        List<Object> itemList = new ArrayList<Object>();
        TendencyChartInfoData info = new TendencyChartInfoData();
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            itemList = newsClassService.findchartByDate(inData);
            if(itemList!=null && itemList.size()>0){
                Object[] item = (Object[]) itemList.get(0);
                info.setNewCount(Integer.parseInt(item[0] != null ? item[0].toString() : "0"));
                info.setNegativeNewsCount(Integer.parseInt(item[1] != null ? item[1].toString() : "0"));
                info.setPostDt(item[2] != null ? item[2].toString() : "");
                if(info.getNewCount()==0){
                    info.setRatio("0");
                }else {
                    info.setRatio(String.format("%.2f", (double) info.getNegativeNewsCount() / info.getNewCount() * 100) + "%");
                }
                map.put("content",info);
                out.setCode("0");
                out.setData(map);
            }else{
                out.setCode("1");
                logger.error("热点新闻，获取数据为空");
            }

        } catch (Exception e) {
            out.setCode("-1");
            out.setMessage("热点新闻，获取数据异常！异常信息："+e.getMessage());
            logger.error("热点新闻，获取数据异常！异常信息："+e.getMessage());
            e.printStackTrace();
        }

        return out;
    }


    //负面新闻跟踪
    @RequestMapping(value = "/lastingBondViolation", method = RequestMethod.POST)
    public BaseOutData getViolation(@RequestBody negativeNewsInData inData) {
        BaseOutData out = new BaseOutData();
        List<Object> itemList = new ArrayList<Object>();
        List<CompanyNewsOutData> reslist = new ArrayList<CompanyNewsOutData>();
        Map<String, List<CompanyNewsOutData>> map = new HashMap<String, List<CompanyNewsOutData>>();
        try {
            //查询负面新闻总数
            int count =  newsClassService.getLastingBondViolationNewsCount(inData.getStartDate(),inData.getEndDate());
            itemList =  newsClassService.getLastingBondViolationNews(inData.getPage(), inData.getPageSize(),inData.getStartDate(),inData.getEndDate());
            if(itemList !=null && itemList.size()>0){
               for (int i = 0; i <itemList.size() ; i++) {
                   Object[] item = (Object[]) itemList.get(i);
                   CompanyNewsOutData outData = new CompanyNewsOutData();
                   String compyId = item[6].toString();
                   if(!StringUtils.isEmpty(compyId)){
                       List<Object> obj = newsClassService.findCompanyNm(compyId);
                       if(obj.size() > 0){
                           Object[] info = (Object[])obj.get(0);
                           outData.setCompanyId(info[0].toString());
                           outData.setCompanyNm(info[1].toString());
                       }else{
                           logger.info("根据公司ID:"+compyId+",查询公司名称，为空");
                       }
                   }
                   String title = item[2]!=null ? item[2].toString() : "";
                   outData.setTitle(title.replaceAll("\\\\", ""));
                   outData.setUrl(item[5]!=null ? item[5].toString() :"");
                   outData.setDate(item[3]!=null ? item[3].toString() :"");
                   outData.setCnn_score(item[7]!=null ? Integer.parseInt(item[7].toString()) : 0);
                   outData.setNewsSource(item[10]!=null ? item[10].toString() :"");
                   outData.setImportance(item[8]!=null ? item[8].toString() :"");
                   outData.setPlainText(item[4]!=null ? item[4].toString() :"");
                   outData.setRelevance(item[9]!=null ? item[9].toString() :"");
                   outData.setPostDt(item[11]!=null ? item[11].toString() :"");
                   outData.setNewsCode(item[1]!=null ? item[1].toString() :"");
                   reslist.add(outData);
               }
                map.put("content", reslist);
                out.setCode("0");
                out.setData(map);
                out.setCount(count);
            }else{
                out.setCode("1");
                out.setMessage("负面新闻跟踪，获取数据为空");
            }

        } catch (Exception e) {
            out.setCode("-1");
            out.setMessage("负面新闻跟踪，获取异常，异常信息："+e.getMessage());
            e.printStackTrace();
        }


        return out;
    }


    //负面新闻详情
    @RequestMapping(value = "/newsContent/{compyId}/{newsCode}", method = RequestMethod.GET)
    public BaseOutData getNewsContent(@PathVariable long compyId, @PathVariable String newsCode) {
        BaseOutData out = new BaseOutData();
        NewsWarningOutData outData = new NewsWarningOutData();
        List<Object> itemList = null;
        try {
            itemList = newsClassService.findNewsContent(compyId, newsCode);
            if (itemList.size() > 0) {
                Object[] item = (Object[]) itemList.get(0);
                outData.setWarningType(item[0] != null ? item[0].toString() : "");
                outData.setNewsTitle(item[1] != null ? item[1].toString() : "");
                outData.setPublishTime(item[3] != null ? item[3].toString() : "");
                outData.setNewsSource(item[4] != null ? item[4].toString() : "");
                outData.setRelevance(item[5] != null ? item[5].toString() : "");
                outData.setScore(item[6] != null ? item[6].toString() : "");
                outData.setContent(item[7] != null ? StringUtil.toString(item[7]) : "");
                outData.setImportance(item[8] != null ? item[8].toString() : "");

                Map sheetLabel = new HashMap();
                List<Object> labelList = newsClassService.findNewsType(compyId, newsCode);
                if(labelList!=null && labelList.size()>0){
                    for (int i = 0; i < labelList.size(); i++) {
                        Object[] label = (Object[]) labelList.get(i);
                        sheetLabel.put(label[0], label[1]);
                    }
                }else{
                    out.setCode("1");
                    out.setMessage("获取负面跟踪新闻事件信息，获取为空");
                }

                outData.setSheetLabel(sheetLabel);
                Map<String,Object> resMap = new HashMap<String,Object>();
                resMap.put("content",outData);
                out.setCode("0");
                out.setData(resMap);
            }else{
                out.setCode("1");
                out.setMessage("负面跟踪新闻详细信息，获取为空");
            }
        } catch (Exception e) {
            out.setCode("-1");
            out.setMessage("负面跟踪新闻详细信息，获取异常,异常信息："+e.getMessage());
            e.printStackTrace();
        }

        return out;
    }


    //根据日期，生成该日期月份的所有日期的数据
    private  List<TendencyChartInfoData> getDaysStr(String date,List<TendencyChartInfoData> list){
        date=date+"-01";
        List<TendencyChartInfoData> resList = new ArrayList<TendencyChartInfoData>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取某月的天数
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(date));
        } catch (ParseException e) {
            this.logger.error("根据日期，获取改日期月份的所有日期,日期转换异常，异常信息："+e.getMessage());
        }
        int daysSize =calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        String[] daysStr = new String[daysSize];
        String yearMonth = date.substring(0,7);
        //生成该月份的所有日期
        for (int i =0 ; i < daysStr.length; i++) {
            if(i<9){
                daysStr[i] = yearMonth+"-0"+(i+1);
            }else {
                daysStr[i] = yearMonth+"-"+(i+1);
            }
        }

        for (int j=0;j<daysStr.length;j++){
            boolean flag = true;//标识list中没有当天日期的数据
            for (int k=0;k<list.size();k++){
                TendencyChartInfoData info = list.get(k);
                if(null!=info.getPostDt() && !"".equals(info.getPostDt())){
                    if(daysStr[j].equals(info.getPostDt())){
                        resList.add(info);
                        flag = false;
                        break;
                    }
                }
            }
            //当list中没有该日期的数据时，手动add空的对象进去
            if(flag){
                TendencyChartInfoData infoData = new TendencyChartInfoData();
                infoData.setNewCount(0);
                infoData.setNegativeNewsCount(0);
                infoData.setRatio("0");
                infoData.setPostDt(daysStr[j]);
                resList.add(infoData);
            }
        }

        return resList;
    }


}
