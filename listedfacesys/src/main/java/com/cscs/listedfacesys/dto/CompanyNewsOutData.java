package com.cscs.listedfacesys.dto;

/**
 * Created by hj on 2018/2/6.
 */
public class CompanyNewsOutData {
    private String title;  //标题
    private String date;   //发布日期
    private String url;    //发布网址
    private String plainText;//纯文字
    private String companyId;//相关公司ID
    private String companyNm;//相关公司名称
    private int cnn_score;   //负面标识
    private String relevance;
    private String importance;//重要度
    private String newsSource;//新闻来源
    private String postDt; //发布日期（页面显示用这个）

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public String getPlainText() {
        return plainText;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public int getCnn_score() {
        return cnn_score;
    }

    public String getRelevance() {
        return relevance;
    }

    public String getImportance() {
        return importance;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public void setCnn_score(int cnn_score) {
        this.cnn_score = cnn_score;
    }

    public void setRelevance(String relevance) {
        this.relevance = relevance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getPostDt() {
        return postDt;
    }

    public void setPostDt(String postDt) {
        this.postDt = postDt;
    }
}
