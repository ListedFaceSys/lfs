package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.entity.LfsUser;
import com.cscs.listedfacesys.services.LfsUserInfoServices;
import com.cscs.repository.LfsUserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class LfsUserInfoServicesImpl implements LfsUserInfoServices {

    @Autowired
    private LfsUserInfoRepository lfsUserInfoRepository;

    @PersistenceContext
    EntityManager em;

    @Override
    public LfsUser save(LfsUser lfsUserInfo) throws Exception {
        return lfsUserInfoRepository.save(lfsUserInfo);
    }

    @Override
    public LfsUser findByUserName(String userName) {
        return lfsUserInfoRepository.findByUserName(userName);
    }

    @Override
    @Transactional
    public Integer updatePassword(String userNm, String password) {
        int num = 1;

        int result = lfsUserInfoRepository.updatePassword(userNm, password);

        if (result > 0) {
            num = 0;
        }

        return num;
    }

    @Override
    public List<LfsUser> findAllUser() {
        String sql = "SELECT u.USER_NM, u.PHONE FROM USER_BASICINFO u";

        Query query = em.createNativeQuery(sql);

        return query.getResultList();
    }
}
