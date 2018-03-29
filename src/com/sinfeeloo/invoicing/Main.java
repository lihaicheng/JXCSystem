package com.sinfeeloo.invoicing;

import com.sinfeeloo.invoicing.login.ui.LoginUi;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private JPanel sysManagePanel;
    private JDesktopPane desktopPane;
    private JFrame frame;
    private JLabel backLabel;
    // ���������Map���ͼ��϶���
    private Map<String, JInternalFrame> ifs = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginUi();
            }
        });
    }

    static {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Main() {
        //����������
        frame = new JFrame("��ҵ���������ϵͳ");
        frame.getContentPane().setBackground(new Color(170, 188, 120));
        frame.addComponentListener(new FrameListener());
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //����������ǩ
        backLabel = new JLabel();// ������ǩ
        backLabel.setVerticalAlignment(SwingConstants.TOP);
        backLabel.setHorizontalAlignment(SwingConstants.CENTER);
        updateBackImage(); // ���»��ʼ������ͼƬ

        desktopPane = new JDesktopPane();
        desktopPane.add(backLabel, new Integer(Integer.MIN_VALUE));
        frame.getContentPane().add(desktopPane);

        //����������ǩ���
        JTabbedPane navigationPanel = createNavigationPanel();
        frame.getContentPane().add(navigationPanel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    /**
     * ���±���ͼƬ�ķ���
     */
    private void updateBackImage() {
        if (backLabel != null) {
            int backWidth = this.frame.getWidth();
            int backHeight = frame.getHeight();
            backLabel.setSize(backWidth, backHeight);
            backLabel.setText("<html><body><image width='" + backWidth
                    + "' height='" + (backHeight - 110) + "' src="
                    + this.getClass().getResource("welcome.jpg")
                    + "'></img></body></html>");
        }
    }


    /**
     * ����������ǩ���ķ���
     *
     * @return
     */
    private JTabbedPane createNavigationPanel() {
        //����������ǩ���
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFocusable(false);
        tabbedPane.setBackground(new Color(211, 230, 192));
        tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED));

        //������Ϣ�������
        JPanel baseInfoManagePanel = new JPanel();
        baseInfoManagePanel.setBackground(new Color(215, 223, 194));
        baseInfoManagePanel.setLayout(new BoxLayout(baseInfoManagePanel, BoxLayout.X_AXIS));
        baseInfoManagePanel.add(createFrameButton("�ͻ���Ϣ����", "CustomerManagement"));
        baseInfoManagePanel.add(createFrameButton("��Ʒ��Ϣ����", "GoodsManagement"));
        baseInfoManagePanel.add(createFrameButton("��Ӧ����Ϣ����", "SupplierManagement"));

        //���������
        JPanel inventoryManagePanel = new JPanel();
        inventoryManagePanel.setBackground(new Color(215, 223, 194));
        inventoryManagePanel.setLayout(new BoxLayout(inventoryManagePanel, BoxLayout.X_AXIS));
        inventoryManagePanel.add(createFrameButton("����̵�", "InventoryVerification"));
        inventoryManagePanel.add(createFrameButton("�۸����", "PriceAdjustment"));

        //���۹������
        JPanel sellManagePanel = new JPanel();
        sellManagePanel.setBackground(new Color(215, 223, 194));
        sellManagePanel.setLayout(new BoxLayout(sellManagePanel, BoxLayout.X_AXIS));
        sellManagePanel.add(createFrameButton("���۵�", "SellOrder"));
        sellManagePanel.add(createFrameButton("�����˻�", "RefundOrder"));

        //��ѯͳ�����
        JPanel searchStatisticPanel = new JPanel();
        searchStatisticPanel.setBounds(0, 0, 600, 41);
        searchStatisticPanel.setName("searchStatisticPanel");
        searchStatisticPanel.setBackground(new Color(215, 223, 194));
        searchStatisticPanel.setLayout(new BoxLayout(searchStatisticPanel, BoxLayout.X_AXIS));
        searchStatisticPanel.add(createFrameButton("�ͻ���Ϣ��ѯ", "CustomerQuery"));
        searchStatisticPanel.add(createFrameButton("��Ʒ��Ϣ��ѯ", "GoodsQuery"));
        searchStatisticPanel.add(createFrameButton("��Ӧ����Ϣ��ѯ", "SupplierQuery"));
        searchStatisticPanel.add(createFrameButton("������Ϣ��ѯ", "SalesOrderQuery"));
        searchStatisticPanel.add(createFrameButton("�����˻���ѯ", "RefundQuery"));
        searchStatisticPanel.add(createFrameButton("����ѯ", "InStorageQuery"));
        searchStatisticPanel.add(createFrameButton("����˻���ѯ", "InStorageRefundQuery"));
        searchStatisticPanel.add(createFrameButton("��������", "SalesRank"));

        //�����������
        JPanel stockManagePanel = new JPanel();
        stockManagePanel.setBackground(new Color(215, 223, 194));
        stockManagePanel.setLayout(new BoxLayout(stockManagePanel, BoxLayout.X_AXIS));
        stockManagePanel.add(createFrameButton("������", "Purchase"));
        stockManagePanel.add(createFrameButton("�����˻�", "Refund"));

        //ϵͳ�������
        sysManagePanel = new JPanel();
        sysManagePanel.setBackground(new Color(215, 223, 194));
        sysManagePanel.setLayout(new BoxLayout(sysManagePanel, BoxLayout.X_AXIS));
        sysManagePanel.add(createFrameButton("����Ա����", "CzyGL"));
        sysManagePanel.add(createFrameButton("��������", "GengGaiMiMa"));
        sysManagePanel.add(createFrameButton("Ȩ�޹���", "QuanManager"));

        tabbedPane.addTab("   ������Ϣ����   ", null, baseInfoManagePanel, "������Ϣ����");
        tabbedPane.addTab("   ��������   ", null, stockManagePanel, "��������");
        tabbedPane.addTab("   ���۹���   ", null, sellManagePanel, "���۹���");
        tabbedPane.addTab("   ��ѯͳ��   ", null, searchStatisticPanel, "��ѯͳ��");
        tabbedPane.addTab("   ������   ", null, inventoryManagePanel, "������");
        tabbedPane.addTab("   ϵͳ����   ", null, sysManagePanel, "ϵͳ����");

        return tabbedPane;
    }


    /**
     * ������ӱ�ǩ��ť(Ϊ�ڲ��������Action�ķ���)
     *
     * @param fName
     * @param cname
     * @return
     */
    private JButton createFrameButton(String fName, String cname) {
        String imgUrl = "res/ActionIcon/" + fName + ".png";
        String imgUrl_roll = "res/ActionIcon/" + fName + "_roll.png";
        String imgUrl_down = "res/ActionIcon/" + fName + "_down.png";
        Icon icon = new ImageIcon(imgUrl);
        Icon icon_roll = null;
        if (imgUrl_roll != null)
            icon_roll = new ImageIcon(imgUrl_roll);
        Icon icon_down = null;
        if (imgUrl_down != null)
            icon_down = new ImageIcon(imgUrl_down);

        Action action = new openFrameAction(fName, cname, icon);
        //������ť
        JButton button = new JButton(action);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setHideActionText(true);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        if (icon_roll != null)
            button.setRolloverIcon(icon_roll);
        if (icon_down != null)
            button.setPressedIcon(icon_down);

        return button;
    }


    /**
     * ������˵���ĵ����¼�������
     */
    protected final class openFrameAction extends AbstractAction {
        private String frameName = null;

        private openFrameAction() {
        }

        public openFrameAction(String cname, String frameName, Icon icon) {
            this.frameName = frameName;
            putValue(Action.NAME, cname);
            putValue(Action.SHORT_DESCRIPTION, cname);
            putValue(Action.SMALL_ICON, icon);
        }

        public void actionPerformed(final ActionEvent e) {
            JInternalFrame jf = getIFrame(frameName);
            // ���ڲ�����չ�ʱ�����ڲ���������ifs����������ô��塣
            jf.addInternalFrameListener(new InternalFrameAdapter() {
                public void internalFrameClosed(InternalFrameEvent e) {
                    ifs.remove(frameName);
                }
            });
            if (jf.getDesktopPane() == null) {
                desktopPane.add(jf);
                jf.setVisible(true);
            }
            try {
                jf.setSelected(true);
            } catch (PropertyVetoException e1) {
                e1.printStackTrace();
            }
        }
    }


    /**
     * ��ȡ�ڲ������Ψһʵ������
     *
     * @param frameName
     * @return
     */
    private JInternalFrame getIFrame(String frameName) {
        JInternalFrame jf = null;
        if (!ifs.containsKey(frameName)) {
            try {
                Class fClass = Class.forName("internalFrame." + frameName);
                Constructor constructor = fClass.getConstructor(null);
                jf = (JInternalFrame) constructor.newInstance(null);
                ifs.put(frameName, jf);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            jf = ifs.get(frameName);
        return jf;
    }


    /**
     * ���������
     */
    private final class FrameListener extends ComponentAdapter {
        public void componentResized(final ComponentEvent e) {
            updateBackImage();
        }
    }


}
