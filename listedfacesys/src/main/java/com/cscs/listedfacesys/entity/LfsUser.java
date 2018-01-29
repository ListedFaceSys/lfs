package com.cscs.listedfacesys.entity;

import javax.persistence.*;

@Entity
@Table(name = "LFS_USER", schema = "GTJA", catalog = "")
public class LfsUser {
    private long id;
    private String userName;
    private String password;
    private String phone;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LfsUser lfsUser = (LfsUser) o;

        if (id != lfsUser.id) return false;
        if (userName != null ? !userName.equals(lfsUser.userName) : lfsUser.userName != null) return false;
        if (password != null ? !password.equals(lfsUser.password) : lfsUser.password != null) return false;
        if (phone != null ? !phone.equals(lfsUser.phone) : lfsUser.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
