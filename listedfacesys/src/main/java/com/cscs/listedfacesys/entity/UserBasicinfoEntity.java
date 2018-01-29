package com.cscs.listedfacesys.entity;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "USER_BASICINFO", schema = "C##MEDIA", catalog = "")
public class UserBasicinfoEntity {
    private long userId;
    private String userNm;
    private String phone;
    private String email;
    private String password;
    private Time loginDt;
    private String loginIp;
    private Time registDt;
    private String registIp;
    private Long updateId;
    private Timestamp updateDt;
    private Long creatId;
    private Timestamp creatDt;

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USERBASICINFO")
    @SequenceGenerator(sequenceName = "SEQ_USERBASICINFO", name="SEQ_USERBASICINFO")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "USER_NM")
    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    @Basic
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    @Column(name = "LOGIN_DT")
    public Time getLoginDt() {
        return loginDt;
    }

    public void setLoginDt(Time loginDt) {
        this.loginDt = loginDt;
    }

    @Basic
    @Column(name = "LOGIN_IP")
    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Basic
    @Column(name = "REGIST_DT")
    public Time getRegistDt() {
        return registDt;
    }

    public void setRegistDt(Time registDt) {
        this.registDt = registDt;
    }

    @Basic
    @Column(name = "REGIST_IP")
    public String getRegistIp() {
        return registIp;
    }

    public void setRegistIp(String registIp) {
        this.registIp = registIp;
    }

    @Basic
    @Column(name = "UPDATE_ID")
    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    @Basic
    @Column(name = "UPDATE_DT")
    public Timestamp getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Timestamp updateDt) {
        this.updateDt = updateDt;
    }

    @Basic
    @Column(name = "CREAT_ID")
    public Long getCreatId() {
        return creatId;
    }

    public void setCreatId(Long creatId) {
        this.creatId = creatId;
    }

    @Basic
    @Column(name = "CREAT_DT")
    public Timestamp getCreatDt() {
        return creatDt;
    }

    public void setCreatDt(Timestamp creatDt) {
        this.creatDt = creatDt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBasicinfoEntity that = (UserBasicinfoEntity) o;

        if (userId != that.userId) return false;
        if (userNm != null ? !userNm.equals(that.userNm) : that.userNm != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (loginDt != null ? !loginDt.equals(that.loginDt) : that.loginDt != null) return false;
        if (loginIp != null ? !loginIp.equals(that.loginIp) : that.loginIp != null) return false;
        if (registDt != null ? !registDt.equals(that.registDt) : that.registDt != null) return false;
        if (registIp != null ? !registIp.equals(that.registIp) : that.registIp != null) return false;
        if (updateId != null ? !updateId.equals(that.updateId) : that.updateId != null) return false;
        if (updateDt != null ? !updateDt.equals(that.updateDt) : that.updateDt != null) return false;
        if (creatId != null ? !creatId.equals(that.creatId) : that.creatId != null) return false;
        if (creatDt != null ? !creatDt.equals(that.creatDt) : that.creatDt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (userNm != null ? userNm.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (loginDt != null ? loginDt.hashCode() : 0);
        result = 31 * result + (loginIp != null ? loginIp.hashCode() : 0);
        result = 31 * result + (registDt != null ? registDt.hashCode() : 0);
        result = 31 * result + (registIp != null ? registIp.hashCode() : 0);
        result = 31 * result + (updateId != null ? updateId.hashCode() : 0);
        result = 31 * result + (updateDt != null ? updateDt.hashCode() : 0);
        result = 31 * result + (creatId != null ? creatId.hashCode() : 0);
        result = 31 * result + (creatDt != null ? creatDt.hashCode() : 0);
        return result;
    }
}
