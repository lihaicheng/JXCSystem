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
        setupComponet(fullNameLabel, 0, 0, 1, 0, false);
        customerFullName = new JTextField();
        // ��λȫ���ı���
        setupComponet(customerFullName, 1, 0, 3, 350, true);

        //�ͻ���ַ
        final JLabel addressLabel = new JLabel("�ͻ���ַ��");
        setupComponet(addressLabel, 0, 1, 1, 0, false);
        address = new JTextField();
        // ��λ��ַ�ı���
        setupComponet(address, 1, 1, 3, 0, true);

        //�ͻ����
        setupComponet(new JLabel("�ͻ���ƣ�"), 0, 2, 1, 0, false);
        customerShortName = new JTextField();
        // ��λ�ͻ�����ı���
        setupComponet(customerShortName, 1, 2, 1, 100, true);

        //��ϵ��
        setupComponet(new JLabel("��ϵ�ˣ�"), 0, 4, 1, 0, false);
        contact = new JTextField();
        // ��λ��ϵ���ı���
        setupComponet(contact, 1, 4, 1, 100, true);

        //��ϵ�绰
        setupComponet(new JLabel("��ϵ�绰��"), 2, 4, 1, 0, false);
        telephone = new JTextField();
        // ��λ��ϵ�绰�ı���
        setupComponet(telephone, 3, 4, 1, 100, true);
        telephone.addKeyListener(new InputKeyListener());

        //����
        setupComponet(new JLabel("E-Mail��"), 0, 5, 1, 0, false);
        email = new JTextField();
        // ��λE-Mail�ı���
        setupComponet(email, 1, 5, 3, 350, true);

        //��������
        setupComponet(new JLabel("�������У�"), 0, 6, 1, 0, false);
        openBank = new JTextField();
        // ��λ���������ı���
        setupComponet(openBank, 1, 6, 1, 100, true);

        //�����˺�
        setupComponet(new JLabel("�����˺ţ�"), 2, 6, 1, 0, false);
        bankCardNum = new JTextField();
        // ��λ�����˺��ı���
        setupComponet(bankCardNum, 3, 6, 1, 100, true);

        //���水ť
        JButton saveButton = new JButton("����");
        // ��λ���水ť
        setupComponet(saveButton, 1, 7, 1, 0, false);
        //��Ӽ����¼�
        saveButton.addActionListener(new SaveButtonActionListener());

        //���ð�ť
        JButton resetButton = new JButton("����");
        // ��λ���ð�ť
        setupComponet(resetButton, 3, 7, 1, 0, false);
        //��Ӽ����¼�
        resetButton.addActionListener(new ResetButtonActionListener());
    }


    // �������λ�ò���ӵ�������
    private void setupComponet(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill) {
        final GridBagConstraints gridBagConstrains = new GridBagConstraints();
        gridBagConstrains.gridx = gridx;
        gridBagConstrains.gridy = gridy;
        gridBagConstrains.insets = new Insets(5, 1, 3, 1);
        if (gridwidth > 1)
            gridBagConstrains.gridwidth = gridwidth;
        if (ipadx > 0)
            gridBagConstrains.ipadx = ipadx;
        if (fill)
            gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
        add(component, gridBagConstrains);
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

                if (CustomerDao.newInstance().addCustomer(customerBean)) {
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
            if (CustomerDao.newInstance().isHaveThisCustomer(telephone.getText().trim())) {
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
