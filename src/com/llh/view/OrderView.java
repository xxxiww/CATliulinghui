/*
 * Created by JFormDesigner on Thu Apr 15 12:51:54 CST 2021
 */

package com.llh.view;

import java.awt.event.*;

import com.llh.controller.UserController;
import com.llh.entity.Scenic;
import com.llh.entity.User;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

/**
 * @author liuling
 */
public class OrderView extends JDialog {
    private final User user;
    private final Scenic scenic;
    private  UserController userController = new UserController();

    public OrderView(Window owner, User user,Scenic scenic) {
        super((JFrame) owner, "购票页面", true);
        this.user = user;
        this.scenic = scenic;
        //初始化界面
        initComponents();
        balanceTxt.setText(String.valueOf(user.getBalance()));
        moneyTxt.setText(String.valueOf(scenic.getPrice()));
        setVisible(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }
    private void buyButtonActionPerformed(ActionEvent e){
        boolean freezeFlag =  true;
        if(user.getStatus()==2){
            //查冻结表，检查冻结时间
            int freeze = userController.checkFreezeTime(user.getPhoneNumber());
            if(freeze==0){
                //正在冻结时间内内
                freezeFlag = false;
                JOptionPane.showMessageDialog(this, "您被冻结了",
                        "购买结果", JOptionPane.INFORMATION_MESSAGE);
            }
            if(freeze==1){
                //现在的时间在end后面
                //解冻操作
                boolean unfreeze = userController.unfreeze(user);
                boolean deleteFreeze = userController.deleteFreeze(user.getPhoneNumber());
                if(unfreeze==false||deleteFreeze==false){
                    freezeFlag  = false;
                }
            }

        }
        if(freezeFlag){
            if (flag == true) {
                boolean buySuccess = userController.buyTicket(calendar, nowTime, scenic, user, Double.parseDouble(moneyTxt.getText()),tiketNum);
                if (buySuccess == true) {
                    JOptionPane.showMessageDialog(this, "购买成功",
                            "购买结果", JOptionPane.INFORMATION_MESSAGE);
                    balanceTxt.setText(String.valueOf(user.getBalance()));
                } else {
                    JOptionPane.showMessageDialog(this, "购买失败！请检查余额是否充足！若余额充足当天景点票已售空",
                            "购买结果", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "您不能选择该时间",
                        "选择结果", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void timeButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        JTimeChooser chooser = new JTimeChooser(this);
        //获取选择的日期对象
        calendar = chooser.showTimeDialog();
        //当前时间
        nowTime  = Calendar.getInstance();
        flag = userController.judgeTime(calendar,nowTime);
        if(flag == false){
            JOptionPane.showMessageDialog(this, "您不能选择该时间",
                    "选择结果", JOptionPane.ERROR_MESSAGE);
        }



    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        title = new JLabel();
        label2 = new JLabel();
        moneyTxt = new JTextField();
        label3 = new JLabel();
        timeButton = new JButton();
        label4 = new JLabel();
        label5 = new JLabel();
        balanceTxt = new JTextField();

        //======== this ========
        setTitle("\u8d2d\u7968\u9875\u9762");
        setResizable(false);
        setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 20));
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- title ----
        title.setText("\u8d2d\u4e70\u95e8\u7968");
        title.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 20));
        contentPane.add(title);
        title.setBounds(170, 5, 195, 50);

        //---- label2 ----
        label2.setText("\u9009\u62e9\u65f6\u95f4\uff1a");
        label2.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
        contentPane.add(label2);
        label2.setBounds(45, 65, 105, 40);

        //---- moneyTxt ----
        moneyTxt.setEditable(false);
        contentPane.add(moneyTxt);
        moneyTxt.setBounds(175, 185, 195, 40);

        //---- label3 ----
        label3.setText("\u9009\u62e9\u7968\u6570\uff1a");
        label3.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
        contentPane.add(label3);
        label3.setBounds(45, 125, 95, 35);

        //---- timeButton ----
        timeButton.setText("\u9009\u62e9\u65f6\u95f4\u6309\u94ae");
        timeButton.addActionListener(e -> timeButtonActionPerformed(e));
        contentPane.add(timeButton);
        timeButton.setBounds(175, 65, 195, 40);
        JComboBox<String> numberComboBox = new JComboBox<>();
        contentPane.add(numberComboBox);
        numberComboBox.setBounds(175, 125, 195, 40);
        numberComboBox.addItem("1");
        numberComboBox.addItem("2");
        numberComboBox.addItem("3");
        numberComboBox.addItem("4");
        numberComboBox.addItem("5");
        numberComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double num =Double.parseDouble(String.valueOf(numberComboBox.getSelectedItem()));
                tiketNum = Integer.parseInt(String.valueOf(numberComboBox.getSelectedItem()));
                moneyTxt.setText(String.valueOf(num * scenic.getPrice()));
            }
        });

        //---- label4 ----
        label4.setText("\u6240\u9700\u91d1\u989d\uff1a");
        label4.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
        contentPane.add(label4);
        label4.setBounds(45, 185, 90, 40);

        //---- label5 ----
        label5.setText("\u60a8\u7684\u4f59\u989d\uff1a");
        label5.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
        contentPane.add(label5);
        label5.setBounds(45, 245, 105, 50);

        //---- buyBut ----
        JButton buyBut = new JButton();
        buyBut.setText("\u786e\u8ba4\u8d2d\u4e70");
        buyBut.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 16));
        contentPane.add(buyBut);
        buyBut.setBounds(200, 310, 150, 45);
        buyBut.addActionListener(e -> buyButtonActionPerformed(e));



        //---- balanceTxt ----
        balanceTxt.setEditable(false);
        contentPane.add(balanceTxt);
        balanceTxt.setBounds(175, 250, 195, 40);

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
        setSize(470, 435);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel title;
    private JLabel label2;
    private JTextField moneyTxt;
    private JLabel label3;
    private JButton timeButton;
    private JLabel label4;
    private JLabel label5;
    private JTextField balanceTxt;
    private int tiketNum;
    private Calendar calendar;
    private Calendar nowTime;
    private boolean flag;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
