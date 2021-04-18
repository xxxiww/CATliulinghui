/*
 * Created by JFormDesigner on Sat Apr 17 20:12:52 CST 2021
 */

package com.llh.view;

import java.awt.event.*;

import com.llh.controller.UserController;
import com.llh.entity.Complain;
import com.llh.entity.Scenic;
import com.llh.entity.User;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author liuling
 */
public class complainView extends JDialog {
    private final User user;
    private UserController userController = new UserController();
    private String mode = "all";
    public complainView(Window owner, User user) {
        super((JFrame) owner, "投诉页面", true);
        this.user = user;
        //初始化界面
        initComponents();
        fillTable(userController.selectComplain(user.getuID(),mode));
        setVisible(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }

    private void complainButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        new SendComplainView(this,user);
    }
    //初始化表格
    private void fillTable(List<Complain> list){

        DefaultTableModel tableModel = (DefaultTableModel) complainTable.getModel();
        tableModel.setRowCount(0);// 清除原有行
        // 填充数据
        if(list!=null){
            for(Complain complain: list){
                String[] arr=new String[4];
                arr[0]= complain.getComplainTime();
                arr[1]= complain.getAnswerTime();
                arr[2]= complain.getContent();
                arr[3]= complain.getAnswer();
                // 添加数据到表格
                tableModel.addRow(arr);
            }}

        // 更新表格
        complainTable.invalidate();
    }

    private void okButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        mode = "已回复";
        fillTable(userController.selectComplain(user.getuID(),mode));
    }

    private void noButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        mode = "未回复";
        fillTable(userController.selectComplain(user.getuID(),mode));
    }

    private void ascButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        mode = "asc";
        fillTable(userController.selectComplain(user.getuID(),mode));
    }

    private void descButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        mode = "desc";
        fillTable(userController.selectComplain(user.getuID(),mode));
    }
    private void allButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        mode = "all";
        fillTable(userController.selectComplain(user.getuID(),mode));
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        title = new JLabel();
        scrollPane1 = new JScrollPane();
        complainTable = new JTable();
        contentLabel = new JLabel();
        scrollPane2 = new JScrollPane();
        contentTextArea = new JTextArea();
        complainButton = new JButton();
        okButton = new JButton();
        noButton = new JButton();
        ascButton = new JButton();
        descButton = new JButton();
        allButton = new JButton();

        //======== this ========
        setTitle("\u6295\u8bc9\u9875\u9762");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- title ----
        title.setText("\u67e5\u770b\u6295\u8bc9\u56de\u590d");
        title.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 25));
        title.setForeground(new Color(51, 51, 51));
        contentPane.add(title);
        title.setBounds(610, 15, 210, 80);

        //======== scrollPane1 ========
        {

            //---- complainTable ----
            complainTable.setRowHeight(35);
            complainTable.getTableHeader().setFont(new Font("华文楷体", 0, 20));
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(JLabel.CENTER);
            complainTable.setDefaultRenderer(Object.class, tcr);
            complainTable.setFont(new Font("华文楷体", 0, 18));
            complainTable.setModel(new DefaultTableModel(
                    new Object[][]{
                            {null, null, null, null},
                            {null, null, null, null},
                            {null, null, null, null},
                            {null, null, null, null},
                            {null, null, null, null},
                    },
                    new String[]{
                            "\u6295\u8bc9\u65f6\u95f4", "\u56de\u590d\u65f6\u95f4", "\u6295\u8bc9\u5185\u5bb9", "\u56de\u590d\u5185\u5bb9"
                    }
            ) {
                boolean[] columnEditable = new boolean[]{
                        false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            complainTable.getColumnModel().getColumn(0).setMinWidth(100);
            complainTable.getColumnModel().getColumn(1).setMinWidth(100);
            complainTable.getColumnModel().getColumn(2).setMinWidth(300);
            complainTable.getColumnModel().getColumn(3).setMinWidth(300);
            scrollPane1.setViewportView(complainTable);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(40, 110, 1325, 460);
        //给table监听
        complainTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //得到选中的行列的索引值
                int row = complainTable.getSelectedRow();  //行
                //得到选中的单元格值，表格中的都是字符串
                //只要景点名
                Object value = complainTable.getValueAt(row, 3);
                String scenicName = value.toString();
                contentTextArea.setText(scenicName);
            }
        });

        //---- contentLabel ----
        contentLabel.setText("\u56de\u590d\u5185\u5bb9\uff1a");
        contentLabel.setFont(new Font("\u6977\u4f53", Font.PLAIN, 19));
        contentPane.add(contentLabel);
        contentLabel.setBounds(295, 705, 95, 45);

        //======== scrollPane2 ========
        {

            //---- contentTextArea ----
            contentTextArea.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 23));
            contentTextArea.setEditable(false);
            contentTextArea.setLineWrap(true);
            scrollPane2.setViewportView(contentTextArea);
            scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(440, 605, 560, 235);

        //---- complainButton ----
        complainButton.setText("\u6211\u8981\u6295\u8bc9");
        complainButton.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 19));
        complainButton.addActionListener(e -> complainButtonActionPerformed(e));
        contentPane.add(complainButton);
        complainButton.setBounds(1035, 690, 135, 55);
        //---- okButton ----
        okButton.setText("\u67e5\u770b\u5df2\u56de\u590d");
        okButton.addActionListener(e -> okButtonActionPerformed(e));
        contentPane.add(okButton);
        okButton.setBounds(930, 60, 115, 35);

        //---- noButton ----
        noButton.setText("\u67e5\u770b\u672a\u56de\u590d");
        noButton.addActionListener(e -> noButtonActionPerformed(e));
        contentPane.add(noButton);
        noButton.setBounds(1050, 60, 110, 35);

        //---- ascButton ----
        ascButton.setText("\u65f6\u95f4\u5347\u5e8f");
        ascButton.addActionListener(e -> ascButtonActionPerformed(e));
        contentPane.add(ascButton);
        ascButton.setBounds(1170, 60, 95, 35);

        //---- descButton ----
        descButton.setText("\u65f6\u95f4\u964d\u5e8f");
        descButton.addActionListener(e -> descButtonActionPerformed(e));
        contentPane.add(descButton);
        descButton.setBounds(1270, 60, 95, 35);

        //---- allButton ----
        allButton.setText("全部");
        allButton.addActionListener(e -> allButtonActionPerformed(e));
        contentPane.add(allButton);
        allButton.setBounds(1370, 60, 95, 35);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
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
        setSize(1510, 905);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables

    private JLabel title;
    private JScrollPane scrollPane1;
    private JTable complainTable;
    private JLabel contentLabel;
    private JScrollPane scrollPane2;
    private JTextArea contentTextArea;
    private JButton complainButton;
    private JButton okButton;
    private JButton noButton;
    private JButton ascButton;
    private JButton descButton;
    private JButton allButton;
}

