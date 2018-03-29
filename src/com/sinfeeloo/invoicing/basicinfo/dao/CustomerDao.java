package com.sinfeeloo.invoicing.basicinfo.dao;


import com.sinfeeloo.invoicing.base.utils.DBUtils;
import com.sinfeeloo.invoicing.base.utils.DatabaseConnection;
import com.sinfeeloo.invoicing.basicinfo.pojo.CustomerBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: mhj
 * @Desc:
 * @Date: 2018/3/29 13:57
 */
public class CustomerDao {

    private static CustomerDao instance = null;

    private CustomerDao() {
    }

    public static CustomerDao newInstance() {
        if (null == instance) {
            instance = new CustomerDao();
        }
        return instance;
    }


    /**
     * ͨ���ֻ����жϸÿͻ��Ƿ����
     *
     * @param telephone
     * @return
     */
    public boolean isHaveThisCustomer(String telephone) {
        ResultSet resultSet = DBUtils.findForResultSet("select * from tb_customer where telephone='" + telephone + "'");
        try {
            if (resultSet.next()) {
                return true;
            }
            return false;

        } catch (Exception er) {
            er.printStackTrace();
        }

        return false;
    }


    /**
     * ��ӿͻ�
     *
     * @param bean
     * @return
     */
    public boolean addCustomer(CustomerBean bean) {
        boolean result = false;
        Connection conn = null;
        try {

            conn = DatabaseConnection.getConn();  //�������ݿ�����
            String sql = "insert into tb_customer(name,shortname,address,telephone,contact,email,openbank,bankcardnum) values(?, ?, ?, ?,?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);   //���׳��쳣

            stmt.setString(1, bean.getName());         //����SQL����һ����������ֵ
            stmt.setString(2, bean.getShortName());         //����SQL����һ����������ֵ
            stmt.setString(3, bean.getAddress());         //����SQL����һ����������ֵ
            stmt.setString(4, bean.getTelephone());         //����SQL����һ����������ֵ
            stmt.setString(5, bean.getContact());         //����SQL����һ����������ֵ
            stmt.setString(6, bean.getEmail());         //����SQL����һ����������ֵ
            stmt.setString(7, bean.getOpenbank());         //����SQL����һ����������ֵ
            stmt.setString(8, bean.getBankcardnum());         //����SQL����һ����������ֵ
            int i = stmt.executeUpdate();            //ִ�в������ݲ���������Ӱ�������
            if (i == 1) {
                result = true;
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

        return result;
    }


    /**
     * ��ȡ�ͻ��б�
     *
     * @return
     */
    public List<List<String>> getCustomerList() {
        List<List<String>> list = DBUtils.findForList("select id,name from tb_customer");
        return list;
    }

    /**
     * ����id��ȡ�ͻ���Ϣ
     *
     * @param id
     * @return
     */
    public CustomerBean getCustomerInfo(String id) {
        CustomerBean customerBean = new CustomerBean();
        ResultSet set = DBUtils.findForResultSet("select * from tb_customer where id='" + id + "'");
        try {
            if (set.next()) {
                customerBean.setId(set.getInt("id"));
                customerBean.setName(set.getString("name").trim());
                customerBean.setShortName(set.getString("shortname") == null ? "" : set.getString("shortname").trim());
                customerBean.setAddress(set.getString("address").trim());
                customerBean.setTelephone(set.getString("telephone").trim());
                customerBean.setContact(set.getString("contact").trim());
                customerBean.setEmail(set.getString("email") == null ? "" : set.getString("email").trim());
                customerBean.setOpenbank(set.getString("openbank") == null ? "" : set.getString("openbank").trim());
                customerBean.setBankcardnum(set.getString("bankcardnum") == null ? "" : set.getString("bankcardnum").trim());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerBean;
    }


    /**
     * ɾ���ͻ�
     *
     * @param id
     * @return
     */
    public int deleteCustomer(String id) {
        return DBUtils.delete("delete tb_customer where id='" + id + "'");
    }


    /**
     * �޸Ŀͻ���Ϣ
     *
     * @return
     */
    public int updateCustomer(CustomerBean customerBean) {
        return DBUtils.update("UPDATE tb_customer SET name='" + customerBean.getName()
                + "',address='" + customerBean.getAddress() + "',shortname='"
                + customerBean.getShortName() + "',telephone='" + customerBean.getTelephone() + "',contact='"
                + customerBean.getContact() + "',email='" + customerBean.getEmail() + "',openbank='"
                + customerBean.getOpenbank() + "',bankcardnum='" + customerBean.getBankcardnum()
                + "' where id='" + customerBean.getId() + "'");
    }
}
