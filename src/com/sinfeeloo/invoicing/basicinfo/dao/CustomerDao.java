package com.sinfeeloo.invoicing.basicinfo.dao;


import com.sinfeeloo.invoicing.base.utils.DbConnUtil;
import com.sinfeeloo.invoicing.basicinfo.pojo.CustomerBean;
import com.sinfeeloo.invoicing.basicinfo.pojo.SelectItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mhj
 * @Desc:
 * @Date: 2018/3/29 13:57
 */
public class CustomerDao {

    //����ģʽ����֤�ö����ڳ�������Զ��Ψһ�ģ����Ա����ظ�����������ɵ�ϵͳ�ڴ汻����ռ��
    private static CustomerDao instance = null;

    public static CustomerDao getInstance() {
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
    public boolean isHaveThisCustomer(String telephone) {       //�����ݿ��в�ѯ��������
        Connection conn = null;
        try {
            conn = DbConnUtil.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tb_customer where telephone='" + telephone + "'");//ִ��SQL�����ؽ����
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
        return false;                                             //���ؽ��
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
            conn = DbConnUtil.getConn();  //�������ݿ�����
            String sql = "insert into tb_customer(name,shortname,address,telephone,contact,email,openbank,bankcardnum) " +
                    "values(?, ?, ?, ?,?, ?, ?, ?)";
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
                if (null != conn)
                    conn.close(); //�ر�����
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
    public List<SelectItem> getCustomerList() {       //�����ݿ��в�ѯ��������
        List<SelectItem> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DbConnUtil.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select id,name from tb_customer");//ִ��SQL�����ؽ����
            while (rs.next()) {
                SelectItem item = new SelectItem();
                item.setId(rs.getInt("id"));
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


    /**
     * ����id��ȡ�ͻ���Ϣ
     *
     * @param id
     * @return
     */
    public CustomerBean getCustomerInfo(int id) {
        CustomerBean customerBean = new CustomerBean();
        Connection conn = null;
        String sql = "select * from tb_customer where id= ? ";
        try {
            conn = DbConnUtil.getConn();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
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
        } finally {
            try {
                if (null != conn)
                    conn.close(); //�ر�����
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customerBean;
    }


    /**
     * ɾ���ͻ�
     *
     * @param id
     * @return
     */
    public boolean deleteCustomer(int id) {
        boolean result = false;
        Connection conn = null;
        try {
            conn = DbConnUtil.getConn();
            String sql = "delete from tb_customer where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int i = stmt.executeUpdate();
            if (i == 1) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != conn)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * �޸Ŀͻ���Ϣ
     *
     * @return
     */
    public boolean updateCustomer(CustomerBean customerBean) {
        boolean result = false;
        Connection conn = null;
        try {
            conn = DbConnUtil.getConn();
            String sql = "update tb_customer set name=?,address=?,shortname=?,telephone=?,contact=?,email=?,openbank=?,bankcardnum=? where id=?";  //update���
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, customerBean.getName());                //����SQL����һ��"?"�Ĳ���ֵ
            stmt.setString(2, customerBean.getAddress());
            stmt.setString(3, customerBean.getShortName());
            stmt.setString(4, customerBean.getTelephone());
            stmt.setString(5, customerBean.getContact());
            stmt.setString(6, customerBean.getEmail());
            stmt.setString(7, customerBean.getOpenbank());
            stmt.setString(8, customerBean.getBankcardnum());
            stmt.setInt(9, customerBean.getId());
            int flag = stmt.executeUpdate();  //ִ���޸Ĳ���������Ӱ�������
            if (flag == 1) {   //�޸ĳɹ�����true
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != conn)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
