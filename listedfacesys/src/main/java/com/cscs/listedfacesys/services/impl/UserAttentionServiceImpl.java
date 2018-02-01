package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.services.UserAttentionService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

        return null;
    }
}
