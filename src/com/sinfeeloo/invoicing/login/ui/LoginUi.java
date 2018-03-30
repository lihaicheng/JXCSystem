package com.sinfeeloo.invoicing.login.ui;

import com.sinfeeloo.invoicing.Main;
import com.sinfeeloo.invoicing.base.BaseFrame;
import com.sinfeeloo.invoicing.login.dao.LoginDao;
import com.sinfeeloo.invoicing.login.pojo.UserBean;
import com.sinfeeloo.invoicing.login.ui.panel.LoginPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @Author: mhj
 * @Desc: ��¼���洰��
 * @Date: 2018/3/28 15:14
 */
public class LoginUi extends BaseFrame {
    private JLabel userLabel;
    private JLabel pwdLabel;
    private JButton exit;
    private JButton login;
    private static UserBean user;

    @Override
    protected void initView() {
        setTitle("��¼��ҵ���������ϵͳ");
        //�����������
        final JPanel panel = new LoginPanel();
        panel.setLayout(null);
        getContentPane().add(panel);//�����������ӵ�������
        setBounds(300, 200, panel.getWidth(), panel.getHeight());

        //�û�����ǩ
        userLabel = new JLabel();
        userLabel.setText("�û�����");
        userLabel.setBounds(100, 135, 200, 18);
        panel.add(userLabel);
        //�û��������
        final JTextField userName = new JTextField();
        userName.setBounds(150, 135, 200, 18);
        panel.add(userName);

        //�����ǩ
        pwdLabel = new JLabel();
        pwdLabel.setText("��  �룺");
        pwdLabel.setBounds(100, 165, 200, 18);
        panel.add(pwdLabel);
        //���������
        final JPasswordField userPassword = new JPasswordField();
        userPassword.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyCode() == 10)
                    login.doClick();
            }
        });
        userPassword.setBounds(150, 165, 200, 18);
        panel.add(userPassword);

        //��½��ť����
        login = new JButton();
        login.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                userName.setText("admin");
                userPassword.setText("123");
                //�������ݿ�ͨ���û�����ѯ���û�
                user = LoginDao.getInstance().getUser(userName.getText(), userPassword.getText());
                if (user.getUsername() == null) {//����û���������������û�����������Ϊ��
                    userName.setText(null);
                    userPassword.setText(null);
                    return;
                }
                setVisible(false);
                //����û�����������ȷ�����ϵͳ������
                new Main();
            }
        });
        login.setText("��¼");
        login.setBounds(180, 195, 60, 18);
        panel.add(login);

        //�˳���ť����
        exit = new JButton();
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        exit.setText("�˳�");
        exit.setBounds(260, 195, 60, 18);
        panel.add(exit);
        setVisible(true);
        setResizable(false);//resizeableֵΪtrueʱ����ʾ�����ɵĴ���������ɸı��С��resizeableֵΪfalseʱ����ʾ���ɵĴ����С���ɳ���Ա�����ģ��û����������ɸı�ô���Ĵ�
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//�ر�
    }
}
