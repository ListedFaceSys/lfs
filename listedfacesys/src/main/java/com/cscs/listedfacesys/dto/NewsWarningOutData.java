package com.cscs.listedfacesys.dto;

import java.util.Map;

/**
 * Created by sh on 2016/8/7.
 */
public class NewsWarningOutData {
    //相关性
    public String relevance;
    //事件分类
    public String warningType;
    //新闻标题
    public String newsTitle;
    //发布时间
    public String publishTime;
    //来源
    public String newsSource;
    //正负
    public String score;
    //内容
    public String content;
    public String newsCode;
    //重要度
    public String importance;

    public Map sheetLabel;

    public String getRelevance() {
        return relevance;
    }

    public void setRelevance(String relevance) {
        this.relevance = relevance;
    }

    public String getWarningType() {
        return warningType;
    }

    public void setWarningType(String warningType) {
        this.warningType = warningType;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Map getSheetLabel() {
        return sheetLabel;
    }

    public void setSheetLabel(Map sheetLabel) {
        this.sheetLabel = sheetLabel;
    }
}
