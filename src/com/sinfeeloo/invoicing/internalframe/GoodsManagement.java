package com.sinfeeloo.invoicing.internalframe;

import com.sinfeeloo.invoicing.base.BaseInternalFrame;
import com.sinfeeloo.invoicing.basicinfo.ui.panel.AddGoodPanel;
import com.sinfeeloo.invoicing.basicinfo.ui.panel.ModifyGoodPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 * @Author: mhj
 * @Desc: ��Ʒ����
 * @Date: 2018/3/30 10:53
 */
public class GoodsManagement extends BaseInternalFrame {
    @Override
    protected void initView() {
        setIconifiable(true);
        setClosable(true);
        setTitle("��Ʒ����");
        JTabbedPane tabPane = new JTabbedPane();
        final AddGoodPanel addGoodPanel = new AddGoodPanel();
        final ModifyGoodPanel modifyGoodPanel = new ModifyGoodPanel();
        tabPane.addTab("��Ʒ��Ϣ���", null, addGoodPanel, "��Ʒ���");
        tabPane.addTab("��Ʒ��Ϣ�޸���ɾ��", null, modifyGoodPanel, "�޸���ɾ��");
        getContentPane().add(tabPane);
        tabPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
//                addGoodPanel.initComboBox();
                addGoodPanel.initSupplierBox();
            }
        });
        //����Ʒ�����ڱ�����ʱ����ʼ����Ʒ��ӽ���Ĺ�Ӧ������ѡ���
        addInternalFrameListener(new InternalFrameAdapter() {
            public void internalFrameActivated(InternalFrameEvent e) {
                super.internalFrameActivated(e);
                addGoodPanel.initSupplierBox();
            }
        });
        pack();
        setVisible(true);
    }
}
