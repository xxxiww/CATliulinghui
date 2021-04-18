/*
 * Created by JFormDesigner on Mon Apr 12 17:03:48 CST 2021
 */

package com.llh.view;

import java.awt.event.*;
import com.llh.controller.UserController;
import com.llh.entity.User;

import java.awt.*;
import javax.swing.*;

/**
 * @author liuling
 */
public class AdministratorView extends JFrame {
    //controller对象
    private final UserController userController = new UserController();
    private final User user;

    public AdministratorView(User user) {
        this.user = user;
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //设置景点信息
    private void setScenicButActionPerformed(ActionEvent e) {
        // TODO add your code here
        new SetScenicView(this);
    }
    //冻结用户
    private void frozeButActionPerformed(ActionEvent e) {
        // TODO add your code here
        new FreezeUserView(this);
    }
    //回复投诉
    private void replyButActionPerformed(ActionEvent e) {
        // TODO add your code here
        new AnswerComplainView(this);
    }

    private void deleteCommentButActionPerformed(ActionEvent e) {
        // TODO add your code here
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        title = new JLabel();
        replyBut = new JButton();
        frozeBut = new JButton();
        deleteCommentBut = new JButton();
        setScenicBut = new JButton();

        //======== this ========
        setTitle("\u7ba1\u7406\u5458\u4e3b\u9875\u9762");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- title ----
        title.setText("\u60a8\u597d\uff01\u4eb2\u7231\u7684\u7ba1\u7406\u5458\uff01");
        title.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.BOLD, 18));
        title.setForeground(new Color(255, 153, 153));
        contentPane.add(title);
        title.setBounds(105, 10, 200, 30);

        //---- replyBut ----
        replyBut.setText("\u56de\u590d\u7528\u6237\u6295\u8bc9");
        replyBut.addActionListener(e -> replyButActionPerformed(e));
        contentPane.add(replyBut);
        replyBut.setBounds(130, 180, 125, 40);

        //---- frozeBut ----
        frozeBut.setText("\u51bb\u7ed3\u7528\u6237");
        frozeBut.addActionListener(e -> frozeButActionPerformed(e));
        contentPane.add(frozeBut);
        frozeBut.setBounds(130, 120, 125, 40);

        //---- deleteCommentBut ----
        deleteCommentBut.setText("\u5220\u9664\u8bc4\u8bba");
        deleteCommentBut.addActionListener(e -> deleteCommentButActionPerformed(e));
        contentPane.add(deleteCommentBut);
        deleteCommentBut.setBounds(130, 245, 125, 40);

        //---- setScenicBut ----
        setScenicBut.setText("\u8bbe\u7f6e\u666f\u70b9\u4fe1\u606f");
        setScenicBut.addActionListener(e -> setScenicButActionPerformed(e));
        contentPane.add(setScenicBut);
        setScenicBut.setBounds(130, 55, 125, 45);

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
        setSize(435, 365);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel title;
    private JButton replyBut;
    private JButton frozeBut;
    private JButton deleteCommentBut;
    private JButton setScenicBut;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
