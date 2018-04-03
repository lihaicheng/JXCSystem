package com.sinfeeloo.invoicing.basicinfo.ui.panel;

import com.sinfeeloo.invoicing.base.BasePanel;
import com.sinfeeloo.invoicing.basicinfo.dao.GoodsDao;
import com.sinfeeloo.invoicing.basicinfo.listener.LimitNumKeyListener;
import com.sinfeeloo.invoicing.basicinfo.listener.OnlyNumKeyListener;
import com.sinfeeloo.invoicing.basicinfo.pojo.GoodsBean;
import com.sinfeeloo.invoicing.basicinfo.pojo.SelectItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @Author: mhj
 * @Desc:�����Ʒ
 * @Date: 2018/3/30 10:54
 */
public class AddGoodPanel extends BasePanel {
    private JTextField tf_specification;
    private JTextField tf_stock;
    private JTextField tf_sellingprice;
    private JTextField tf_purchaseprice;
    private JTextField tf_category;
    private JTextField tf_brand;
    private JTextField tf_barCode;
    private JTextField tf_goodsCode;
    private JTextField tf_goodsName;
    private JComboBox cb_supplier;
    private int mSupplierId;
    private JTextField tf_comment;

    @Override
    protected void initView() {
        setLayout(new GridBagLayout());
        setBounds(10, 10, 550, 400);

        //��Ʒ����
        setupComponent(new JLabel("��Ʒ���ƣ�"), 0, 0, 1, 1, false);
        tf_goodsName = new JTextField();
        setupComponent(tf_goodsName, 1, 0, 3, 1, true);

        //��Ʒ����
        setupComponent(new JLabel("��Ʒ���룺"), 0, 1, 1, 1, false);
        tf_goodsCode = new JTextField();
        tf_goodsCode.addKeyListener(new OnlyNumKeyListener());
        setupComponent(tf_goodsCode, 1, 1, 3, 10, true);

        //��Ʒ������
        setupComponent(new JLabel("��Ʒ�����룺"), 0, 2, 1, 1, false);
        tf_barCode = new JTextField();
        setupComponent(tf_barCode, 1, 2, 3, 300, true);

        //Ʒ��
        setupComponent(new JLabel("Ʒ�ƣ�"), 0, 3, 1, 1, false);
        tf_brand = new JTextField();
        setupComponent(tf_brand, 1, 3, 1, 130, true);

        //Ʒ��
        setupComponent(new JLabel("Ʒ�ࣺ"), 2, 3, 1, 1, false);
        tf_category = new JTextField();
        setupComponent(tf_category, 3, 3, 1, 1, true);

        //������
        setupComponent(new JLabel("�����ۣ�"), 0, 4, 1, 1, false);
        tf_purchaseprice = new JTextField();
        tf_purchaseprice.addKeyListener(new LimitNumKeyListener());
        setupComponent(tf_purchaseprice, 1, 4, 1, 1, true);

        //���ۼ�
        setupComponent(new JLabel("���ۼۣ�"), 2, 4, 1, 1, false);
        tf_sellingprice = new JTextField();
        tf_purchaseprice.addKeyListener(new LimitNumKeyListener());
        setupComponent(tf_sellingprice, 3, 4, 1, 1, true);

        //���
        setupComponent(new JLabel("��棺"), 0, 5, 1, 1, false);
        tf_stock = new JTextField();
        tf_stock.addKeyListener(new OnlyNumKeyListener());
        setupComponent(tf_stock, 1, 5, 3, 1, true);

        //��Ӧ��
        setupComponent(new JLabel("��Ӧ�̣�"), 0, 6, 1, 1, false);
        cb_supplier = new JComboBox();
        cb_supplier.setMaximumRowCount(5);
        setupComponent(cb_supplier, 1, 6, 3, 1, true);

        //���
        setupComponent(new JLabel("���"), 0, 7, 1, 1, false);
        tf_specification = new JTextField();
        setupComponent(tf_specification, 1, 7, 3, 1, true);

        //��ע
        setupComponent(new JLabel("��ע��"), 0, 8, 1, 1, false);
        tf_comment = new JTextField();
        setupComponent(tf_comment, 1, 8, 3, 1, true);


        //��Ӱ�ť
        JButton jButton = new JButton("���");
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                //�������
                if (checkInfo()) {
                    if (GoodsDao.getInstance().addGood(getData())) {
                        JOptionPane.showMessageDialog(AddGoodPanel.this,
                                "��ӳɹ���", "��Ʒ���", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                    } else {
                        JOptionPane.showMessageDialog(AddGoodPanel.this,
                                "���ʧ�ܣ�", "��Ʒ���", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        setupComponent(jButton, 1, 9, 1, 1, false);

        final GridBagConstraints gridBagConstraints_20 = new GridBagConstraints();
        gridBagConstraints_20.weighty = 1.0;
        gridBagConstraints_20.insets = new Insets(0, 65, 0, 15);
        gridBagConstraints_20.gridy = 9;
        gridBagConstraints_20.gridx = 1;


        // ����ť���¼�������
        JButton resetButton = new JButton("����");
        setupComponent(resetButton, 2, 9, 1, 1, false);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                reset();
            }
        });

    }

    /**
     * ����
     */
    private void reset() {
        tf_purchaseprice.setText("");
        tf_barCode.setText("");
        tf_brand.setText("");
        tf_category.setText("");
        tf_goodsCode.setText("");
        tf_specification.setText("");
        tf_sellingprice.setText("");
        tf_stock.setText("");
        tf_goodsName.setText("");
        tf_comment.setText("");
    }

    /**
     * ��ȡ����
     *
     * @return
     */
    private GoodsBean getData() {
        GoodsBean goodsBean = new GoodsBean();
        goodsBean.setGoodsname(tf_goodsName.getText().trim());
        goodsBean.setGoodscode(tf_goodsCode.getText().trim());
        goodsBean.setBarcode(tf_barCode.getText().trim());
        goodsBean.setBrandName(tf_brand.getText().trim());
        goodsBean.setBrandId(1);
        goodsBean.setCategoryName(tf_category.getText().trim());
        goodsBean.setCategoryId(1);
        goodsBean.setPurchaseprice(Double.parseDouble(tf_purchaseprice.getText().trim()));
        goodsBean.setSellingprice(Double.parseDouble(tf_sellingprice.getText().trim()));
        goodsBean.setStock(Integer.parseInt(tf_stock.getText().trim()));
        goodsBean.setSpecification(tf_specification.getText().trim());
        goodsBean.setSupplierid(mSupplierId);
        goodsBean.setComment(tf_comment.getText().trim());
        goodsBean.setStatus_("");
        return goodsBean;
    }


    /**
     * �������
     */
    private boolean checkInfo() {
        if ("".equals(tf_goodsName.getText())) {
            JOptionPane.showMessageDialog(null, "����д��Ʒ���ƣ�");
            return false;
        }
        if ("".equals(tf_goodsCode.getText())) {
            JOptionPane.showMessageDialog(null, "����д��Ʒ���룡");
            return false;
        }
        if ("".equals(tf_brand.getText())) {
            JOptionPane.showMessageDialog(null, "����д��ƷƷ�ƣ�");
            return false;
        }
        if ("".equals(tf_category.getText())) {
            JOptionPane.showMessageDialog(null, "����д��ƷƷ�࣡");
            return false;
        }
        if ("".equals(tf_purchaseprice.getText())) {
            JOptionPane.showMessageDialog(null, "����д��Ʒ�����ۣ�");
            return false;
        }
        if ("".equals(tf_sellingprice.getText())) {
            JOptionPane.showMessageDialog(null, "����д��Ʒ���ۼ۸�");
            return false;
        }
        if ("".equals(tf_stock.getText())) {
            JOptionPane.showMessageDialog(null, "����д��Ʒ��棡");
            return false;
        }
        if (GoodsDao.getInstance().isHaveThisGood(Integer.parseInt(tf_goodsCode.getText().trim()))) {
            JOptionPane.showMessageDialog(null, "����Ʒ�Ѵ���");
            return false;
        }
        try {
            Double.parseDouble(tf_purchaseprice.getText().trim());
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "�����۸�ʽ����");
            return false;
        }
        try {
            Double.parseDouble(tf_sellingprice.getText().trim());
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "���ۼ۸��ʽ����");
            return false;
        }
        return true;

    }




    // ��ʼ����Ӧ������ѡ���
    public void initSupplierBox() {
        List<SelectItem> list = GoodsDao.getInstance().getSupplierList();
        cb_supplier.removeAllItems();
        for (SelectItem item : list) {
            cb_supplier.addItem(item);
        }
        doSelect();
    }

    /**
     * ѡ��
     */
    private void doSelect() {
        SelectItem selectedItem = (SelectItem) cb_supplier.getSelectedItem();
        mSupplierId = selectedItem.getId();
    }
}
