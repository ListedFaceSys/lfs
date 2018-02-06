package com.cscs.util;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

/**
 * Created by wzy 2018/2/6
 */
public class StringUtil {
    public static String toString(Object o) {
        if (o != null) {
            if (o instanceof Clob) {
                try {
                    return ClobToString((Clob) o);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "";
                }
            } else if(o instanceof JSONArray){
                JSONArray array = (JSONArray) o;
                if(array.length() > 0){
                    return toString(array.get(0));
                }
            }
            return o.toString();
        }
        return "";
    }

    public static String toString(Object o, int length) {
        String s = toString(o);
        if(s != null && s.length() > length){
            return s.substring(0, length);
        } else {
            return s;
        }
    }

    public static String addQuote(String s){
        return "\"" + s + "\"";
    }

    public static boolean isEmpty(String s){
        return s == null || s.trim().length() == 0;
    }

    public static String ClobToString(Clob clob) throws SQLException, IOException {

        String reString = "";
        Reader is = clob.getCharacterStream();// 得到流
        BufferedReader br = new BufferedReader(is);
        String s = br.readLine();
        StringBuffer sb = new StringBuffer();
        while (s != null) {// 执行循环将字符串全部取出付值给 StringBuffer由StringBuffer转成STRING
            sb.append(s);
            s = br.readLine();
        }
        reString = sb.toString();
        return reString;
    }
}
