package com.sinfeeloo.invoicing.base.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mhj
 * @Desc:
 * @Date: 2018/3/28 15:35
 */
public class DBUtils {

    protected static String second = null;

    private static DBUtils instance = null;

    private DBUtils() {
    }

    public static DBUtils newInstance() {
        if (null == instance) {
            instance = new DBUtils();
        }
        return instance;
    }




    /**
     * ����
     *
     * @param sql
     * @return
     */
    public static ResultSet findForResultSet(String sql) {
        if (conn == null)
            return null;
        long time = System.currentTimeMillis();
        ResultSet rs = null;
        try {
            Statement stmt = null;
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            second = ((System.currentTimeMillis() - time) / 1000d) + "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }


    /**
     * ����
     *
     * @param sql
     * @return
     */
    public static boolean insert(String sql) {
        boolean result = false;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static boolean add(PreparedStatement stmt) {
        try {




            int i = stmt.executeUpdate();            //ִ�в������ݲ���������Ӱ�������
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { //finally���ô��ǲ��ܳ����Ƿ�����쳣����Ҫִ��finally��䣬�����ڴ˴��ر�����
            try {
                conn.close(); //��һ��Connection���Ӻ����һ��Ҫ��������close���������ر����ӣ����ͷ�ϵͳ��Դ�����ݿ���Դ
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * ����
     *
     * @param sql
     * @return
     */
    public static int update(String sql) {
        int result = 0;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * ���б�
     *
     * @param sql
     * @return
     */
    public static List<List<String>> findForList(String sql) {
        List<List<String>> list = new ArrayList<>();
        ResultSet rs = findForResultSet(sql);
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int colCount = metaData.getColumnCount();
            while (rs.next()) {
                List<String> row = new ArrayList<>();
                for (int i = 1; i <= colCount; i++) {
                    String str = rs.getString(i);
                    if (str != null && !str.isEmpty())
                        str = str.trim();
                    row.add(str);
                }
                list.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    // ִ��ָ����ѯ
    public static ResultSet query(String QueryStr) {
        ResultSet set = findForResultSet(QueryStr);
        return set;
    }

    // ִ��ɾ��
    public static int delete(String sql) {
        return update(sql);
    }
}
