package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.services.WarningTopService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Create by wzy 2018/02/01
 */
@Service
public class WarningTopServiceImpl implements WarningTopService {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Object> getWarningTop10() {
        return null;
    }
}
