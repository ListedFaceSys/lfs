package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.dto.TendencyChartInData;
import com.cscs.listedfacesys.services.NewsClassesService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by hj on 2018/02/01.
 * 新闻类实现类
 */
@Service
public class NewsClassesServiceImpl implements NewsClassesService {

    @PersistenceContext
    EntityManager em;

    //负面跟踪新闻
    public List<Object> getLastingBondViolationNews(int page, int pageSize,String startDate,String endDate) throws Exception {
        int pageNum = page*pageSize+1;
        int rows = (page-1)*pageSize;
        String where = "    AND (to_date(x.POST_DT,'yyyy-mm-dd') BETWEEN to_date('"+ startDate +"','yyyy-mm-dd') AND to_date('"+ endDate +"','yyyy-mm-dd'))\n";

        if((startDate==null && !"".equals(startDate)) || (endDate==null && !"".equals(endDate))){
            where="";
        }
        String sql = "SELECT *\n" +
                "FROM\n" +
                "  (SELECT tt.*,\n" +
                "    ROWNUM RN\n" +
                "  FROM\n" +
                "    (SELECT x.*,\n" +
                "      Z.LABEL\n" +
                "    FROM\n" +
                "      (SELECT d.NEWS_BASICINFO_SID\n" +
                "        || '-'\n" +
                "        || d.company_id ID,\n" +
                "        a.NEWS_BASICINFO_SID INFO_CD,\n" +
                "        a.TITLE,\n" +
                "        TO_CHAR(a.POST_DT,'YYYY-MM-DD')POST_DT,\n" +
                "        a.PLAIN_TEXT,\n" +
                "        a.POST_URL,\n" +
                "        d.company_id,\n" +
                "        d.SCORE CNN_SCORE,\n" +
                "        d.IMPORTANCE,\n" +
                "        d.RELEVANCE,\n" +
                "        a.MEDIA_NM\n" +
                "      FROM NEWS_BASICINFO a\n" +
                "      INNER JOIN XW_NEWS_COMPANY d\n" +
                "      ON a.NEWS_BASICINFO_SID  = d.NEWS_BASICINFO_SID\n" +
                "      WHERE NVL(d.RELEVANCE,0) > 0.01\n" +
                "      OR d.IMPORTANCE         != 0\n" +
                "      )x\n" +
                "    LEFT JOIN\n" +
                "      (SELECT TO_CHAR(B.NEWS_BASICINFO_SID)\n" +
                "        ||'-'\n" +
                "        ||TO_CHAR(B.COMPANY_ID) ID,\n" +
                "        wm_concat(DISTINCT D.SHEET_L1) LABEL\n" +
                "      FROM WARNING_NEWS_RESULT A\n" +
                "      INNER JOIN XW_NEWS_COMPANY B\n" +
                "      ON A.NEWS_BASICINFO_SID = B.NEWS_BASICINFO_SID\n" +
                "      AND A.COMPANY_ID        = B.COMPANY_ID\n" +
                "      INNER JOIN RULE C\n" +
                "      ON A.RULE_ID     = C.RULE_ID\n" +
                "      AND C.SHEET_TYPE = 0\n" +
                "      INNER JOIN SHEET D\n" +
                "      ON C.SHEET_ID    = D.SHEET_ID\n" +
                "      AND D.SHEET_TYPE = 0\n" +
                "      GROUP BY B.NEWS_BASICINFO_SID,\n" +
                "        B.COMPANY_ID\n" +
                "      ) z ON x.ID     = z.ID\n" +
                "    WHERE x.CNN_SCORE <0\n" +
                "    AND x.RELEVANCE  >= 0.8\n" +where+
                "    ORDER BY x.POST_DT DESC\n" +
                "    ) tt\n" +
                "  WHERE ROWNUM < "+pageNum+"   \n" +
                "  )\n" +
                "WHERE RN > "+ rows+"\n";
        System.out.print(sql);
        return em.createNativeQuery(sql).getResultList();
    }


