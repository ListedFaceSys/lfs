package com.cscs.listedfacesys.dto;

/**
 * Created by hj on 2018/2/1.
 */
public class NewsTableOutData {
    private String title;
    private String date;
    private String url;
    private String content;
    private String companyId;
    private String companyNm;
    private String cnn_score;
    private String newsCode;
    private String importance;
    private String selectDate;//设置初始查询月份

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCnnScore() {
        return cnn_score;
    }

    public void setCnnScore(String cnn_score) {
        this.cnn_score = cnn_score;
    }

    public String getNewsCode() {
        return newsCode;
    }

    public void setNewsCode(String newsCode) {
        this.newsCode = newsCode;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getCnn_score() {
        return cnn_score;
    }

    public String getSelectDate() {
        return selectDate;
    }

    public void setCnn_score(String cnn_score) {
        this.cnn_score = cnn_score;
    }

    public void setSelectDate(String selectDate) {
        this.selectDate = selectDate;
    }

    @Override
    public String toString() {
        return "NewsTableOutData{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", companyId='" + companyId + '\'' +
                ", companyNm='" + companyNm + '\'' +
                ", cnn_score='" + cnn_score + '\'' +
                ", newsCode='" + newsCode + '\'' +
                ", importance='" + importance + '\'' +
                '}';
    }
}
