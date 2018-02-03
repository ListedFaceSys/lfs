package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.dto.NewsWarningInData;
import com.cscs.listedfacesys.services.NewsCountService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hj on 2018/02/01.
 * 新闻表格类实现类
 */
@Service
public class NewCountServiceImpl implements NewsCountService {

    /**
     * 热点新闻
     * @param inData
     * @return
     */
    public List<Object> findchartGroup(NewsWarningInData inData) {
       /* String sqlWhere = "";
        String classify = "";
        if (inData.getTime() == 1) {
            sqlWhere = " WHERE POST_DT >= SYSDATE - 7 AND A.COMPANY_ID IN (SELECT FOCUS_ID FROM USER_FOCUS WHERE FOCUS_TYPE = 1 AND USER_ID = " + inData.getUserId() + ") ";
            classify = " TO_CHAR(POST_DT,'YYYY-MM-DD')POST_DT";
        } else if (inData.getTime() == 2) {
            sqlWhere = " WHERE POST_DT >= add_months(SYSDATE, -1) AND A.COMPANY_ID IN (SELECT FOCUS_ID FROM USER_FOCUS WHERE FOCUS_TYPE = 1 AND USER_ID = " + inData.getUserId() + ") ";
            classify = " TO_CHAR(POST_DT,'YYYY-MM-DD')POST_DT";
        } else if (inData.getTime() == 3) {
            sqlWhere = " WHERE POST_DT >= add_months(SYSDATE, -3) AND A.COMPANY_ID IN (SELECT FOCUS_ID FROM USER_FOCUS WHERE FOCUS_TYPE = 1 AND USER_ID = " + inData.getUserId() + ") ";
            classify = " TO_CHAR(POST_DT,'YYYY-IW')POST_DT";
        } else if (inData.getTime() == 4) {
            sqlWhere = " WHERE POST_DT >= add_months(SYSDATE, -12) AND A.COMPANY_ID IN (SELECT FOCUS_ID FROM USER_FOCUS WHERE FOCUS_TYPE = 1 AND USER_ID = " + inData.getUserId() + ") ";
            classify = " TO_CHAR(POST_DT,'YYYY-MM')POST_DT";
        } else {
            sqlWhere = " WHERE POST_DT >= SYSDATE - 7 AND A.COMPANY_ID IN (SELECT FOCUS_ID FROM USER_FOCUS WHERE FOCUS_TYPE = 1 AND USER_ID = " + inData.getUserId() + ") ";
            classify = " TO_CHAR(POST_DT,'YYYY-MM-DD')POST_DT";
        }
        String sql = "SELECT CN1,CN2,A.POST_DT FROM(\n" +
                "SELECT COUNT(1) CN1,POST_DT FROM(SELECT " + classify + " FROM COMPY_BASICINFO A\n" +
                "INNER JOIN XW_NEWS_COMPANY B ON A.COMPANY_ID = B.COMPANY_ID AND B.ISDEL = 0 AND (B.RELEVANCE > 0.01 OR B.IMPORTANCE > 0)\n" +
                "INNER JOIN NEWS_BASICINFO C ON C.NEWS_BASICINFO_SID = B.NEWS_BASICINFO_SID\n" +
                "" + sqlWhere + " )\n" +
                "GROUP BY POST_DT)A\n" +
                "LEFT JOIN (\n" +
                "SELECT COUNT(1) CN2,POST_DT FROM(SELECT " + classify + " FROM COMPY_BASICINFO A\n" +
                "INNER JOIN XW_NEWS_COMPANY B ON A.COMPANY_ID = B.COMPANY_ID AND B.ISDEL = 0 AND (B.RELEVANCE > 0.01 OR B.IMPORTANCE > 0)\n" +
                "INNER JOIN NEWS_BASICINFO C ON C.NEWS_BASICINFO_SID = B.NEWS_BASICINFO_SID \n" +
                "" + sqlWhere + " AND B.SCORE < 0)\n" +
                "GROUP BY POST_DT)B ON A.POST_DT = B.POST_DT \n" +
                "ORDER BY POST_DT DESC\n";
        return em.createNativeQuery(sql).getResultList();*/
       return  null;
    }
}
