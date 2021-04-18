/*
 * Created by JFormDesigner on Fri Apr 09 02:03:06 CST 2021
 */

package com.llh.view;

import com.llh.controller.UserController;
import com.llh.entity.User;
import com.llh.utils.CodePicture;
import com.llh.utils.NumberLenghtLimitedDmt;
import com.llh.utils.PasswordLenghtLimitedDmt;
import com.llh.utils.PhoneFormatCheckUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author liuling
 */
public class loginView extends JFrame {
    private int count = 0;

    private final UserController userController = new UserController();
    //定义一个控制层的对象，将获取到的数据输入给控制层，再由控制层往下传输，处理之后逐层返回结果
    private User user = null;

    public loginView() {
        initComponents();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    //登录按钮
    private void loginButActionPerformed(String phoneNumber, char[] pwd) {
        if(count>=3){
            new CodePic(this);

        }
        else {
            //校验是否为空账号或空密码
            if (phoneNumber == null || pwd == null) {
                JOptionPane.showMessageDialog(this, "请勿输入空账号或空密码",
                        "登录结果", JOptionPane.ERROR_MESSAGE);//最后一个是消息类型
                //输入空的，conut++
                count++;

            }
            user = userController.login(phoneNumber, pwd);

            if (user != null) {
                int identity = user.getStatus();
                if (identity == 0 || identity == 2) {
                    //普通用户：0；被冻结的用户：2
                    this.dispose();
                    new UserMainView(user);
                }
                if (identity == 1) {
                    //管理员
                    this.dispose();
                    new AdministratorView(user);
                }
            }
            if (user == null) {
                JOptionPane.showMessageDialog(this, "登录失败，请您再次检查您的账号和密码是否输入正确或账号是否冻结",
                        "登录结果", JOptionPane.ERROR_MESSAGE);
                count++;
            }
        }





    }

    //记住密码按钮
    private void rememberPasswordActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    //注册按钮
    //注册功能只能注册普通用户
    private void registerButActionPerformed(ActionEvent e) {
        // 跳出注册弹框
        new RegisterView(this);

    }

    private void initComponents() {
        /*String fileName = "2.jpg";
        String filePath = "C:\\java\\demo\\test\\images\\" + fileName;*/

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        JPanel registerPanel = new JPanel();
        JLabel huige = new JLabel();
        JLabel userLabel = new JLabel();
        JLabel userPassword = new JLabel();
        JTextField userTxt = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginBut = new JButton();
        JButton registerBut = new JButton();
        JCheckBox rememberPassword = new JCheckBox();

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
        huige.setForeground(new Color(0, 0, 153));
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
        //账号必须为11位的手机号（数字）
        userTxt.setDocument(new NumberLenghtLimitedDmt(11));
        passwordField.setDocument(new PasswordLenghtLimitedDmt(16));

        //---- loginBut ----
        loginBut.setText("\u767b\u5f55");
        loginBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户在框中输入的账号和密码，并传给实现函数
                String phoneNumber = userTxt.getText();
                char[] pwd = passwordField.getPassword();
                loginButActionPerformed(phoneNumber,pwd);
            }
        });
        contentPane.add(loginBut);
        loginBut.setBounds(135, 265, 120, 35);

        //---- registerBut ----
        registerBut.setText("\u6ce8\u518c");
        registerBut.addActionListener(e -> registerButActionPerformed(e));
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

    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
