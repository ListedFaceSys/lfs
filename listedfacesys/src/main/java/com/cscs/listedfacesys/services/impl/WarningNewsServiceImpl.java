package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.services.WarningNewsService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Create by wzy 2018/02/01
 */
@Service
public class WarningNewsServiceImpl implements WarningNewsService {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Object> getWarningTop10() {
        return null;
    }
}
