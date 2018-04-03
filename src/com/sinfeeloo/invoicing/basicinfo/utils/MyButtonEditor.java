package com.sinfeeloo.invoicing.basicinfo.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButtonEditor extends DefaultCellEditor {

    private MyButton button;

    private MyEvent event;

    public MyButtonEditor() {
        super(new JTextField());
        button = new MyButton("ȷ��");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //��������Զ�����¼�������
                event.invoke(e);
            }

        });

    }

    public MyButtonEditor(MyEvent e) {
        this();
        this.event = e;
    }
    /*
    ��д�༭������������һ����ť��JTable
    */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
//      setClickCountToStart(1);
//�����������İ�ť���ڵ��к��зŽ�button����
        button.setRow(row);
        button.setColumn(column);
        return button;
    }


}