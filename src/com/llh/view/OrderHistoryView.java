/*
 * Created by JFormDesigner on Fri Apr 16 01:15:40 CST 2021
 */

package com.llh.view;

import java.awt.event.*;

import com.llh.bean.HistoryOrder;
import com.llh.controller.UserController;
import com.llh.entity.Scenic;
import com.llh.entity.User;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author liuling
 */
public class OrderHistoryView extends JDialog {
    private final  User user;
    private final UserController userController = new UserController();
    public OrderHistoryView(Window owner, User user) {
        super((JFrame) owner, "历史订单页面", true);
        this.user = user;
        //初始化界面
        initComponents();
        fillTable(userController.orderInquiry(user));
        setVisible(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }
    private void fillTable(List<HistoryOrder> list){

        DefaultTableModel tableModel = (DefaultTableModel) orderTable.getModel();
        tableModel.setRowCount(0);// 清除原有行
        // 填充数据
        if(list!=null){
            for(HistoryOrder historyOrder: list){
                String[] arr=new String[6];
                arr[0]= String.valueOf(historyOrder.getOrderId());
                arr[1]= historyOrder.getScenicName();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date orderTime =historyOrder.getOrderTime();
                String order = sdf.format(orderTime);
                arr[2]= order;
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date scheduleTime = historyOrder.getScheduleTime();
                String schedule = sdf.format(scheduleTime);
                arr[3]= schedule;
                arr[4]= String.valueOf(historyOrder.getTicketNum());
                arr[5]= String.valueOf(historyOrder.getTotal());
                // 添加数据到表格
                tableModel.addRow(arr);
            }}

        // 更新表格
        orderTable.invalidate();
    }


    private void submitButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void orderTableMousePressed(MouseEvent e) {
        // TODO add your code here
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titleLabel = new JLabel();
        scrollPane1 = new JScrollPane();
        orderTable = new JTable();
        chooseTxt = new JTextField();
        subLable = new JLabel();
        submitButton = new JButton();

        //======== this ========
        setTitle("\u5386\u53f2\u8ba2\u5355\u9875\u9762");
        setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- titleLabel ----
        titleLabel.setText("\u5386\u53f2\u8ba2\u5355\u9875\u9762");
        titleLabel.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 28));
        contentPane.add(titleLabel);
        titleLabel.setBounds(495, 10, 310, 75);

        //======== scrollPane1 ========
        {

            //---- orderTable ----
            orderTable.setRowHeight(35);
            orderTable.getTableHeader().setFont(new Font("华文楷体", 0, 20));
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setHorizontalAlignment(JLabel.CENTER);
            orderTable.setDefaultRenderer(Object.class,tcr);
            orderTable.setFont(new Font("华文楷体", 0, 18));
            orderTable.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                },
                new String[] {
                    "\u8ba2\u5355\u53f7", "\u666f\u70b9\u540d", "\u4e0b\u5355\u65f6\u95f4", "\u9884\u5b9a\u65f6\u95f4", "\u8d2d\u4e70\u7968\u6570", "\u603b\u91d1\u989d"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, false, false, false, false, false
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            orderTable.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
            orderTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    orderTableMousePressed(e);
                }
            });
            scrollPane1.setViewportView(orderTable);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(30, 105, 1115, 315);

        //---- chooseTxt ----
        chooseTxt.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
        chooseTxt.setEditable(false);
        contentPane.add(chooseTxt);
        chooseTxt.setBounds(410, 460, 365, 44);

        //---- subLable ----
        subLable.setText("\u6211\u8981\u8bc4\u8bba\uff1a");
        subLable.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
        contentPane.add(subLable);
        subLable.setBounds(295, 465, 110, 40);

        //---- submitButton ----
        submitButton.setText("\u786e\u8ba4");
        submitButton.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
        submitButton.addActionListener(e -> submitButtonActionPerformed(e));
        contentPane.add(submitButton);
        submitButton.setBounds(795, 460, 105, 35);

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
        setSize(1170, 575);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel titleLabel;
    private JScrollPane scrollPane1;
    private JTable orderTable;
    private JTextField chooseTxt;
    private JLabel subLable;
    private JButton submitButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
