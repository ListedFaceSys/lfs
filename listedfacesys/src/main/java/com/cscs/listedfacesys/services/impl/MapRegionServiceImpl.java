package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.services.MapRegionService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * create by wzy on 2018/02/07
 */
@Service
public class MapRegionServiceImpl implements MapRegionService {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Object> getRegionCompanyCount() {
        String sql = "";

        return em.createNativeQuery(sql).getResultList();
    }
}
