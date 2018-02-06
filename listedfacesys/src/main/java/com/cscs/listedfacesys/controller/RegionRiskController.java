package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.dto.*;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.services.NewsClassesService;
import com.cscs.listedfacesys.services.UserAttentionService;
import com.cscs.listedfacesys.services.WarningAnnounceService;
import com.cscs.util.SimilarityUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
    @Autowired
    UserAttentionService userAttentionService;

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

        List<WarningInfoData> warningInfoList = new ArrayList<>();

        List<Object> companyIdList = warningAnnounceService.getWarningTop10();
        String idList = "";
        for (Object it : companyIdList) {
            Object[] focusItem = (Object[]) it;
            idList += focusItem[1].toString() + ",";
        }
        if (idList.length() == 0) {
            logger.info("[未查询到ID数据]"+idList);
            return outData;
        }

        idList = idList.substring(0, idList.length() - 1);

        List<Object> contentList = warningAnnounceService.getWarningTop10Content(idList);
        Set<String> focusIds = userAttentionService.searchAllCompy(userId);

        warningInfoList = getWarningInfoData(contentList, focusIds, null, null);

        if (warningInfoList != null){
            Map<String, List<WarningInfoData>> data = new HashMap<>();
            data.put("warningDataList",warningInfoList);
            outData.setData(data);
        } else {
            logger.info("[公告数据处理异常]" + warningInfoList);
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
                            int year_Month = Integer.parseInt(postDt.substring(0,7));
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
                out.setData(map);
                out.setCode("1");
                out.setMessage("热点新闻，获取数据为空");
            }

        } catch (Exception e) {
            out.setData(map);
            out.setCode("-1");
            out.setMessage("热点新闻，获取数据异常！异常信息："+e.getMessage());
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


    //热点新闻趋势图(根据日期查询)
    @RequestMapping(value = "/newsChartByDate", method = RequestMethod.POST)
    public TendencyChartInfoData getNewsChartByDate(@RequestBody TendencyChartInData inData) {
        int newsCount = 0;
        int negativeNewsCount = 0;
        List<Object> itemList = new ArrayList<Object>();
        TendencyChartInfoData info = new TendencyChartInfoData();
        try {
            itemList = newsClassService.findchartByDate(inData);
            Object[] item = (Object[]) itemList.get(0);
            info.setNewCount(Integer.parseInt(item[0] != null ? item[0].toString() : "0"));
            info.setNegativeNewsCount(Integer.parseInt(item[1] != null ? item[1].toString() : "0"));
            info.setPostDt(item[2] != null ? item[2].toString() : "");
            if(info.getNewCount()==0){
                info.setRatio("0");
            }else {
                info.setRatio(String.format("%.2f", (double) info.getNegativeNewsCount() / info.getNewCount() * 100) + "%");
            }
        } catch (Exception e) {
            logger.error("热点新闻，获取数据异常！异常信息："+e.getMessage());
            e.printStackTrace();
        }

        return info;
    }


    //负面新闻跟踪
    @RequestMapping(value = "/lastingBondViolation/{page}", method = RequestMethod.GET)
    public BaseOutData getViolation(@PathVariable int page,@PathVariable int pageSize,@PathVariable String startDate,@PathVariable String endDate) {
        BaseOutData out = new BaseOutData();
        List<Object> itemList = new ArrayList<Object>();
        List<CompanyNewsOutData> reslist = new ArrayList<CompanyNewsOutData>();
        Map<String, List<CompanyNewsOutData>> map = new HashMap<String, List<CompanyNewsOutData>>();
        try {
            itemList =  newsClassService.getLastingBondViolationNews(page, pageSize,startDate,endDate);
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
                   outData.setCnn_score(item[7]!=null ? Integer.parseInt(item[3].toString()) : 0);
                   outData.setNewsSource(item[10]!=null ? item[10].toString() :"");
                   outData.setImportance(item[8]!=null ? item[8].toString() :"");
                   outData.setPlainText(item[4]!=null ? item[4].toString() :"");
                   outData.setRelevance(item[9]!=null ? item[9].toString() :"");
                   reslist.add(outData);
               }
                map.put("content", reslist);
                out.setCode("0");
                out.setData(map);
            }else{
                out.setCode("1");
                out.setData(map);
                out.setMessage("负面新闻跟踪，获取数据为空");
            }

        } catch (Exception e) {
            out.setCode("-1");
            out.setData(map);
            out.setMessage("负面新闻跟踪，获取异常，异常信息："+e.getMessage());
            e.printStackTrace();
        }


        return out;
    }

    //对公告信息列表进行处理及排序
    private List<WarningInfoData> getWarningInfoData(List<Object> contentList, Set<String> focusIds,Map compyMap,Map compyFromMap) {
        Map<String, WarningInfoData> outMap = new LinkedHashMap<String, WarningInfoData>();

        for (Object it : contentList) {
            Object[] item = (Object[]) it;
            String companyId = item[0].toString();
            WarningInfoData info = outMap.get(companyId);
            if (info == null) {
                info = new WarningInfoData();
                info.setCompanyId(companyId);
                if(compyMap != null && compyFromMap != null){
                    String focusId = compyMap.get(companyId).toString();
                    info.setFocusCompanyId(focusId);
                    info.setFocusCompanyNm(compyFromMap.get(focusId).toString());
                }
                info.setCompanyNm(item[1].toString());
                info.setFocused(focusIds.contains(companyId));
                outMap.put(companyId, info);
            }
            Map<String, List<String>> typeMap = info.getTypeMap();
            if (typeMap == null) {
                typeMap = new TreeMap<String, List<String>>();
                info.setTypeMap(typeMap);
            }

            String warnType = item[3].toString();
            List<String> warnTitles = typeMap.get(warnType);
            if (warnTitles == null) {
                warnTitles = new ArrayList<String>();
                typeMap.put(warnType, warnTitles);
            }
            if(!isDupliated(warnTitles, item[2].toString())){
                warnTitles.add(item[2].toString());
                info.setWarnCount(info.getWarnCount() + 1);
            } else {
                logger.info("[重复数据]" + item[2].toString());
            }
        }
        List<WarningInfoData> list = new ArrayList<>(outMap.values());
        Collections.sort(list, new Comparator<WarningInfoData>() {
            @Override
            public int compare(WarningInfoData o1, WarningInfoData o2) {
                if (o1 != null && o2 != null) {
                    return o2.getWarnCount() - o1.getWarnCount();
                }
                return 0;
            }
        });
        return list;
    }

    //字符串相似比较
    private boolean isDupliated(Collection<String> set, String s){
        for(String item : set){
            if(SimilarityUtil.isSimilar(item, s)){
                return true;
            }
        }
        return false;
    }

    //根据日期，生成该日期月份的所有日期的数据
    private  List<TendencyChartInfoData> getDaysStr(String date,List<TendencyChartInfoData> list){
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
