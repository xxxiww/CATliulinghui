/*
 * Created by JFormDesigner on Mon Apr 12 04:38:45 CST 2021
 */

package com.llh.view;

import com.llh.controller.UserController;
import com.llh.entity.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

/**
 * @author liuling
 */
public class RechargeView extends JDialog {
    private final UserController userController = new UserController();
    private final User user;

    public RechargeView(Window owner,User user) {
        super((JFrame) owner,"\u5145\u503c\u9875\u9762",true);
        this.user = user;
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }
    //充值按钮
    private void rechargeButActionPerformed(ActionEvent e){
        if(monney.equals("请选择面额")){
            JOptionPane.showMessageDialog(this, "请先选择充值面额！！",
                    "充值结果", JOptionPane.INFORMATION_MESSAGE);

        }else {
            double monn = Double.valueOf(monney);
            monn = monn + user.getBalance();
            user.setBalance(monn);
            boolean rechargeSuccess = userController.recharge(user);
            if (rechargeSuccess == true) {
                moneyTxt.setText(String.valueOf(user.getBalance()));
                JOptionPane.showMessageDialog(this, "充值成功",
                        "充值结果", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "充值失败",
                        "充值结果", JOptionPane.INFORMATION_MESSAGE);

            }
        }


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        title = new JLabel();
        rechargeBut = new JButton();
        moneyTxt = new JTextField();
        moneyLabel = new JLabel();

        //======== this ========
        setTitle("\u5145\u503c\u9875\u9762");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- title ----
        title.setText("\u8bf7\u9009\u62e9\u60a8\u60f3\u5145\u503c\u7684\u91d1\u989d");
        title.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 28));
        title.setForeground(new Color(51, 51, 51));
        contentPane.add(title);
        title.setBounds(50, 15, 325, 60);
        /////
        JComboBox <String> selectBox = new JComboBox<>();
        contentPane.add(selectBox);
        selectBox.setBounds(60, 90, 130, 45);
        selectBox.addItem("请选择面额");
        selectBox.addItem("100");
        selectBox.addItem("500");
        selectBox.addItem("1000");
        selectBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // TODO add your code here
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    monney = (String) selectBox.getSelectedItem();
                }
            }
        });

        //---- rechargeBut ----
        rechargeBut.setText("\u786e\u8ba4\u5145\u503c");
        rechargeBut.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
        contentPane.add(rechargeBut);
        rechargeBut.addActionListener(e -> rechargeButActionPerformed(e));
        rechargeBut.setBounds(220, 90, 130, 40);

        //---- moneyTxt ----
        moneyTxt.setEditable(false);
        contentPane.add(moneyTxt);
        moneyTxt.setBounds(195, 160, 165, 30);

        //---- moneyLabel ----
        moneyLabel.setText("\u60a8\u73b0\u5728\u7684\u4f59\u989d\u4e3a\uff1a");
        moneyLabel.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
        contentPane.add(moneyLabel);
        moneyLabel.setBounds(45, 155, 165, 40);

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
        setSize(410, 285);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        moneyTxt.setText(String.valueOf(user.getBalance()));
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel title;
    private JButton rechargeBut;
    private JTextField moneyTxt;
    private JLabel moneyLabel;
    private JComboBox selectBox;
    private  String monney = "请选择面额" ;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
