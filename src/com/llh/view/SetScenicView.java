/*
 * Created by JFormDesigner on Mon Apr 12 17:26:32 CST 2021
 */

package com.llh.view;

import java.awt.event.*;

import com.llh.bean.ModifyScenic;
import com.llh.controller.ScenicController;
import com.llh.controller.UserController;
import com.llh.entity.Scenic;
import com.llh.utils.NumberLenghtLimitedDmt;
import com.llh.utils.UserNameLenghtLimitedDmt;

import java.awt.*;
import javax.swing.*;

/**
 * @author liuling
 */
public class SetScenicView extends JDialog {
    private final ScenicController scenicController = new ScenicController();
    private final UserController userController = new UserController();
    //定义一个控制层的对象，将获取到的数据输入给控制层，再由控制层往下传输，处理之后逐层返回结果
    private final Scenic scenic = null;
    private UploadPic frame;

    public SetScenicView(Window owner) {
        super((JFrame) owner,"\u8bbe\u7f6e\u666f\u70b9\u4fe1\u606f",true);
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }
    //上传图片
    private void imgTextFieldButtonActionPerformed(ActionEvent e) {
        if("删除".equals(chooseFlag)||"选择".equals(chooseFlag)){
            JOptionPane.showMessageDialog(this, "此操作状态下上传图片功能禁用",
                    "操作结果", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            frame = new UploadPic(new JButton());
            flag = 1;
        }

    }
    //提交按钮
    private void submitButtonActionPerformed(ActionEvent e) {
         String img = picName;//若不想更改图片，初始就是""
         String scenicName;
         String local;
         double price;
         int numberTickets;
         String des;
         String openTime;

        // TODO add your code here
        if("选择".equals(chooseFlag)){
            JOptionPane.showMessageDialog(this, "请先选择操作选项",
                    "操作结果", JOptionPane.ERROR_MESSAGE);
        }
        else if("删除".equals(chooseFlag)){
            //景点名
             scenicName = nameTextField.getText();
            boolean delete = scenicController.deleteByName(scenicName);
            if(delete == true){
                JOptionPane.showMessageDialog(this, "删除成功",
                        "操作结果", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(this, "没找到该景点",
                        "操作结果", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
           scenicName=nameTextField.getText();
           local=localTextField.getText();
           price=Double.parseDouble(priceTextField.getText());
           numberTickets=Integer.parseInt(numberTextField.getText());
           des=desTextArea.getText();
           openTime=openTimeTextField.getText();
            if(flag==1){
                //获得图片名
                picName=frame.getNamePic();
                if("".equals(picName)==false){
                    img = "images/"+ picName;
                }}
            ModifyScenic modifyScenic =  new ModifyScenic(img,scenicName,local,price,numberTickets,des,openTime);
            if("更改".equals(chooseFlag)){
                boolean change = scenicController.updateScenic(modifyScenic);
                if(change == true){
                    JOptionPane.showMessageDialog(this, "更改成功",
                            "操作结果", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(this, "没找到该景点",
                            "操作结果", JOptionPane.ERROR_MESSAGE);
                }
            }
             //添加
             else{
                 if("".equals(picName)){
                     JOptionPane.showMessageDialog(this, "请上传图片",
                             "操作结果", JOptionPane.ERROR_MESSAGE);
                 }
                 else {
                     boolean add = scenicController.addScenic(modifyScenic);
                     if (add == true) {
                         JOptionPane.showMessageDialog(this, "添加成功",
                                 "操作结果", JOptionPane.INFORMATION_MESSAGE);
                     } else {
                         JOptionPane.showMessageDialog(this, "添加失败",
                                 "操作结果", JOptionPane.ERROR_MESSAGE);
                     }
                 }

             }
        }
        picName = "";
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        imgLabel = new JLabel();
        title = new JLabel();
        imgTextField = new JButton();
        nameLabel = new JLabel();
        nameTextField = new JTextField();
        localLabel = new JLabel();
        localTextField = new JTextField();
        priceLabel = new JLabel();
        priceTextField = new JTextField();
        numberLabel = new JLabel();
        numberTextField = new JTextField();
        desLabel = new JLabel();
        scrollPane1 = new JScrollPane();
        desTextArea = new JTextArea();
        openTimeLabel = new JLabel();
        openTimeTextField = new JTextField();
        submitButton = new JButton();

        //======== this ========
        setTitle("\u8bbe\u7f6e\u666f\u70b9\u4fe1\u606f");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- imgLabel ----
        imgLabel.setText("上传图片：");
        contentPane.add(imgLabel);
        imgLabel.setBounds(75, 80, 70, 30);

        //---- title ----
        title.setText("\u8bf7\u8f93\u5165\u60f3\u8981\u64cd\u4f5c\u7684\u666f\u70b9\u4fe1\u606f\u5427\uff01\uff01");
        title.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.BOLD, 24));
        contentPane.add(title);
        title.setBounds(145, 10, 390, 45);
        contentPane.add(imgTextField);
        imgTextField.setText("点这里！");
        imgTextField.setBounds(165, 80, 350, 35);
        imgTextField.addActionListener(e -> imgTextFieldButtonActionPerformed(e));

        //---- nameLabel ----
        nameLabel.setText("\u666f\u70b9\u540d\uff1a");
        contentPane.add(nameLabel);
        nameLabel.setBounds(new Rectangle(new Point(85, 140), nameLabel.getPreferredSize()));
        contentPane.add(nameTextField);
        nameTextField.setBounds(165, 130, 350, 35);
        nameTextField.setDocument(new UserNameLenghtLimitedDmt(30));

        //---- localLabel ----
        localLabel.setText("\u5730\u70b9\uff1a");
        contentPane.add(localLabel);
        localLabel.setBounds(95, 180, 55, 30);
        contentPane.add(localTextField);
        localTextField.setBounds(165, 180, 350, 35);

        //---- priceLabel ----
        priceLabel.setText("\u7968\u4ef7\uff1a");
        contentPane.add(priceLabel);
        priceLabel.setBounds(95, 240, 45, 25);
        contentPane.add(priceTextField);
        priceTextField.setBounds(165, 235, 350, 35);
        priceTextField.setDocument(new NumberLenghtLimitedDmt(4));

        //---- numberLabel ----
        numberLabel.setText("\u95e8\u7968\u6570\uff1a");
        contentPane.add(numberLabel);
        numberLabel.setBounds(new Rectangle(new Point(85, 295), numberLabel.getPreferredSize()));
        contentPane.add(numberTextField);
        numberTextField.setBounds(165, 290, 350, 35);
        numberTextField.setDocument(new NumberLenghtLimitedDmt(10));

        //---- desLabel ----
        desLabel.setText("\u63cf\u8ff0\uff1a");
        contentPane.add(desLabel);
        desLabel.setBounds(95, 455, 65, 60);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(desTextArea);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(170, 420, 350, 160);

        //---- openTimeLabel ----
        openTimeLabel.setText("\u5f00\u653e\u65f6\u95f4\uff1a");
        contentPane.add(openTimeLabel);
        openTimeLabel.setBounds(75, 360, 70, 25);
        contentPane.add(openTimeTextField);
        openTimeTextField.setBounds(165, 355, 355, 35);
        //---- chooseComboBox ----
        JComboBox <String> chooseComboBox = new JComboBox<>();
        contentPane.add(chooseComboBox);
        chooseComboBox.setBounds(540, 35, 80, 45);
        chooseComboBox.addItem("选择");
        chooseComboBox.addItem("删除");
        chooseComboBox.addItem("更改");
        chooseComboBox.addItem("增加");
        chooseComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    chooseFlag = (String) chooseComboBox.getSelectedItem();
                    if(chooseFlag.equals("删除")){
                        localTextField.setEditable(false);
                        priceTextField.setEditable(false);
                        numberTextField.setEditable(false);
                        desTextArea.setEditable(false);
                        openTimeTextField.setEditable(false);

                    }
                    else{
                        localTextField.setEditable(true);
                        priceTextField.setEditable(true);
                        numberTextField.setEditable(true);
                        desTextArea.setEditable(true);
                        openTimeTextField.setEditable(true);

                    }
                }
            }
        });
        //---- submitButton ----
        submitButton.setText("\u63d0\u4ea4");
        submitButton.addActionListener(e -> submitButtonActionPerformed(e));
        contentPane.add(submitButton);
        submitButton.setBounds(235, 605, 205, 40);

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
        setSize(655, 690);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private int flag = 0;
    private String picName = "";
    //flag String
    private String chooseFlag = "选择";
    //
    private JLabel imgLabel;
    private JLabel title;
    private JButton imgTextField;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel localLabel;
    private JTextField localTextField;
    private JLabel priceLabel;
    private JTextField priceTextField;
    private JLabel numberLabel;
    private JTextField numberTextField;
    private JLabel desLabel;
    private JScrollPane scrollPane1;
    private JTextArea desTextArea;
    private JLabel openTimeLabel;
    private JTextField openTimeTextField;
    private JComboBox chooseComboBox;
    private JButton submitButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
