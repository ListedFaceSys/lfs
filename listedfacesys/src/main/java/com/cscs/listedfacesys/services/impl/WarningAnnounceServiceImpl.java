package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.services.WarningAnnounceService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

/**
 * Create by wzy 2018/02/01
 */
@Service
public class WarningAnnounceServiceImpl implements WarningAnnounceService {

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
    public List<Object> getWarningTop10Content(String compyList) {
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

}
