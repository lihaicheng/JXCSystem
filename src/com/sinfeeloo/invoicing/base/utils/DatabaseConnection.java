package com.sinfeeloo.invoicing.base.utils;


import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static Connection conn = null;
    protected static String dbClassName = "com.mysql.jdbc.Driver";
    protected static String dbUrl = "jdbc:mysql://localhost:3306/newjxc?" + "useUnicode=true&characterEncoding=utf-8&useSSL=false";
    protected static String dbUser = "root";
    protected static String dbPwd = "root";
    protected static String second = null;

    public static Connection getConn() {
        try {
            Class.forName(dbClassName); //�������ݿ���������
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);  //��ȡ����
        } catch (Exception e) {
            System.out.println("�������ݿ�ʧ��");
            e.printStackTrace();
        }
        return conn;
    }

}