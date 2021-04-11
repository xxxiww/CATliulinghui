/*
 * Created by JFormDesigner on Fri Apr 09 02:03:06 CST 2021
 */

package com.llh.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author liuling
 */
public class loginView extends JFrame {
    public loginView() {
        initComponents();
    }

    //登录按钮
    private void loginButActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    //记住密码按钮
    private void rememberPasswordActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        registerPanel = new JPanel();
        huige = new JLabel();
        userLabel = new JLabel();
        userPassword = new JLabel();
        userTxt = new JTextField();
        passwordField = new JPasswordField();
        loginBut = new JButton();
        registerBut = new JButton();
        rememberPassword = new JCheckBox();

        //======== this ========
        setTitle("\u6656\u54e5\u65c5\u793e\u767b\u9646\u754c\u9762");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== registerPanel ========
        {
            registerPanel.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < registerPanel.getComponentCount(); i++) {
                    Rectangle bounds = registerPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = registerPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                registerPanel.setMinimumSize(preferredSize);
                registerPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(registerPanel);
        registerPanel.setBounds(new Rectangle(new Point(115, 185), registerPanel.getPreferredSize()));

        //---- huige ----
        huige.setText("\u6656\u54e5\u65c5\u793e");
        huige.setFont(new Font("\u534e\u6587\u96b6\u4e66", Font.PLAIN, 36));
        contentPane.add(huige);
        huige.setBounds(145, 10, 175, 130);

        //---- userLabel ----
        userLabel.setText("\u8d26\u53f7\uff1a");
        contentPane.add(userLabel);
        userLabel.setBounds(105, 145, 50, 35);

        //---- userPassword ----
        userPassword.setText("\u5bc6\u7801\uff1a");
        contentPane.add(userPassword);
        userPassword.setBounds(new Rectangle(new Point(105, 215), userPassword.getPreferredSize()));
        contentPane.add(userTxt);
        userTxt.setBounds(150, 150, 180, 30);
        contentPane.add(passwordField);
        passwordField.setBounds(150, 210, 180, 30);

        //---- loginBut ----
        loginBut.setText("\u767b\u5f55");
        loginBut.addActionListener(e -> loginButActionPerformed(e));
        contentPane.add(loginBut);
        loginBut.setBounds(135, 265, 120, 35);

        //---- registerBut ----
        registerBut.setText("\u6ce8\u518c");
        contentPane.add(registerBut);
        registerBut.setBounds(330, 335, 85, 35);

        //---- rememberPassword ----
        rememberPassword.setText("\u8bb0\u4f4f\u5bc6\u7801");
        rememberPassword.addActionListener(e -> rememberPasswordActionPerformed(e));
        contentPane.add(rememberPassword);
        rememberPassword.setBounds(new Rectangle(new Point(270, 270), rememberPassword.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(430, 415));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel registerPanel;
    private JLabel huige;
    private JLabel userLabel;
    private JLabel userPassword;
    private JTextField userTxt;
    private JPasswordField passwordField;
    private JButton loginBut;
    private JButton registerBut;
    private JCheckBox rememberPassword;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
