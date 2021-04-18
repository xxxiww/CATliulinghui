/*
 * Created by JFormDesigner on Sun Apr 18 14:17:22 CST 2021
 */

package com.llh.view;

import com.llh.controller.UserController;
import com.llh.entity.Complain;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author liuling
 */
public class AnswerComplainView extends JDialog {
    private UserController userController = new UserController();
    private String mode = "all";
    public AnswerComplainView(Window owner) {
        super((JFrame) owner, "回复投诉页面", true);
        //初始化界面
        initComponents();
        fillTable(userController.selectComplainAdmin(mode));
        setVisible(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }

    private void noButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        mode = "未回复";
        fillTable(userController.selectComplainAdmin(mode));
    }

    private void okButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        mode = "已回复";
        fillTable(userController.selectComplainAdmin(mode));
    }

    private void ascButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        mode = "asc";
        fillTable(userController.selectComplainAdmin(mode));
    }

    private void descButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        mode = "desc";
        fillTable(userController.selectComplainAdmin(mode));
    }

    private void allButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        mode = "all";
        fillTable(userController.selectComplainAdmin(mode));
    }

    private void submitButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        String id = idTxt.getText();
        String content = contentTxt.getText();
        boolean answer = userController.answerComplain(id,content);
        if(answer){
            JOptionPane.showMessageDialog(this, "回复成功",
                    "回复结果", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this, "回复失败",
                    "回复结果", JOptionPane.ERROR_MESSAGE);
        }
    }

    //初始化表格
    private void fillTable(List<Complain> list){

        DefaultTableModel tableModel = (DefaultTableModel) complainTable.getModel();
        tableModel.setRowCount(0);// 清除原有行
        // 填充数据
        if(list!=null){
            for(Complain complain: list){
                String[] arr=new String[6];
                arr[0]= String.valueOf(complain.getId());
                arr[1]= String.valueOf(complain.getUserId());
                arr[2]= complain.getComplainTime();
                arr[3]= complain.getAnswerTime();
                arr[4]= complain.getContent();
                arr[5]= complain.getAnswer();
                // 添加数据到表格
                tableModel.addRow(arr);
            }}

        // 更新表格
        complainTable.invalidate();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        title = new JLabel();
        scrollPane1 = new JScrollPane();
        complainTable = new JTable();
        idLabel = new JLabel();
        idTxt = new JTextField();
        contentLabel = new JLabel();
        contentScrollPane = new JScrollPane();
        contentTxt = new JTextArea();
        submitButton = new JButton();
        noButton = new JButton();
        okButton = new JButton();
        ascButton = new JButton();
        descButton = new JButton();
        allButton = new JButton();

        //======== this ========
        setResizable(false);
        setFont(new Font("\u534e\u6587\u7ec6\u9ed1", Font.PLAIN, 16));
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- title ----
        title.setText("\u8bf7\u70b9\u51fb\u60f3\u56de\u590d\u7684\u884c");
        title.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 28));
        contentPane.add(title);
        title.setBounds(585, 15, 280, 70);

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
                new Object[][] {
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                },
                new String[] {
                    "\u6295\u8bc9id", "\u7528\u6237id", "\u6295\u8bc9\u65f6\u95f4", "\u56de\u590d\u65f6\u95f4", "\u6295\u8bc9\u5185\u5bb9", "\u56de\u590d\u5185\u5bb9"
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
            complainTable.getColumnModel().getColumn(0).setMinWidth(70);
            complainTable.getColumnModel().getColumn(1).setMinWidth(70);
            complainTable.getColumnModel().getColumn(2).setMinWidth(100);
            complainTable.getColumnModel().getColumn(3).setMinWidth(100);
            complainTable.getColumnModel().getColumn(4).setMinWidth(300);
            complainTable.getColumnModel().getColumn(5).setMinWidth(300);
            scrollPane1.setViewportView(complainTable);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(45, 105, 1430, 560);
        //给table监听
        complainTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //得到选中的行列的索引值
                int row = complainTable.getSelectedRow();  //行
                //得到选中的单元格值，表格中的都是字符串
                Object value = complainTable.getValueAt(row, 0);
                String complainId = value.toString();
                idTxt.setText(complainId);
            }
        });

        //---- idLabel ----
        idLabel.setText("\u6295\u8bc9id\uff1a");
        idLabel.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
        contentPane.add(idLabel);
        idLabel.setBounds(250, 760, 90, 55);

        //---- idTxt ----
        idTxt.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
        idTxt.setEditable(false);
        contentPane.add(idTxt);
        idTxt.setBounds(350, 760, 255, 55);

        //---- contentLabel ----
        contentLabel.setText("\u56de\u590d\u5185\u5bb9\uff1a");
        contentLabel.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
        contentPane.add(contentLabel);
        contentLabel.setBounds(675, 760, 110, 45);

        //======== contentScrollPane ========
        {

            //---- contentTxt ----
            contentTxt.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 18));
            contentTxt.setLineWrap(true);
            contentScrollPane.setViewportView(contentTxt);
            contentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        }
        contentPane.add(contentScrollPane);
        contentScrollPane.setBounds(815, 705, 480, 175);

        //---- submitButton ----
        submitButton.setText("\u786e\u8ba4");
        submitButton.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 16));
        submitButton.addActionListener(e -> submitButtonActionPerformed(e));
        contentPane.add(submitButton);
        submitButton.setBounds(1320, 770, 130, 45);

        //---- noButton ----
        noButton.setText("\u672a\u56de\u590d");
        noButton.setFont(new Font("\u534e\u6587\u7ec6\u9ed1", Font.PLAIN, 16));
        noButton.addActionListener(e -> noButtonActionPerformed(e));
        contentPane.add(noButton);
        noButton.setBounds(985, 60, 90, 35);

        //---- okButton ----
        okButton.setText("\u5df2\u56de\u590d");
        okButton.setFont(new Font("\u534e\u6587\u7ec6\u9ed1", Font.PLAIN, 16));
        okButton.addActionListener(e -> okButtonActionPerformed(e));
        contentPane.add(okButton);
        okButton.setBounds(1080, 60, 90, 35);

        //---- ascButton ----
        ascButton.setText("\u65f6\u95f4\u5347\u5e8f");
        ascButton.setFont(new Font("\u534e\u6587\u7ec6\u9ed1", Font.PLAIN, 16));
        ascButton.addActionListener(e -> ascButtonActionPerformed(e));
        contentPane.add(ascButton);
        ascButton.setBounds(1170, 60, ascButton.getPreferredSize().width, 35);

        //---- descButton ----
        descButton.setText("\u65f6\u95f4\u964d\u5e8f");
        descButton.setFont(new Font("\u534e\u6587\u7ec6\u9ed1", Font.PLAIN, 16));
        descButton.addActionListener(e -> descButtonActionPerformed(e));
        contentPane.add(descButton);
        descButton.setBounds(1270, 60, 100, 35);

        //---- allButton ----
        allButton.setText("\u5168\u90e8");
        allButton.setFont(new Font("\u534e\u6587\u7ec6\u9ed1", Font.PLAIN, 16));
        allButton.addActionListener(e -> allButtonActionPerformed(e));
        contentPane.add(allButton);
        allButton.setBounds(1375, 60, 90, 35);

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
        setSize(1520, 930);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel title;
    private JScrollPane scrollPane1;
    private JTable complainTable;
    private JLabel idLabel;
    private JTextField idTxt;
    private JLabel contentLabel;
    private JScrollPane contentScrollPane;
    private JTextArea contentTxt;
    private JButton submitButton;
    private JButton noButton;
    private JButton okButton;
    private JButton ascButton;
    private JButton descButton;
    private JButton allButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
