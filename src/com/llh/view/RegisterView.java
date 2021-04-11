/*
 * Created by JFormDesigner on Fri Apr 09 23:09:47 CST 2021
 */

package com.llh.view;

import java.awt.event.*;
import com.llh.controller.UserController;
import com.llh.utils.NumberLenghtLimitedDmt;
import com.llh.utils.PasswordLenghtLimitedDmt;
import com.llh.utils.UserNameLenghtLimitedDmt;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.tree.*;

/**
 * @author liuling
 */
public class RegisterView extends JDialog {

    private final UserController userController = new UserController();
    //定义一个控制层的对象，将获取到的数据输入给控制层，再由控制层往下传输，处理之后逐层返回结果


    public RegisterView(Window owner) {
        super((JFrame) owner,"\u6ce8\u518c\u9875\u9762",true);
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        JPanel panel1 = new JPanel();
        JLabel userLabel = new JLabel();
        JTextField userTextField = new JTextField();
        JTextField phoneTextField = new JTextField();
        JLabel passwordlabel = new JLabel();
        JPasswordField passwordField = new JPasswordField();
        JLabel phoneLabel = new JLabel();
        JLabel mainLabel = new JLabel();
        JButton regiserButton = new JButton();
        JLabel label1 = new JLabel();

        //======== this ========
        setTitle("\u6ce8\u518c\u9875\u9762");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        panel1.setBounds(new Rectangle(new Point(135, 130), panel1.getPreferredSize()));

        //---- userLabel ----
        userLabel.setText("\u7528\u6237\u540d\uff1a");
        userLabel.setFont(new Font("\u65b9\u6b63\u8212\u4f53", Font.BOLD, 15));
        contentPane.add(userLabel);
        userLabel.setBounds(70, 95, 95, 40);
        contentPane.add(userTextField);
        userTextField.setBounds(150, 100, 170, 40);
        contentPane.add(phoneTextField);
        phoneTextField.setBounds(150, 155, 170, 40);

        //---- passwordlabel ----
        passwordlabel.setText("\u5bc6\u7801\uff1a");
        passwordlabel.setFont(new Font("\u65b9\u6b63\u8212\u4f53", Font.BOLD, 15));
        contentPane.add(passwordlabel);
        passwordlabel.setBounds(75, 220, passwordlabel.getPreferredSize().width, 25);
        contentPane.add(passwordField);
        passwordField.setBounds(150, 215, 170, 40);

        //---- phoneLabel ----
        phoneLabel.setText("\u7535\u8bdd\u53f7\u7801\uff1a");
        phoneLabel.setFont(new Font("\u65b9\u6b63\u8212\u4f53", Font.BOLD, 15));
        contentPane.add(phoneLabel);
        phoneLabel.setBounds(new Rectangle(new Point(65, 160), phoneLabel.getPreferredSize()));

        //---- mainLabel ----
        mainLabel.setText("\u6ce8\u518c\u9875\u9762");
        mainLabel.setFont(new Font("\u534e\u6587\u5f69\u4e91", Font.BOLD | Font.ITALIC, 36));
        mainLabel.setForeground(new Color(102, 255, 102));
        contentPane.add(mainLabel);
        mainLabel.setBounds(145, 15, 195, 65);

        //---- regiserButton ----
        regiserButton.setText("\u6ce8\u518c");
        regiserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userTextField.getText();
                String phoneNum = phoneTextField.getText();
                char[] password = passwordField.getPassword();
                regiserButtonActionPerformed(userName, phoneNum, password);
            }
        });
        contentPane.add(regiserButton);
        regiserButton.setBounds(170, 280, 130, 40);

        //---- label1 ----
        label1.setText("\u5bc6\u7801\u7684\u6700\u5927\u957f\u5ea6\u53ea\u670916\u4f4d,\u7528\u6237\u540d\u6700\u5927\u957f\u5ea6\u4e3a8\u4f4d\u5662(\u2022\u0324\u0300\u1d55\u2022\u0324\u0301\u0e51)\u1d4e\u1d4e\u1d4e\u1d4e");
        contentPane.add(label1);
        label1.setBounds(85, 330, 335, 45);

        userTextField.setDocument(new UserNameLenghtLimitedDmt(8));
        passwordField.setDocument(new PasswordLenghtLimitedDmt(16));
        phoneTextField.setDocument(new NumberLenghtLimitedDmt(11));

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(450, 425);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }




    //注册按钮监听器
    private void regiserButtonActionPerformed(String userName, String phoneNum, char[] pwd)  {
        /*
        进行数据校验：
        1.已经设置了在输入的时候不能输入空格
        2.手机号不足11位的输入错误
        */

        //从这开始开始注册
        boolean result = userController.register(userName, phoneNum, pwd);
        //分别对应了用户名，手机号（账号），密码
        if(true == result){
            //如果注册成功！弹窗成功
            JOptionPane.showMessageDialog(this, "注册成功",
                    "注册结果", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            //如果注册成功！弹窗失败
            JOptionPane.showMessageDialog(this, "该手机号已被注册，注册失败",
                    "注册结果", JOptionPane.INFORMATION_MESSAGE);
        }


    }
}
