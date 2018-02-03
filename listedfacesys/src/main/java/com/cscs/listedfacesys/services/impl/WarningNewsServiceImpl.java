package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.dto.NewsTableOutData;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.services.WarningNewsService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Create by wzy 2018/02/01
 */
@Service
public class WarningNewsServiceImpl implements WarningNewsService {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Object> getWarningTop10() {
        String sql = "SELECT CN,COMPANY_ID FROM (SELECT NVL(A.CN,0) + NVL(B.CN,0) CN,NVL(A.COMPANY_ID,B.COMPANY_ID) COMPANY_ID FROM (\n" +
                "SELECT COUNT(DISTINCT WARNING_TITLE) CN,COMPANY_ID FROM VW_COMPY_WARNINGS\n" +
                "WHERE TYPE_ID NOT IN(10,12,107) \n" +
                "GROUP BY COMPANY_ID)A\n" +
                "LEFT JOIN(SELECT COUNT(COMPANY_ID) CN,COMPANY_ID \n" +
                "FROM COMPY_ANNOUNCE_ALARM A\n" +
                "INNER JOIN LKP_ALARM_KEYWORD B ON A.ALARM_KEYWORD_CD = B.ALARM_KEYWORD_CD AND\n" +
                "B.SECOND_TYPE IN ('治理风险','财务风险','经营风险','市场风险','法律法规风险')\n" +
                "WHERE NOTICE_DT >= ADD_MONTHS(SYSDATE, -12)\n" +
                "GROUP BY COMPANY_ID)B ON A.COMPANY_ID  = B.COMPANY_ID\n" +
                "ORDER BY NVL(A.CN,0) + NVL(B.CN,0) DESC)WHERE ROWNUM < 11";
        return em.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object> getWarningTop5Content(String compyList) {
        String sql = "SELECT DISTINCT COMPANY_ID,COMPANY_NM,TITLE,TYPE_NAME,NOTICE_DT\n" +
                "FROM(\n" +
                "SELECT A.COMPANY_ID,A.COMPANY_NM,A.WARNING_TITLE TITLE,B.TYPE_NAME,A.NOTICE_DT\n" +
                "FROM VW_COMPY_WARNINGS A\n" +
                "INNER JOIN COMPY_EVENT_TYPE B ON A.TYPE_ID = B.ID\n" +
                "WHERE TYPE_ID NOT IN(10,12,107) AND A.COMPANY_ID IN(" + compyList + ")\n" +
                "UNION\n" +
                "SELECT A.COMPANY_ID,B.COMPANY_NM,A.NOTICE_TITLE TITLE,C.FST_TYPE || '-' || C.SECOND_TYPE  TYPE_NAME,A.NOTICE_DT\n" +
                "FROM COMPY_ANNOUNCE_ALARM A\n" +
                "INNER JOIN COMPY_BASICINFO B ON A.COMPANY_ID = B.COMPANY_ID\n" +
                "INNER JOIN LKP_ALARM_KEYWORD C ON A.ALARM_KEYWORD_CD = C.ALARM_KEYWORD_CD\n" +
                "AND C.SECOND_TYPE IN ('治理风险','财务风险','经营风险','市场风险','法律法规风险')\n" +
                "WHERE NOTICE_DT >= ADD_MONTHS(SYSDATE, -12) AND A.COMPANY_ID IN(" + compyList + ")\n" +
                ")ORDER BY COMPANY_ID,TYPE_NAME,NOTICE_DT desc";
        return em.createNativeQuery(sql).getResultList();
    }

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

}
