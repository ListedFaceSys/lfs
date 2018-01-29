package com.cscs.listedfacesys.services.impl;

import com.cscs.listedfacesys.entity.UserBasicinfoEntity;
import com.cscs.listedfacesys.services.IUserBasicInfoServices;
import com.cscs.repository.UserBasicInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class UserBasicInfoServicesImpl implements IUserBasicInfoServices {

    @Autowired
    private UserBasicInfoRepository userBasicInfoRepository;

    @PersistenceContext
    EntityManager em;

    @Override
    public UserBasicinfoEntity save(UserBasicinfoEntity userBasicInfo) throws Exception {
        return userBasicInfoRepository.save(userBasicInfo);
    }

    @Override
    public UserBasicinfoEntity findByUserNm(String userNm) {
        return userBasicInfoRepository.findByUserNm(userNm);
    }

    @Override
    @Transactional
    public Integer updatePassword(String userNm, String password) {
        int num = 1;

        int result = userBasicInfoRepository.updatePassword(userNm, password);

        if (result > 0) {
            num = 0;
        }

        return num;
    }

    @Override
    public List<UserBasicinfoEntity> findAllUser() {
        String sql = "SELECT u.USER_NM, u.PHONE FROM USER_BASICINFO u";

        Query query = em.createNativeQuery(sql);

        return query.getResultList();
    }
}
