package com.sinfeeloo.invoicing.basicinfo.ui.panel;

import com.sinfeeloo.invoicing.base.BasePanel;
import com.sinfeeloo.invoicing.base.listener.InputKeyListener;
import com.sinfeeloo.invoicing.basicinfo.dao.CustomerDao;
import com.sinfeeloo.invoicing.basicinfo.pojo.CustomerBean;
import com.sinfeeloo.invoicing.basicinfo.pojo.SelectItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mhj
 * @Desc:
 * @Date: 2018/3/29 10:52
 */
public class ModifyCustomerPanel extends BasePanel {
    private JTextField customerFullName;
    private JTextField bankCardNum;
    private JTextField openBank;
    private JTextField email;
    private JTextField telephone;
    private JTextField contact;
    private JTextField address;
    private JTextField customerShortName;
    private JButton modifyButton;
    private JButton delButton;
    private JComboBox kehu;

    @Override
    protected void initView() {
        setBounds(10, 10, 460, 300);
        setLayout(new GridBagLayout());
        setVisible(true);


        //�ͻ�ȫ��
        setupComponet(new JLabel("�ͻ�ȫ�ƣ�"), 0, 0, 1, 0, false);
        customerFullName = new JTextField();
        customerFullName.setEditable(false);
        // ��λȫ���ı���
        setupComponet(customerFullName, 1, 0, 3, 350, true);

        //�ͻ���ַ
        setupComponet(new JLabel("�ͻ���ַ��"), 0, 1, 1, 0, false);
        address = new JTextField();
        // ��λ��ַ�ı���
        setupComponet(address, 1, 1, 3, 0, true);

        //�ͻ����
        setupComponet(new JLabel("�ͻ���ƣ�"), 0, 2, 1, 0, false);
        customerShortName = new JTextField();
        // ��λ�ͻ�����ı���
        setupComponet(customerShortName, 1, 2, 1, 130, true);


        setupComponet(new JLabel("�������룺"), 2, 2, 1, 0, false);

        //��ϵ��
        setupComponet(new JLabel("��ϵ�ˣ�"), 0, 4, 1, 0, false);
        contact = new JTextField();
        // ��λ��ϵ���ı���
        setupComponet(contact, 1, 4, 1, 100, true);

        //�ֻ���
        setupComponet(new JLabel("�ֻ��ţ�"), 2, 4, 1, 0, false);
        telephone = new JTextField();
        // ��λ��ϵ�绰�ı���
        setupComponet(telephone, 3, 4, 1, 100, true);
        telephone.addKeyListener(new InputKeyListener());

        //����
        setupComponet(new JLabel("���䣺"), 0, 5, 1, 0, false);
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

        //ѡ��ͻ�
        setupComponet(new JLabel("ѡ��ͻ�"), 0, 7, 1, 0, false);
        kehu = new JComboBox();
        kehu.setPreferredSize(new Dimension(230, 21));
        initComboBox();// ��ʼ������ѡ���

        // ����ͻ���Ϣ������ѡ����ѡ���¼�
        kehu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doSelect();
            }
        });

        // ��λ�ͻ���Ϣ������ѡ���
        setupComponet(kehu, 1, 7, 2, 0, true);
        modifyButton = new JButton("�޸�");
        delButton = new JButton("ɾ��");
        JPanel panel = new JPanel();
        panel.add(modifyButton);
        panel.add(delButton);
        // ��λ��ť
        setupComponet(panel, 3, 7, 1, 0, false);

        // ����ɾ����ť�ĵ����¼�
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectItem item = (SelectItem) kehu.getSelectedItem();
                if (item == null || !(item instanceof SelectItem))
                    return;
                int confirm = JOptionPane.showConfirmDialog(
                        ModifyCustomerPanel.this, "ȷ��ɾ���ͻ���Ϣ��");
                if (confirm == JOptionPane.YES_OPTION) {
                    if (CustomerDao.newInstance().deleteCustomer(item.getId()) > 0) {
                        JOptionPane.showMessageDialog(ModifyCustomerPanel.this,
                                "�ͻ���" + item.getName() + "��ɾ���ɹ�");
                        kehu.removeItem(item);
                    }
                }
            }
        });
        // �����޸İ�ť�ĵ����¼�
        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectItem item = (SelectItem) kehu.getSelectedItem();
                CustomerBean customerBean = new CustomerBean();
                customerBean.setId(Integer.parseInt(item.getId()));
                customerBean.setAddress(address.getText().trim());
                customerBean.setName(customerFullName.getText().trim());
                customerBean.setShortName(customerShortName.getText().trim());
                customerBean.setEmail(email.getText().trim());
                customerBean.setContact(contact.getText().trim());
                customerBean.setTelephone(telephone.getText().trim());
                customerBean.setOpenbank(openBank.getText().trim());
                customerBean.setBankcardnum(bankCardNum.getText().trim());
                if (CustomerDao.newInstance().updateCustomer(customerBean) == 1)
                    JOptionPane.showMessageDialog(ModifyCustomerPanel.this, "�޸����");
                else
                    JOptionPane.showMessageDialog(ModifyCustomerPanel.this, "�޸�ʧ��");
            }
        });
    }


    /**
     * ��ʼ���ͻ�����ѡ���
     */
    public void initComboBox() {
        List<List<String>> customerList = CustomerDao.newInstance().getCustomerList();
        List<SelectItem> items = new ArrayList<>();
        kehu.removeAllItems();
        for (int i = 0; i < customerList.size(); i++) {
            kehu.addItem(new SelectItem(customerList.get(i).get(0), customerList.get(i).get(1)));
        }
        doSelect();
    }

    /**
     * ѡ�в���
     */
    private void doSelect() {
        SelectItem selectedItem;
        if (!(kehu.getSelectedItem() instanceof SelectItem)) {
            return;
        }
        selectedItem = (SelectItem) kehu.getSelectedItem();
        CustomerBean customerBean = CustomerDao.newInstance().getCustomerInfo(selectedItem.getId());
        customerFullName.setText(customerBean.getName());
        customerShortName.setText(customerBean.getShortName());
        address.setText(customerBean.getAddress());
        contact.setText(customerBean.getContact());
        telephone.setText(customerBean.getTelephone());
        email.setText(customerBean.getEmail());
        openBank.setText(customerBean.getOpenbank());
        bankCardNum.setText(customerBean.getBankcardnum());
    }


    // �������λ�ò���ӵ�������
    private void setupComponet(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill) {
        final GridBagConstraints gridBagConstrains = new GridBagConstraints();
        gridBagConstrains.gridx = gridx;
        gridBagConstrains.gridy = gridy;
        if (gridwidth > 1)
            gridBagConstrains.gridwidth = gridwidth;
        if (ipadx > 0)
            gridBagConstrains.ipadx = ipadx;
        gridBagConstrains.insets = new Insets(5, 1, 3, 1);
        if (fill)
            gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
        add(component, gridBagConstrains);
    }
}
