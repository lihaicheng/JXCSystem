package com.sinfeeloo.invoicing.basicinfo.ui.panel;

import com.sinfeeloo.invoicing.base.BasePanel;
import com.sinfeeloo.invoicing.base.listener.InputKeyListener;
import com.sinfeeloo.invoicing.basicinfo.dao.CustomerDao;
import com.sinfeeloo.invoicing.basicinfo.pojo.CustomerBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: mhj
 * @Desc:��ӿͻ�
 * @Date: 2018/3/29 10:52
 */
public class AddCustomerPanel extends BasePanel {
    private JTextField customerFullName;
    private JTextField bankCardNum;
    private JTextField openBank;
    private JTextField email;
    private JTextField telephone;
    private JTextField contact;
    private JTextField address;
    private JTextField customerShortName;
    private JButton resetButton;

    @Override
    protected void initView() {
        setBounds(10, 10, 460, 300);
        setLayout(new GridBagLayout());
        setVisible(true);
        //�ͻ�ȫ��
        final JLabel fullNameLabel = new JLabel();
        fullNameLabel.setText("�ͻ�ȫ�ƣ�");
        setupComponent(fullNameLabel, 0, 0, 1, 0, false);
        customerFullName = new JTextField();
        // ��λȫ���ı���
        setupComponent(customerFullName, 1, 0, 3, 350, true);

        //�ͻ���ַ
        final JLabel addressLabel = new JLabel("�ͻ���ַ��");
        setupComponent(addressLabel, 0, 1, 1, 0, false);
        address = new JTextField();
        // ��λ��ַ�ı���
        setupComponent(address, 1, 1, 3, 0, true);

        //�ͻ����
        setupComponent(new JLabel("�ͻ���ƣ�"), 0, 2, 1, 0, false);
        customerShortName = new JTextField();
        // ��λ�ͻ�����ı���
        setupComponent(customerShortName, 1, 2, 1, 100, true);

        //��ϵ��
        setupComponent(new JLabel("��ϵ�ˣ�"), 0, 4, 1, 0, false);
        contact = new JTextField();
        // ��λ��ϵ���ı���
        setupComponent(contact, 1, 4, 1, 100, true);

        //��ϵ�绰
        setupComponent(new JLabel("��ϵ�绰��"), 2, 4, 1, 0, false);
        telephone = new JTextField();
        // ��λ��ϵ�绰�ı���
        setupComponent(telephone, 3, 4, 1, 100, true);
        telephone.addKeyListener(new InputKeyListener());

        //����
        setupComponent(new JLabel("E-Mail��"), 0, 5, 1, 0, false);
        email = new JTextField();
        // ��λE-Mail�ı���
        setupComponent(email, 1, 5, 3, 350, true);

        //��������
        setupComponent(new JLabel("�������У�"), 0, 6, 1, 0, false);
        openBank = new JTextField();
        // ��λ���������ı���
        setupComponent(openBank, 1, 6, 1, 100, true);

        //�����˺�
        setupComponent(new JLabel("�����˺ţ�"), 2, 6, 1, 0, false);
        bankCardNum = new JTextField();
        // ��λ�����˺��ı���
        setupComponent(bankCardNum, 3, 6, 1, 100, true);

        //���水ť
        JButton saveButton = new JButton("����");
        // ��λ���水ť
        setupComponent(saveButton, 1, 7, 1, 0, false);
        //��Ӽ����¼�
        saveButton.addActionListener(new SaveButtonActionListener());

        //���ð�ť
        JButton resetButton = new JButton("����");
        // ��λ���ð�ť
        setupComponent(resetButton, 3, 7, 1, 0, false);
        //��Ӽ����¼�
        resetButton.addActionListener(new ResetButtonActionListener());
    }




    // ���水ť���¼�������
    private final class SaveButtonActionListener implements ActionListener {
        public void actionPerformed(final ActionEvent e) {

            if(checkInfo()){
                CustomerBean customerBean = new CustomerBean();
                customerBean.setName(customerFullName.getText().trim());
                customerBean.setShortName(customerShortName.getText().trim());
                customerBean.setAddress(address.getText().trim());
                customerBean.setTelephone(telephone.getText().trim());
                customerBean.setContact(contact.getText().trim());
                customerBean.setEmail(email.getText().trim());
                customerBean.setOpenbank(openBank.getText().trim());
                customerBean.setBankcardnum(openBank.getText().trim());

                if (CustomerDao.getInstance().addCustomer(customerBean)) {
                    JOptionPane.showMessageDialog(AddCustomerPanel.this, "��ӳɹ���",
                            "�ͻ������Ϣ", JOptionPane.INFORMATION_MESSAGE);
                    //����
                    reset();
                } else {
                    JOptionPane.showMessageDialog(AddCustomerPanel.this, "���ʧ�ܣ�",
                            "�ͻ������Ϣ", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }


        /**
         * �������
         */
        private boolean checkInfo() {
            if ("".equals(customerFullName.getText())) {
                JOptionPane.showMessageDialog(null, "����д�ͻ�ȫ�ƣ�");
                return false;
            }
            if ("".equals(address.getText())) {
                JOptionPane.showMessageDialog(null, "����д�ͻ���ַ��");
                return false;
            }
            if ("".equals(customerShortName.getText())) {
                JOptionPane.showMessageDialog(null, "����д�ͻ���ƣ�");
                return false;
            }
            if ("".equals(telephone.getText())) {
                JOptionPane.showMessageDialog(null, "����д�ֻ��ţ�");
                return false;
            }
            if (telephone.getText().length() != 11) {
                JOptionPane.showMessageDialog(null, "�ֻ��Ÿ�ʽ����ȷ��");
                return false;
            }
            //ͨ���ֻ����жϸÿͻ��Ƿ����
            if (CustomerDao.getInstance().isHaveThisCustomer(telephone.getText().trim())) {
                JOptionPane.showMessageDialog(AddCustomerPanel.this,
                        "�ֻ����Ѵ��ڣ�", "�ͻ������Ϣ",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            if ("".equals(contact.getText())) {
                JOptionPane.showMessageDialog(null, "����д��ϵ�ˣ�");
                return false;
            }
            return true;

        }
    }

    // ���ð�ť���¼�������
    private final class ResetButtonActionListener implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            reset();
        }
    }

    /**
     * ����
     */
    public void reset() {
        customerFullName.setText("");
        customerShortName.setText("");
        address.setText("");
        email.setText("");
        contact.setText("");
        telephone.setText("");
        openBank.setText("");
        bankCardNum.setText("");
    }
}
