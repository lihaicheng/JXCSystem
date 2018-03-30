package com.sinfeeloo.invoicing.basicinfo.dao;

import com.sinfeeloo.invoicing.base.utils.DbConnUtil;
import com.sinfeeloo.invoicing.basicinfo.pojo.CustomerBean;
import com.sinfeeloo.invoicing.basicinfo.pojo.GoodsBean;
import com.sinfeeloo.invoicing.basicinfo.pojo.SelectItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mhj
 * @Desc:
 * @Date: 2018/3/30 14:08
 */
public class GoodsDao {

    //����ģʽ����֤�ö����ڳ�������Զ��Ψһ�ģ����Ա����ظ�����������ɵ�ϵͳ�ڴ汻����ռ��
    private static GoodsDao instance = null;

    public static GoodsDao getInstance() {
        if (null == instance) {
            instance = new GoodsDao();
        }
        return instance;
    }


    /**
     * ͨ����Ʒ�����жϸ���Ʒ�Ƿ����
     *
     * @param goodsCode
     * @return
     */
    public boolean isHaveThisGood(int goodsCode) {       //�����ݿ��в�ѯ��������
        Connection conn = null;
        try {
            conn = DbConnUtil.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tb_goods where goodscode='" + goodsCode + "'");//ִ��SQL�����ؽ����
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != conn)
                    conn.close(); //�ر�����
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;  //���ؽ��
    }


    /**
     * �����Ʒ
     *
     * @param bean
     * @return
     */
    public boolean addGood(GoodsBean bean) {
        boolean result = false;
        Connection conn = null;
        try {
            conn = DbConnUtil.getConn();  //�������ݿ�����
            String sql = "INSERT INTO tb_goods (barcode,goodscode,goodsname,brandid,brandname,categoryid,categoryname,purchaseprice,sellingprice,stock,status_,specification,supplierid,isdelete)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement stmt = conn.prepareStatement(sql);   //���׳��쳣
            stmt.setString(1, bean.getBarcode());         //����SQL����һ����������ֵ
            stmt.setString(2, bean.getGoodscode());
            stmt.setString(3, bean.getGoodsname());
            stmt.setInt(4, bean.getBrandId());
            stmt.setString(5, bean.getBrandName());
            stmt.setInt(6, bean.getCategoryId());
            stmt.setString(7, bean.getCategoryName());
            stmt.setDouble(8, bean.getPurchaseprice());
            stmt.setDouble(9, bean.getSellingprice());
            stmt.setInt(10, bean.getStock());
            stmt.setString(11, bean.getStatus_());
            stmt.setString(12, bean.getSpecification());
            stmt.setInt(13, bean.getSupplierid());
            stmt.setInt(14, 1);//1��δɾ��
            int i = stmt.executeUpdate();            //ִ�в������ݲ���������Ӱ�������
            if (i == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { //finally���ô��ǲ��ܳ����Ƿ�����쳣����Ҫִ��finally��䣬�����ڴ˴��ر�����
            try {
                if (null != conn)
                    conn.close(); //�ر�����
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }



    /**
     * ��ȡ�������б�
     *
     * @return
     */
    public List<SelectItem> getSupplierList() {       //�����ݿ��в�ѯ��������
        List<SelectItem> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DbConnUtil.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select code,name from tb_supplier");//ִ��SQL�����ؽ����
            while (rs.next()) {
                SelectItem item = new SelectItem();
                item.setId(rs.getInt("code"));
                item.setName(rs.getString("name"));
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != conn)
                    conn.close(); //�ر�����
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list; //���ؽ��
    }

}
