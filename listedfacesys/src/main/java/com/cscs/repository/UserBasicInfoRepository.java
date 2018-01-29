package com.cscs.repository;

import com.cscs.listedfacesys.entity.UserBasicinfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Create by wth on 2018/1/27
 */
@SuppressWarnings("JpaQlInspection")
public interface UserBasicInfoRepository extends JpaRepository<UserBasicinfoEntity, Long> {

    /**
     * 判断账户是否存在
     * @param userNm
     * @return
     */
    @Query(value = "select u from UserBasicinfoEntity u where u.userNm=:userNm")
    UserBasicinfoEntity findByUserNm(@Param("userNm") String userNm);

    /**
     * 修改密码
     * @param userNm
     * @param password
     * @return 0：成功；1：失败
     */
    @Modifying
    @Query(value = "update UserBasicinfoEntity as u set u.password=:password where u.userNm=:userNm")
    Integer updatePassword(@Param("userNm") String userNm, @Param("password") String password);
}
