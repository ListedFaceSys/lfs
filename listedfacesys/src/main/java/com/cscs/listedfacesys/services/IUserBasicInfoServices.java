package com.cscs.listedfacesys.services;

import com.cscs.listedfacesys.entity.UserBasicinfoEntity;

import java.util.List;

/**
 * Create by wth on 2018/1/27
 */
public interface IUserBasicInfoServices {

    /**
     * 新建账号
     */
    UserBasicinfoEntity save(UserBasicinfoEntity userBasicInfo) throws Exception;

    /**
     * 查询账号是否存在
     */
    UserBasicinfoEntity findByUserNm(String userNm);

    /**
     * 修改密码
     * @param userNm
     * @param password
     * @return 0：成功；1：失败
     */
    Integer updatePassword(String userNm, String password);

    /**
     * 查询所有的账号
     * @return
     */
    List<UserBasicinfoEntity> findAllUser();
}
