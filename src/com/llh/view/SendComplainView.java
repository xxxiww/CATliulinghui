/*
 * Created by JFormDesigner on Sat Apr 17 20:54:55 CST 2021
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
public class SendComplainView extends JDialog {
    private final User user;
    private final UserController userController = new UserController();
    public SendComplainView(Window owner,User user) {
        super((JDialog) owner, "投诉页面", true);
        this.user = user;
        //初始化界面
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }

    private void submitButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        String content = complainTextArea.getText();

          boolean send =  userController.sendComplain(content,user.getuID());
          if(send){
              JOptionPane.showMessageDialog(this, "投诉成功",
                      "投诉结果", JOptionPane.INFORMATION_MESSAGE);
          }
          else{
              JOptionPane.showMessageDialog(this, "投诉失败",
                      "投诉结果", JOptionPane.ERROR_MESSAGE);
          }



    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        complainTextArea = new JTextArea();
        submitButton = new JButton();

        //======== this ========
        setTitle("\u6295\u8bc9\u9875\u9762");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u6295\u8bc9\u5185\u5bb9\uff1a");
        label1.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.BOLD, 16));
        contentPane.add(label1);
        label1.setBounds(20, 25, 90, 45);

        //======== scrollPane1 ========
        {

            //---- complainTextArea ----
            complainTextArea.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
            scrollPane1.setViewportView(complainTextArea);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(120, 25, 380, 290);

        //---- submitButton ----
        submitButton.setText("\u786e\u8ba4");
        submitButton.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
        submitButton.addActionListener(e -> submitButtonActionPerformed(e));
        contentPane.add(submitButton);
        submitButton.setBounds(230, 335, 160, 40);

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
        setSize(565, 435);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTextArea complainTextArea;
    private JButton submitButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
