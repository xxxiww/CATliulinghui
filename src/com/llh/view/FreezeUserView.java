/*
 * Created by JFormDesigner on Sat Apr 17 02:18:39 CST 2021
 */

package com.llh.view;

import com.llh.controller.UserController;
import com.llh.utils.NumberLenghtLimitedDmt;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

/**
 * @author liuling
 */
public class FreezeUserView extends JDialog {
    private final UserController userController = new UserController();
    private Calendar beginCalendar;
    private Calendar endCalendar;
    private String begin;
    private String end;
    public FreezeUserView(Window owner) {
        super((JFrame) owner, "冻结用户页面", true);
        //初始化界面
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }

    private void submitButActionPerformed(ActionEvent e) {
        // TODO add your code here
        String phone = phoneTxt.getText();
        //判断是否没有输入信息
        if(phone.equals("")||beginTimeTxt.getText().equals("")||endTimeTxt.getText().equals("")){
            JOptionPane.showMessageDialog(this, "不能填写空信息",
                    "冻结结果", JOptionPane.ERROR_MESSAGE);//最后一个是消息类型
        }
        else{
            //判断时间是否合法
             boolean judge = userController.judgeFreezeTime(beginCalendar,endCalendar);
             if(judge){
                boolean freeze = userController.freezeUser(begin,end,phoneTxt.getText());
                if(freeze){
                    JOptionPane.showMessageDialog(this, "冻结成功",
                            "冻结结果", JOptionPane.INFORMATION_MESSAGE);//最后一个是消息类型
                }
                else{
                    JOptionPane.showMessageDialog(this, "冻结失败，没找到该用户奥！",
                            "冻结结果", JOptionPane.ERROR_MESSAGE);//最后一个是消息类型

                }
             }
             else{
              JOptionPane.showMessageDialog(this, "不能选择该时间",
                    "冻结结果", JOptionPane.ERROR_MESSAGE);//最后一个是消息类型
              }
        }
    }

    private void beginButActionPerformed(ActionEvent e) {
        // TODO add your code here
        JTimeChooser chooser = new JTimeChooser(this);
        //获取选择的日期对象
        beginCalendar = chooser.showTimeDialog();
        //setText
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date beginDate =beginCalendar.getTime();
        begin = sdf.format(beginDate);
        beginTimeTxt.setText(begin);

    }

    private void endButActionPerformed(ActionEvent e) {
        // TODO add your code here
        JTimeChooser chooser = new JTimeChooser(this);
        //获取选择的日期对象
         endCalendar = chooser.showTimeDialog();
        //setText
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date endDate =endCalendar.getTime();
        end = sdf.format(endDate);
        endTimeTxt.setText(end);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        phoneTxt = new JTextField();
        label3 = new JLabel();
        beginTimeTxt = new JTextField();
        label4 = new JLabel();
        beginBut = new JButton();
        endTimeTxt = new JTextField();
        endBut = new JButton();
        submitBut = new JButton();

        //======== this ========
        setTitle("\u51bb\u7ed3\u7528\u6237\u9875\u9762");
        setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u7528\u6237\u8d26\u53f7\uff1a");
        label1.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
        contentPane.add(label1);
        label1.setBounds(55, 90, 90, 40);

        //---- label2 ----
        label2.setText("\u8bf7\u8f93\u5165\u60a8\u60f3\u51bb\u7ed3\u7684\u7528\u6237\u8d26\u53f7");
        label2.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 20));
        label2.setForeground(Color.orange);
        contentPane.add(label2);
        label2.setBounds(115, 15, 250, 65);

        //---- phoneTxt ----
        phoneTxt.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
        contentPane.add(phoneTxt);
        phoneTxt.setBounds(150, 95, 290, 40);
        phoneTxt.setDocument(new NumberLenghtLimitedDmt(11));

        //---- label3 ----
        label3.setText("\u51bb\u7ed3\u5f00\u59cb\u65f6\u95f4\uff1a");
        label3.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
        contentPane.add(label3);
        label3.setBounds(25, 145, 125, 45);

        //---- beginTimeTxt ----
        beginTimeTxt.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
        beginTimeTxt.setEditable(false);
        contentPane.add(beginTimeTxt);
        beginTimeTxt.setBounds(150, 145, 190, 45);

        //---- label4 ----
        label4.setText("\u51bb\u7ed3\u7ed3\u675f\u65f6\u95f4\uff1a");
        label4.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
        contentPane.add(label4);
        label4.setBounds(25, 200, 120, 45);

        //---- beginBut ----
        beginBut.setText("\u9009\u62e9\u65f6\u95f4");
        beginBut.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
        beginBut.addActionListener(e -> beginButActionPerformed(e));
        contentPane.add(beginBut);
        beginBut.setBounds(345, 145, beginBut.getPreferredSize().width, 45);

        //---- endTimeTxt ----
        endTimeTxt.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
        endTimeTxt.setEditable(false);
        contentPane.add(endTimeTxt);
        endTimeTxt.setBounds(150, 205, 190, 45);

        //---- endBut ----
        endBut.setText("\u9009\u62e9\u65f6\u95f4");
        endBut.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
        endBut.addActionListener(e -> endButActionPerformed(e));
        contentPane.add(endBut);
        endBut.setBounds(345, 205, 100, 45);

        //---- submitBut ----
        submitBut.setText("\u786e\u8ba4");
        submitBut.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 16));
        submitBut.addActionListener(e -> submitButActionPerformed(e));
        contentPane.add(submitBut);
        submitBut.setBounds(175, 290, 155, 45);

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
        setSize(495, 445);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JTextField phoneTxt;
    private JLabel label3;
    private JTextField beginTimeTxt;
    private JLabel label4;
    private JButton beginBut;
    private JTextField endTimeTxt;
    private JButton endBut;
    private JButton submitBut;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
