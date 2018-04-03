package com.sinfeeloo.invoicing.internalframe;

import com.sinfeeloo.invoicing.base.BaseInternalFrame;
import com.sinfeeloo.invoicing.basicinfo.dao.GoodsDao;
import com.sinfeeloo.invoicing.basicinfo.pojo.GoodsBean;
import com.sinfeeloo.invoicing.basicinfo.utils.*;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * @Author: mhj
 * @Desc: ��Ʒ��ѯ
 * @Date: 2018/3/30 20:03
 */
public class GoodsQuery extends BaseInternalFrame {
    private JTable table;
    private JTextField jf_goodsName;
    private JTextField jf_goodsCode;
    private JComboBox jb_brand;
    private JComboBox jb_category;

    @Override
    protected void initView() {
        setIconifiable(true);
        setClosable(true);
        setTitle("��Ʒ��Ϣ��ѯ");
        setMaximizable(true);
        getContentPane().setLayout(new GridBagLayout());
        setBounds(100, 100, 609, 375);

        //���
        table = new JTable();
        table.setEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(30);


        //���ñ�ͷ������ʾ
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        table.getTableHeader().setDefaultRenderer(hr);

        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);

        //���ñ�ͷ
        final DefaultTableModel dftm = (DefaultTableModel) table.getModel();
        String[] tableHeads = new String[]{"���", "��Ʒ����", "��Ʒ����", "��Ʒ������", "Ʒ��",
                "Ʒ��", "���", "������", "���ۼ�", "���", "��Ӧ��", "��ע", "����"};
        dftm.setColumnIdentifiers(tableHeads);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        //���ñ��ı༭��
        table.getColumnModel().getColumn(12).setCellEditor(new MyRender());
        //���ñ�����Ⱦ��
        table.getColumnModel().getColumn(12).setCellRenderer(new MyRender());


        //JScrollPane�Ǵ��й���������塣JScrollPane��Container������࣬Ҳ��һ������������ֻ�����һ�������
        // JScrollPane��һ���÷����Ƚ�һЩ�����ӵ�һ��JPanel�У�Ȼ���ٰ����JPanel��ӵ�JScrollPane��
         JScrollPane scrollPane = new JScrollPane(table);
        //GridBagLayout��һ�����Ĳ��ֹ����������������������������GridBagConstraints
        final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
        gridBagConstraints_6.weighty = 1.0;//�е�Ȩ�أ�ͨ�����������������η����е�ʣ��ռ�
        gridBagConstraints_6.anchor = GridBagConstraints.NORTH;//ͬ���ǵ���������������ʱ��ͨ�� anchor�����������λ�ã�anchor������ֵ�����Ժ���Ե�ֵ�ֱ��� ���ɸ����ĵ����У������в鿴
        gridBagConstraints_6.insets = new Insets(0, 10, 0, 10);//����������������ʱ��ͨ�� insets��ָ�����ܣ����������ң�������϶
        gridBagConstraints_6.fill = GridBagConstraints.BOTH;//�����������ڶ����ܳ������ʱ��ͨ�� fill��ֵ���趨��䷽ʽ�����ĸ�ֵ
        gridBagConstraints_6.gridwidth = 8;//�����ռ������Ҳ������Ŀ��
        gridBagConstraints_6.gridy = 2;//�����������
        gridBagConstraints_6.gridx = 0;//����ĺ�����
        add(scrollPane, gridBagConstraints_6);

        //��ѯ��������Ʒ����
        setupComponet(new JLabel(" ��Ʒ���ƣ�"), 0, 0, 1, 1, false);
        jf_goodsName = new JTextField();
        setupComponet(jf_goodsName, 1, 0, 1, 140, true);

        //��ѯ��������Ʒ����
        setupComponet(new JLabel(" ��Ʒ���룺"), 2, 0, 1, 1, false);
        jf_goodsCode = new JTextField();
        setupComponet(jf_goodsCode, 3, 0, 1, 140, true);

        //��ѯ����-Ʒ��
        setupComponet(new JLabel(" Ʒ�ƣ�"), 0, 1, 1, 1, false);
        jb_brand = new JComboBox();
        jb_brand.setModel(new DefaultComboBoxModel(new String[]{"��", "����", "����", "�¿�˹"}));
        setupComponet(jb_brand, 1, 1, 1, 30, true);

        //��ѯ����-Ʒ��
        setupComponet(new JLabel(" Ʒ�ࣺ"), 2, 1, 1, 1, false);
        jb_category = new JComboBox();
        jb_category.setModel(new DefaultComboBoxModel(new String[]{"��", "ϴ�»�", "�����", "�յ�"}));
        setupComponet(jb_category, 3, 1, 1, 30, true);


        final JButton btn_query = new JButton("��ѯ");
        btn_query.addActionListener(e -> {
            //��ѯ
            query(dftm);
        });
        setupComponet(btn_query, 4, 1, 1, 1, false);

        final JButton btn_reset = new JButton("����");
        btn_reset.addActionListener(e -> {
            //����
            reset(dftm);
        });
        setupComponet(btn_reset, 5, 1, 1, 1, false);
    }


    private void query(DefaultTableModel dftm) {
        List<GoodsBean> goodsList = GoodsDao.getInstance().getGoodsList(jf_goodsName.getText().trim(), jf_goodsCode.getText().trim(), jb_brand.getSelectedItem().toString(), jb_category.getSelectedItem().toString());
        updateTable(goodsList, dftm);
    }

    private void reset(DefaultTableModel dftm) {
        jf_goodsName.setText("");
        jf_goodsCode.setText("");
        jb_brand.setSelectedIndex(0);
        jb_category.setSelectedIndex(0);
        List<GoodsBean> goodsList = GoodsDao.getInstance().getGoodsList(jf_goodsName.getText().trim(), jf_goodsCode.getText().trim(), jb_brand.getSelectedItem().toString(), jb_category.getSelectedItem().toString());
        updateTable(goodsList, dftm);
    }


    /**
     * ˢ���б�
     *
     * @param list
     * @param dftm "���", "��Ʒ����", "��Ʒ����", "��Ʒ������", "Ʒ��",
     *             "Ʒ��", "���", "������", "���ۼ�", "���", "��Ӧ��", "��ע"
     */
    private void updateTable(List<GoodsBean> list, final DefaultTableModel dftm) {
        int num = dftm.getRowCount();
        for (int i = 0; i < num; i++)
            dftm.removeRow(0);

        for (GoodsBean bean : list) {
            Vector rowData = new Vector();
            rowData.add(bean.getId());
            rowData.add(bean.getGoodsname());
            rowData.add(bean.getGoodscode());
            rowData.add(bean.getBarcode());
            rowData.add(bean.getBrandName());
            rowData.add(bean.getCategoryName());
            rowData.add(bean.getSpecification());
            rowData.add(bean.getPurchaseprice());
            rowData.add(bean.getSellingprice());
            rowData.add(bean.getStock());
            rowData.add("");
            rowData.add(bean.getComment());
            dftm.addRow(rowData);
        }
    }

}
