package com.cscs.util;

/**
 * sql工具类
 */
public class SqlUtils {

    /**
     * 生成分页sql
     * @param page 当前页数
     * @param pageSize 一页显示的数量
     * @param sql  需分页的业务sql
     * @return
     */
    public static String  pagingMethod(int page,int pageSize,String sql){
        int pageNum = page*pageSize+1;
        int rows = (page-1)*pageSize;
        String pagingSql = "SELECT *\n" +
                "FROM\n" +
                "  (SELECT tt.*,\n" +
                "    ROWNUM RN\n" +
                "  FROM\n" +
                "    ( \n"
                +
                sql
                +
                "    ) tt\n" +
                "  WHERE ROWNUM < "+pageNum+"   \n" +
                "  )\n" +
                "WHERE RN > "+ rows+"\n";
        return pagingSql;
    }
}
