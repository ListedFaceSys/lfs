package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.services.UserAttentionService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create by wzy 2018/02/01
 */
@Service
public class UserAttentionServiceImpl implements UserAttentionService {

    @PersistenceContext
    EntityManager em;

    @Override
    public Set<String> searchAllCompy(Long userId) {
        String sql = "SELECT FOCUS_ID FROM USER_FOCUS WHERE FOCUS_TYPE = 1 AND USER_ID = " + userId + "\n";
        List<Object> ids = em.createNativeQuery(sql).getResultList();
        Set<String> result = new HashSet<String>();
        for (Object o : ids) {
            result.add(String.valueOf(o));
        }
        return result;
    }
}
