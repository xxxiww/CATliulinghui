/*
 * Created by JFormDesigner on Sun Apr 11 21:52:59 CST 2021
 */

package com.llh.view;

import com.llh.entity.User;

import java.awt.*;
import javax.swing.*;

/**
 * @author liuling
 */
public class personnalView extends JDialog {
    private final User user;

    public personnalView(Window owner,User user) {
        super((JFrame) owner,"\u4e2a\u4eba\u4fe1\u606f",true);
        this.user = user;
        //初始化界面
        initComponents();
        //头像
        ImageIcon icon=new ImageIcon(user.getHeadImg());
        icon.setImage(icon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
        headImg.setIcon(icon);
        nameTextField.setText(user.getUserName());
        phoneTextField.setText(user.getPhoneNumber());
        chargeTextField.setText(String.valueOf(user.getBalance()));
        setVisible(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        title = new JLabel();
        nameLabel = new JLabel();
        nameTextField = new JTextField();
        phoneLabel = new JLabel();
        phoneTextField = new JTextField();
        chargeLabel = new JLabel();
        chargeTextField = new JTextField();
        headImg = new JLabel();
        label7 = new JLabel();

        //======== this ========
        setTitle("\u4e2a\u4eba\u4fe1\u606f");
        setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 20));
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- title ----
        title.setText("\u4e2a\u4eba\u4fe1\u606f");
        title.setFont(new Font("\u534e\u6587\u5f69\u4e91", Font.BOLD, 40));
        title.setForeground(Color.darkGray);
        contentPane.add(title);
        title.setBounds(190, 5, 210, 85);

        //---- nameLabel ----
        nameLabel.setText("\u6635\u79f0\uff1a");
        nameLabel.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 20));
        contentPane.add(nameLabel);
        nameLabel.setBounds(100, 215, 80, 35);

        //---- nameTextField ----
        nameTextField.setEditable(false);
        contentPane.add(nameTextField);
        nameTextField.setBounds(175, 215, 245, 35);

        //---- phoneLabel ----
        phoneLabel.setText("\u624b\u673a\u53f7\u7801\uff1a");
        phoneLabel.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 20));
        contentPane.add(phoneLabel);
        phoneLabel.setBounds(60, 265, 110, 35);

        //---- phoneTextField ----
        phoneTextField.setEditable(false);
        contentPane.add(phoneTextField);
        phoneTextField.setBounds(175, 265, 245, 35);

        //---- chargeLabel ----
        chargeLabel.setText("\u4f59\u989d\uff1a");
        chargeLabel.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 20));
        contentPane.add(chargeLabel);
        chargeLabel.setBounds(100, 310, 65, 40);

        //---- chargeTextField ----
        chargeTextField.setEditable(false);
        contentPane.add(chargeTextField);
        chargeTextField.setBounds(175, 315, 245, 35);

        //---- headImg ----
        headImg.setText("text");
        contentPane.add(headImg);
        headImg.setBounds(235, 105, 100, 100);

        //---- label7 ----
        label7.setText("\u5934\u50cf\uff1a");
        label7.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 20));
        contentPane.add(label7);
        label7.setBounds(100, 165, 70, 25);

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
        setSize(535, 445);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel title;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel chargeLabel;
    private JTextField chargeTextField;
    private JLabel headImg;
    private JLabel label7;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    
}
