package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.dto.NewsTableOutData;
import com.cscs.listedfacesys.dto.TendencyChartInData;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.services.NewsClassesService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hj on 2018/02/01.
 * 新闻表格类实现类
 */
@Service
public class NewsClassesServiceImpl implements NewsClassesService {

    @Override
    public BaseOutData getLastingBondViolationNews(int page, int pageSize) throws Exception {
        BaseOutData out = new BaseOutData();
        int startRow = page * pageSize;

//        String uri = Contants.NEWS_SERVER_URL + "q=cnn_score:[-2%20TO%20-1]%20AND%20relevance:[0.8%20TO%20*]&start=" + startRow + "&rows=" + pageSize;
//        System.out.println("-----------------------------------" + uri + "-------------------------------------");
//        // 查询
//        HttpGet fuzzyGet = new HttpGet(uri);
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        // 执行请求
//        try {
//            CloseableHttpResponse fuzzyResponse = httpClient.execute(fuzzyGet);
//            String fuzzyList = EntityUtils.toString(fuzzyResponse.getEntity(), Contants.UTF_8);
//            JSONObject result = new JSONObject(fuzzyList);
//            JSONObject response = (JSONObject) result.get("response");
//            out.setCount(response.getInt("numFound"));
//            JSONArray docs = response.getJSONArray("docs");
//            int length = docs.length();
//            List<NewsTableOutData> list = new ArrayList<NewsTableOutData>(length);
//            for (int i = 0; i < length; i++) {
//                JSONObject obj = (JSONObject) docs.get(i);
//                NewsTableOutData outData = new NewsTableOutData();
//
//                String compyId = obj.get("company_ids").toString();
//                if(!StringUtils.isEmpty(compyId)){
//                    List<Object> item = findCompanyNm(compyId);
//                    if(item.size() > 0){
//                        Object[] info = (Object[])item.get(0);
//                        outData.setCompanyId(info[0].toString());
//                        outData.setCompanyNm(info[1].toString());
//                    }
//                }
//                outData.setTitle(StringUtil.toString(obj.get("title")).replaceAll("\\\\", ""));
//                outData.setUrl(StringUtil.toString(obj.get("url")));
//                outData.setDate(StringUtil.toString(obj.get("last_modified")));
//                outData.setContent(StringUtil.toString(obj.get("content"), 60));
//                outData.setNewsCode(StringUtil.toString(obj.get("newscode")));
//                outData.setImportance(StringUtil.toString(obj.get("importance")));
//                list.add(outData);
//            }
        List<NewsTableOutData> list = new ArrayList<NewsTableOutData>(startRow+1);
        for (int i = 1; i < startRow+1; i++) {
            NewsTableOutData outData = new NewsTableOutData();

            outData.setCompanyId("CompanyId"+i);
            outData.setCompanyNm("江苏雷科防务科技股份有限公司"+i);
            outData.setTitle("雷科防务停牌筹划购买资产事项"+i);
            outData.setUrl("url"+i);
            outData.setDate("2018-1-"+i+" 21:59:06");
            outData.setContent("content"+i);
            outData.setNewsCode("newscode"+i);
            outData.setImportance("importance"+i);
            outData.setCnnScore("cnnscore"+i);
            outData.setSelectDate("201801");//设置初始查询月份
            outData.setMediaNm("东方时报"+i);
            list.add(outData);
        }
        Map<String, List<NewsTableOutData>> map = new HashMap<String, List<NewsTableOutData>>();

        Collections.sort(list, new Comparator<NewsTableOutData>() {
            @Override
            public int compare(NewsTableOutData o1, NewsTableOutData o2) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                try {
                    Date d1 = sdf.parse(o1.getDate());
                    Date d2 = sdf.parse(o1.getDate());
                    if(d1.before(d2)){
                        return 1;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return -1;
            }
        });
        map.put("content", list);

        out.setData(map);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return out;
    }

    @Override
    public List<Object> findchart(TendencyChartInData inData) {
        //查询当前时间前七个月的数据
//        String sqlWhere = " WHERE POST_DT >= add_months(SYSDATE, -7) AND A.COMPANY_ID IN (SELECT FOCUS_ID FROM USER_FOCUS WHERE FOCUS_TYPE = 1 AND USER_ID = " + inData.getUserId() + ") ";
//        String classify = " TO_CHAR(POST_DT,'YYYY-MM-DD')POST_DT";
//        String sql = "SELECT CN1,CN2,A.POST_DT FROM(\n" +
//                "SELECT COUNT(1) CN1,POST_DT FROM(SELECT " + classify + " FROM COMPY_BASICINFO A\n" +
//                "INNER JOIN XW_NEWS_COMPANY B ON A.COMPANY_ID = B.COMPANY_ID AND B.ISDEL = 0 AND (B.RELEVANCE > 0.01 OR B.IMPORTANCE > 0)\n" +
//                "INNER JOIN NEWS_BASICINFO C ON C.NEWS_BASICINFO_SID = B.NEWS_BASICINFO_SID\n" +
//                "" + sqlWhere + " )\n" +
//                "GROUP BY POST_DT)A\n" +
//                "LEFT JOIN (\n" +
//                "SELECT COUNT(1) CN2,POST_DT FROM(SELECT " + classify + " FROM COMPY_BASICINFO A\n" +
//                "INNER JOIN XW_NEWS_COMPANY B ON A.COMPANY_ID = B.COMPANY_ID AND B.ISDEL = 0 AND (B.RELEVANCE > 0.01 OR B.IMPORTANCE > 0)\n" +
//                "INNER JOIN NEWS_BASICINFO C ON C.NEWS_BASICINFO_SID = B.NEWS_BASICINFO_SID \n" +
//                "" + sqlWhere + " AND B.SCORE < 0)\n" +
//                "GROUP BY POST_DT)B ON A.POST_DT = B.POST_DT \n" +
//                "ORDER BY POST_DT DESC\n";
//        return em.createNativeQuery(sql).getResultList();
       return  null;
    }
}
