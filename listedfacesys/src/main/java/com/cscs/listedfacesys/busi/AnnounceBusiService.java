package com.cscs.listedfacesys.busi;

import com.cscs.listedfacesys.dto.WarningInfoData;
import com.cscs.listedfacesys.dto.WarningRiskInfoData;
import com.cscs.listedfacesys.dto.WarningRiskOutData;
import com.cscs.util.SimilarityUtil;
import com.cscs.util.StringUtil;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by wzy 2018/2/6
 */
public class AnnounceBusiService {
    static Logger logger = Logger.getLogger("MyLogger");

    /**
     * 对公告信息列表进行处理及排序
     * @param contentList
     * @param focusIds
     * @param compyMap
     * @param compyFromMap
     * @return
     */
    public static List<WarningInfoData> getWarningInfoData(List<Object> contentList, Set<String> focusIds, Map compyMap, Map compyFromMap) {
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

    /**
     * 字符串相似比较
     * @param set
     * @param s
     * @return
     */
    private static boolean isDupliated(Collection<String> set, String s){
        for(String item : set){
            if(SimilarityUtil.isSimilar(item, s)){
                return true;
            }
        }
        return false;
    }

    /**
     * 7年数据遍历处理方法
     * @param volumeData
     * @param startDate
     * @return
     */
    public static List<WarningRiskOutData> convert(List<Object> volumeData, String startDate) {
        Map<String, WarningRiskOutData> dataMap = new TreeMap<String, WarningRiskOutData>();
        List<WarningRiskOutData> issuedVolumeList = null;

        for (Object o: volumeData) {
            Object[] objs = (Object[]) o;
            String dateMonth = StringUtil.toString(objs[0]);
            List<Number> nb = new ArrayList<>();
            for (int i = 0;i < 5;i++) {
                nb.add((Number) objs[i+1]);
            }
            int risk1 = nb.get(0).intValue();
            int risk2 = nb.get(1).intValue();
            int risk3 = nb.get(2).intValue();
            int risk4 = nb.get(3).intValue();
            int risk5 = nb.get(4).intValue();

            if (dateMonth != null && dateMonth.length() == 6) {
                String year = dateMonth.substring(0, 4);
                Integer month = Integer.parseInt(dateMonth.substring(4, 6));

                WarningRiskOutData riskData = dataMap.get(year);
                if (riskData == null) {
                    riskData = new WarningRiskOutData();
                    riskData.setDate(year);
                    dataMap.put(year, riskData);
                }

                List<WarningRiskInfoData> riskByYear = riskData.getWarningRiskInfoDataList();
                if (riskByYear == null) {
                    riskByYear = new ArrayList<WarningRiskInfoData>();
                    for (int i = 0; i < 12; i++) {
                        riskByYear.add(i,new WarningRiskInfoData());
                        riskByYear.get(i).setDataMonth(i + 1);
                    }
                }
                riskByYear.get(month - 1).setRisk1(risk1);
                riskByYear.get(month - 1).setRisk2(risk2);
                riskByYear.get(month - 1).setRisk3(risk3);
                riskByYear.get(month - 1).setRisk4(risk4);
                riskByYear.get(month - 1).setRisk5(risk5);

                riskData.setWarningRiskInfoDataList(riskByYear);
            }
        }

        issuedVolumeList = new ArrayList<WarningRiskOutData>();
        for (int i = 0; i < 7; i++) {
            int yearByI = Integer.valueOf(startDate);;
            WarningRiskOutData single = null;
            if (dataMap.get(String.valueOf(yearByI + i)) == null ){
                single = new WarningRiskOutData();
                single.setDate(String.valueOf(yearByI + i));
            } else {
                single = dataMap.get(String.valueOf(yearByI + i));
            }
            issuedVolumeList.add(i,single);
        }

        return issuedVolumeList;
    }

}
