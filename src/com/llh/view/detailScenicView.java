/*
 * Created by JFormDesigner on Wed Apr 14 22:42:48 CST 2021
 */

package com.llh.view;

import java.awt.event.*;
import com.llh.entity.Scenic;

import java.awt.*;
import javax.swing.*;

/**
 * @author liuling
 */
public class detailScenicView extends JDialog {
    private  final Scenic scenic;
    public detailScenicView(Window owner, Scenic scenic) {
        super((JFrame) owner,"景点详情",true);
        this.scenic = scenic;
        //初始化界面
        initComponents();
        ImageIcon icon=new ImageIcon(scenic.getScenicImg());
        icon.setImage(icon.getImage().getScaledInstance(365,215,Image.SCALE_DEFAULT));
        imgLable.setIcon(icon);
        nameTextField.setText(scenic.getScenicName());
        localTextField.setText(scenic.getLocation());
        priceTextField.setText(String.valueOf(scenic.getPrice()));
        openTimeTxt.setText(scenic.getOpenTime());
        desTextArea.setText(scenic.getScenicDes());
        setVisible(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }

    private void detailButActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        title = new JLabel();
        label2 = new JLabel();
        imgLable = new JLabel();
        label4 = new JLabel();
        nameTextField = new JTextField();
        label5 = new JLabel();
        localTextField = new JTextField();
        label6 = new JLabel();
        label7 = new JLabel();
        priceTextField = new JTextField();
        openTimeTxt = new JTextField();
        label8 = new JLabel();
        scrollPane1 = new JScrollPane();
        desTextArea = new JTextArea();
        detailBut = new JButton();

        //======== this ========
        setTitle("\u666f\u70b9\u8be6\u60c5\u9875\u9762");
        setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 16));
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- title ----
        title.setText("\u666f\u70b9\u8be6\u60c5");
        title.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.BOLD, 26));
        title.setForeground(new Color(255, 153, 153));
        contentPane.add(title);
        title.setBounds(295, 5, 130, 60);

        //---- label2 ----
        label2.setText("\u666f\u70b9\u56fe\u7247\uff1a");
        label2.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 16));
        contentPane.add(label2);
        label2.setBounds(75, 130, 95, 50);
        contentPane.add(imgLable);
        imgLable.setBounds(185, 60, 365, 215);

        //---- label4 ----
        label4.setText("\u666f\u70b9\u540d\uff1a");
        label4.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 16));
        contentPane.add(label4);
        label4.setBounds(90, 285, 75, 40);

        //---- nameTextField ----
        nameTextField.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 16));
        nameTextField.setEditable(false);
        contentPane.add(nameTextField);
        nameTextField.setBounds(180, 285, 380, 45);

        //---- label5 ----
        label5.setText("\u666f\u70b9\u5730\u70b9\uff1a");
        label5.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 16));
        contentPane.add(label5);
        label5.setBounds(80, 345, 90, 40);

        //---- localTextField ----
        localTextField.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 16));
        localTextField.setEditable(false);
        contentPane.add(localTextField);
        localTextField.setBounds(180, 345, 380, 45);

        //---- label6 ----
        label6.setText("\u7968\u4ef7\uff1a");
        label6.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 16));
        contentPane.add(label6);
        label6.setBounds(110, 405, 55, 45);

        //---- label7 ----
        label7.setText("\u5f00\u653e\u65f6\u95f4\uff1a");
        label7.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 16));
        contentPane.add(label7);
        label7.setBounds(80, 465, 90, 40);

        //---- priceTextField ----
        priceTextField.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 16));
        priceTextField.setEditable(false);
        contentPane.add(priceTextField);
        priceTextField.setBounds(180, 405, 380, 45);

        //---- openTimeTxt ----
        openTimeTxt.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 16));
        openTimeTxt.setEditable(false);
        contentPane.add(openTimeTxt);
        openTimeTxt.setBounds(180, 465, 380, 45);

        //---- label8 ----
        label8.setText("\u8be6\u7ec6\u63cf\u8ff0\uff1a");
        label8.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 16));
        contentPane.add(label8);
        label8.setBounds(80, 590, 85, 45);

        //======== scrollPane1 ========
        {

            //---- desTextArea ----
            desTextArea.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 16));
            desTextArea.setEditable(false);
            desTextArea.setLineWrap(true);
            scrollPane1.setViewportView(desTextArea);
            scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(180, 530, 380, 195);

        //---- detailBut ----
        detailBut.setText("\u67e5\u770b\u8bc4\u8bba");
        detailBut.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 16));
        detailBut.addActionListener(e -> detailButActionPerformed(e));
        contentPane.add(detailBut);
        detailBut.setBounds(265, 750, 185, 55);

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
        setSize(695, 880);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel title;
    private JLabel label2;
    private JLabel imgLable;
    private JLabel label4;
    private JTextField nameTextField;
    private JLabel label5;
    private JTextField localTextField;
    private JLabel label6;
    private JLabel label7;
    private JTextField priceTextField;
    private JTextField openTimeTxt;
    private JLabel label8;
    private JScrollPane scrollPane1;
    private JTextArea desTextArea;
    private JButton detailBut;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
