package com.sinfeeloo.invoicing.internalframe;

import com.sinfeeloo.invoicing.base.BaseInternalFrame;
import com.sinfeeloo.invoicing.basicinfo.ui.panel.AddCustomerPanel;
import com.sinfeeloo.invoicing.basicinfo.ui.panel.ModifyCustomerPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @Author: mhj
 * @Desc:�ͻ���Ϣ����
 * @Date: 2018/3/29 10:50
 */
public class CustomerManagement extends BaseInternalFrame {
    @Override
    protected void initView() {
        setIconifiable(true);
        setClosable(true);
        setTitle("�ͻ���Ϣ����");
        JTabbedPane tabPane = new JTabbedPane();
         ModifyCustomerPanel modifyPanel = new ModifyCustomerPanel();
         AddCustomerPanel addPanel = new AddCustomerPanel();
        tabPane.addTab("�ͻ���Ϣ���", null, addPanel, "�ͻ����");
        tabPane.addTab("�ͻ���Ϣ�޸���ɾ��", null, modifyPanel, "�޸���ɾ��");
        getContentPane().add(tabPane);
        tabPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                modifyPanel.initComboBox();
            }
        });
        pack();
        setVisible(true);
    }
}