    //负面跟踪新闻总数
    public int getLastingBondViolationNewsCount(String startDate,String endDate) throws Exception {

        String where = "    AND (to_date(x.POST_DT,'yyyy-mm-dd') BETWEEN to_date('"+ startDate +"','yyyy-mm-dd') AND to_date('"+ endDate +"','yyyy-mm-dd'))\n";

        if((startDate==null && !"".equals(startDate)) || (endDate==null && !"".equals(endDate))){
            where="";
        }
        String sql = "SELECT count(*) FROM (SELECT x.*,\n" +
                "      Z.LABEL\n" +
                "    FROM\n" +
                "      (SELECT d.NEWS_BASICINFO_SID\n" +
                "        || '-'\n" +
                "        || d.company_id ID,\n" +
                "        a.NEWS_BASICINFO_SID INFO_CD,\n" +
                "        a.TITLE,\n" +
                "        TO_CHAR(a.POST_DT,'YYYY-MM-DD')POST_DT,\n" +
                "        a.PLAIN_TEXT,\n" +
                "        a.POST_URL,\n" +
                "        d.company_id,\n" +
                "        d.SCORE CNN_SCORE,\n" +
                "        d.IMPORTANCE,\n" +
                "        d.RELEVANCE,\n" +
                "        a.MEDIA_NM\n" +
                "      FROM NEWS_BASICINFO a\n" +
                "      INNER JOIN XW_NEWS_COMPANY d\n" +
                "      ON a.NEWS_BASICINFO_SID  = d.NEWS_BASICINFO_SID\n" +
                "      WHERE NVL(d.RELEVANCE,0) > 0.01\n" +
                "      OR d.IMPORTANCE         != 0\n" +
                "      )x\n" +
                "    LEFT JOIN\n" +
                "      (SELECT TO_CHAR(B.NEWS_BASICINFO_SID)\n" +
                "        ||'-'\n" +
                "        ||TO_CHAR(B.COMPANY_ID) ID,\n" +
                "        wm_concat(DISTINCT D.SHEET_L1) LABEL\n" +
                "      FROM WARNING_NEWS_RESULT A\n" +
                "      INNER JOIN XW_NEWS_COMPANY B\n" +
                "      ON A.NEWS_BASICINFO_SID = B.NEWS_BASICINFO_SID\n" +
                "      AND A.COMPANY_ID        = B.COMPANY_ID\n" +
                "      INNER JOIN RULE C\n" +
                "      ON A.RULE_ID     = C.RULE_ID\n" +
                "      AND C.SHEET_TYPE = 0\n" +
                "      INNER JOIN SHEET D\n" +
                "      ON C.SHEET_ID    = D.SHEET_ID\n" +
                "      AND D.SHEET_TYPE = 0\n" +
                "      GROUP BY B.NEWS_BASICINFO_SID,\n" +
                "        B.COMPANY_ID\n" +
                "      ) z ON x.ID     = z.ID\n" +
                "    WHERE x.CNN_SCORE <0\n" +
                "    AND x.RELEVANCE  >= 0.8\n" +where+
                "    ORDER BY x.POST_DT DESC) \n";
        System.out.print(sql);
        Query query = em.createNativeQuery(sql);
        return Integer.valueOf(query.getSingleResult().toString());
    }


    /**
     * 将固定sql提取出公共的，以便保持和调用的方法的关联性
     * @param sqlWhere 条件
     * @param byDate 日期条件（可不传，不传视为不需要根据日期查询）
     * @return
     */
    private String returnSqlStr(String sqlWhere,String byDate) {
        String classify = " TO_CHAR(POST_DT,'YYYY-MM-DD')POST_DT";
        if(byDate==null){
            byDate="";
        }
        String mysql = "SELECT CN1,CN2,TO_CHAR(A.POST_DT,'YYYY-MM-DD') FROM(\n" +
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
                "GROUP BY POST_DT)B ON A.POST_DT = B.POST_DT "+byDate+" \n" +
                "ORDER BY POST_DT DESC \n";
        return mysql;
    }


    //热点新闻，查询当前时间前七个月的数据
    public List<Object> findchart(TendencyChartInData inData) {
        String sqlWhere = " WHERE POST_DT >= add_months(SYSDATE, -7) ";
        String sql = returnSqlStr(sqlWhere,"");
        System.out.print(sql);
        return em.createNativeQuery(sql).getResultList();
    }

    //热点新闻，根据日期查询该日期当天的新闻信息
    public List<Object> findchartByDate(TendencyChartInData inData) {
        String sqlwhereDate = " WHERE A.POST_DT= '"+inData.getPos_Dt()+"'";
        String sql = returnSqlStr("",sqlwhereDate);
        System.out.print(sql);
        return em.createNativeQuery(sql).getResultList();
    }

    //查询公司名称
    public List<Object> findCompanyNm(String compyId) {
        String sql = "SELECT COMPANY_ID,COMPANY_NM FROM COMPY_BASICINFO WHERE IS_DEL = 0 AND COMPANY_ID IN ("+compyId+")";
        // System.out.println(sql);
        return em.createNativeQuery(sql).getResultList();
    }
}
