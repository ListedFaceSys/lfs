package com.cscs.listedfacesys.services;


import java.util.Set;

/**
 * Create by wzy on 2018/2/1
 * 用户关注
 */
public interface UserAttentionService {

    /**
     * 查询用户关注公司ID列表
     * @param userId
     * @return
     */
    public Set<String> searchAllCompy(Long userId);
}
