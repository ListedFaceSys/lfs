package com.cscs.listedfacesys.services;

import com.cscs.listedfacesys.entity.LfsUser;

import java.util.List;

/**
 * Create by wth on 2018/1/27
 */
public interface LfsUserInfoServices {

    /**
     * 新建账号
     */
    LfsUser save(LfsUser lfsUserInfo) throws Exception;

    /**
     * 查询账号是否存在
     */
    LfsUser findByUserName(String userName);

    /**
     * 修改密码
     * @param userName
     * @param password
     * @return 0：成功；1：失败
     */
    Integer updatePassword(String userName, String password);

    /**
     * 查询所有的账号
     * @return
     */
    List<LfsUser> findAllUser();
}
