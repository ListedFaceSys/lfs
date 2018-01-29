package com.cscs.repository;

import com.cscs.listedfacesys.entity.LfsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Create by wth on 2018/1/27
 */
@SuppressWarnings("JpaQlInspection")
public interface LfsUserInfoRepository extends JpaRepository<LfsUser, Long> {

    /**
     * 判断账户是否存在
     * @param userName
     * @return
     */
    @Query(value = "select u from LfsUser u where u.userName=:userName")
    LfsUser findByUserName(@Param("userName") String userName);

    /**
     * 修改密码
     * @param userName
     * @param password
     * @return 0：成功；1：失败
     */
    @Modifying
    @Query(value = "update LfsUser as u set u.password=:password where u.userName=:userName")
    Integer updatePassword(@Param("userName") String userName, @Param("password") String password);
}
