package com.cscs.listedfacesys.dto.base;

import java.util.Map;

/**
 * Created by wth on 2018/1/27
 */
public class BaseOutData {
    private String code;
    private String message;
    private int count;
    private Map<String,?> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Map<String, ?> getData() {
        return data;
    }

    public void setData(Map<String, ?> data) {
        this.data = data;
    }
}
