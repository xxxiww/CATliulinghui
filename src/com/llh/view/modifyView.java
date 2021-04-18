/*
 * Created by JFormDesigner on Mon Apr 12 00:22:24 CST 2021
 */

package com.llh.view;

import java.awt.event.*;
import com.llh.bean.Modify;
import com.llh.controller.UserController;
import com.llh.entity.User;
import com.llh.utils.PasswordLenghtLimitedDmt;
import com.llh.utils.UserNameLenghtLimitedDmt;
import java.awt.*;
import javax.swing.*;

/**
 * @author liuling
 */
public class modifyView extends JDialog {
    private final UserController userController = new UserController();
    private final User user;
    private UploadPic frame;
    
    public modifyView(Window owner,User user) {
        super((JFrame) owner,"\u4fee\u6539\u4e2a\u4eba\u4fe1\u606f",true);
        this.user = user;
        //初始化界面
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }

    //按下按钮
    private void alterButtonActionPerformed(ActionEvent e) {

        //获得用户输入
        String modifyName = nameTextField.getText();
        //头像路径保留原来的头像路径以防不改头像
        String modifyHead = user.getHeadImg();
        //获得用户输入的密码
        String modifyPassword = new String(passwordTextField.getPassword());

        if(modifyPassword.equals("")  || modifyName .equals("") ){
            JOptionPane.showMessageDialog(this, "请勿输入空账号或空密码",
                    "修改结果", JOptionPane.ERROR_MESSAGE);//最后一个是消息类型
        }
        else {
            /*  分情况！
                1.用户不修改头像
                此时frame对象还没有new，所以在这的PicName=="",
                2.用户修改头像
                点击过上传按钮 flag ==2；
             */
            //封装对象
            //若用户先上传了，就有frame对象了
            if(flag==1){
            //获得图片名
            picName=frame.getNamePic();
            if("".equals(picName)==false){
                modifyHead = "images/"+ picName;
            }}
            Modify modify = new Modify(modifyName, modifyHead, modifyPassword);
            //调用controller
            boolean modifySuccess = userController.modify(user, modify);
            if (modifySuccess == true) {
                JOptionPane.showMessageDialog(this, "修改成功啦！",
                        "修改结果", JOptionPane.INFORMATION_MESSAGE);//最后一个是消息类型

            }
            if (modifySuccess == false) {
                JOptionPane.showMessageDialog(this, "请检查您输入的图片路径是否正确",
                        "修改结果", JOptionPane.ERROR_MESSAGE);//最后一个是消息类型
            }
            //刷新头像和名字
            ImageIcon icon=new ImageIcon(user.getHeadImg());
            icon.setImage(icon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
            imgLable.setIcon(icon);
            nameTextField.setText(user.getUserName());
        }
    }


    private void headImgButMouseClicked(MouseEvent e) {
        frame = new UploadPic(new JButton());
        flag =1;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        title = new JLabel();
        headLabel = new JLabel();
        nameLabel = new JLabel();
        nameTextField = new JTextField();
        passwordLabel = new JLabel();
        passwordTextField = new JPasswordField();
        alterButton = new JButton();
        headImgBut = new JButton();
        imgLable = new JLabel();

        //======== this ========
        setTitle("\u4fee\u6539\u4e2a\u4eba\u4fe1\u606f");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- title ----
        title.setText("\u4fee\u6539\u4e2a\u4eba\u4fe1\u606f");
        title.setFont(new Font("\u534e\u6587\u5f69\u4e91", Font.BOLD, 30));
        title.setForeground(new Color(51, 51, 51));
        contentPane.add(title);
        title.setBounds(125, 5, 200, 65);

        //---- headLabel ----
        headLabel.setText("\u4fee\u6539\u5934\u50cf\uff1a");
        headLabel.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
        contentPane.add(headLabel);
        headLabel.setBounds(35, 105, 105, 40);

        //---- nameLabel ----
        nameLabel.setText("\u4fee\u6539\u6635\u79f0\uff1a");
        nameLabel.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
        contentPane.add(nameLabel);
        nameLabel.setBounds(35, 160, 90, 40);
        contentPane.add(nameTextField);
        nameTextField.setBounds(125, 165, 225, 30);

        //---- passwordLabel ----
        passwordLabel.setText("\u4fee\u6539\u5bc6\u7801\uff1a");
        passwordLabel.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
        contentPane.add(passwordLabel);
        passwordLabel.setBounds(35, 215, 90, 35);
        contentPane.add(passwordTextField);
        passwordTextField.setBounds(125, 215, 225, 30);

        //---- alterButton ----
        alterButton.setText("\u786e\u8ba4\u4fee\u6539");
        alterButton.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
        alterButton.addActionListener(e -> alterButtonActionPerformed(e));
        contentPane.add(alterButton);
        alterButton.setBounds(165, 270, 115, 35);

        //---- headImgBut ----
        headImgBut.setText("\u4e0a\u4f20");
        headImgBut.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 15));
        headImgBut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                headImgButMouseClicked(e);
            }
        });
        contentPane.add(headImgBut);
        headImgBut.setBounds(260, 105, 90, 40);
        contentPane.add(imgLable);
        imgLable.setBounds(145, 60, 100, 100);

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
        setSize(465, 355);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        //给昵称和密码做校验
        nameTextField.setDocument(new UserNameLenghtLimitedDmt(8));
        passwordTextField.setDocument(new PasswordLenghtLimitedDmt(16));
        //放一下原来的值
        nameTextField.setText(user.getUserName());
        //放原来的头像
        ImageIcon icon=new ImageIcon(user.getHeadImg());
        icon.setImage(icon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
        imgLable.setIcon(icon);

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel title;
    private JLabel headLabel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordTextField;
    private JButton alterButton;
    private JButton headImgBut;
    private JLabel imgLable;
    private String picName = "";
    private int flag = 0;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
