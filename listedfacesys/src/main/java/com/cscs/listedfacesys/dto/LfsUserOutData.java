package com.cscs.listedfacesys.dto;

import com.cscs.listedfacesys.dto.base.BaseOutData;

/**
 * Create by wth on 2018/1/27
 */
public class LfsUserOutData extends BaseOutData {
    private long id;
    private String userName;
    private String phone;
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
