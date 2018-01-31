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
import java.util.ArrayList;
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
        String sql = "SELECT u.USER_NAME , u.PHONE  FROM LFS_USER u";
        List<LfsUser> out = new ArrayList<LfsUser>();
        Query query = em.createNativeQuery(sql);
        List rows = query.getResultList();
        if(rows != null) {
            for (Object row: rows) {
                Object[] cells = (Object[]) row;
                if(cells != null) {
                    LfsUser temp = new LfsUser();
                    if(cells[0] != null) {
                        temp.setUserName(cells[0]+"");  //返回用户名
                    }
                    if(cells[1] != null){
                        temp.setPhone(cells[1]+"");     //返回电话号码
                    }
                    out.add(temp);
                }
            }
        }
        return out;
    }
}
