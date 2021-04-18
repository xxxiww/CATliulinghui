/*
 * Created by JFormDesigner on Sun Apr 11 14:47:20 CST 2021
 */

package com.llh.view;

import com.llh.controller.ScenicController;
import com.llh.controller.UserController;
import com.llh.entity.Scenic;
import com.llh.entity.User;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 * @author liuling
 */
public class UserMainView extends JFrame {
    //controller对象
    private final ScenicController scenicController = new ScenicController();
    private final UserController userController = new UserController();
    private  Scenic scenic;
    private final User user;


    public UserMainView(User user) {
        this.user = user;
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

   //初始化表格
    private void fillTable(List<Scenic>list){

            DefaultTableModel tableModel = (DefaultTableModel) scenicTable.getModel();
            tableModel.setRowCount(0);// 清除原有行
             // 填充数据
        if(list!=null){
            for(Scenic scenic: list){
                String[] arr=new String[6];
                arr[0]= scenic.getScenicName();
                arr[1]= scenic.getLocation();
                arr[2]= scenic.getOpenTime();
                arr[3]= String.valueOf(scenic.getPrice());
                arr[4]= String.valueOf(scenic.getCommentsNum());
                arr[5]= scenic.getScenicDes();
                // 添加数据到表格
                tableModel.addRow(arr);
            }}

            // 更新表格
            scenicTable.invalidate();
        }


    //查看个人信息
    private void checkMenuItemActionPerformed(ActionEvent e) {
        // TODO add your code here
        new personnalView(this,user);
    }

    //修改个人信息
    private void alterMenuItemActionPerformed(ActionEvent e) {
        // TODO add your code here
        new modifyView(this,user);
    }

    //查看历史订单
    private void historyMenuItemActionPerformed(ActionEvent e) {
        // TODO add your code here
        new OrderHistoryView(this,user);

    }

    //充值
    private void rechargeMenuItemActionPerformed(ActionEvent e) {
        // TODO add your code here
        new RechargeView(this,user);
    }

    //投诉
    private void complainMenuItemActionPerformed(ActionEvent e) {
        // TODO add your code here
        new complainView(this,user);
    }

    //搜索景点按钮
    private void selectButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    //升序
    private void ascButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        mode = "asc";
        fillTable(scenicController.selectScenic(selectScenic,mode,pageNo));
    }

    //降序
    private void descButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        mode = "desc";
        fillTable(scenicController.selectScenic(selectScenic,mode,pageNo));
    }

    //购票
    private void buyButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        if("".equals(chooseTxt.getText())){
            JOptionPane.showMessageDialog(this, "请先选择想查看的景点奥",
                    "查看结果", JOptionPane.ERROR_MESSAGE);//最后一个是消息类型
        }else{
            Scenic lookScenic = scenicController.selectByName(chooseTxt.getText());

            new OrderView(this,user,lookScenic);
        }

    }

    //查看详情
    private void detailsButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        if("".equals(chooseTxt.getText())){
            JOptionPane.showMessageDialog(this, "请先选择想查看的景点奥",
                    "查看结果", JOptionPane.ERROR_MESSAGE);//最后一个是消息类型
        }else{
        Scenic lookScenic = scenicController.selectByName(chooseTxt.getText());
        new detailScenicView(this,lookScenic);}
    }

    private void page1ActionPerformed(ActionEvent e){
        pageNo = 1;
        fillTable(scenicController.selectScenic(selectScenic,mode,pageNo));

    }
    private void page2ActionPerformed(ActionEvent e){
        pageNo = 2;
        fillTable(scenicController.selectScenic(selectScenic,mode,pageNo));

    }
    private void page3ActionPerformed(ActionEvent e){
        pageNo = 3;
        fillTable(scenicController.selectScenic(selectScenic,mode,pageNo));

    }
    private void nextPageActionPerformed(ActionEvent e){
        pageNo++;
        fillTable(scenicController.selectScenic(selectScenic,mode,pageNo));

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        checkMenuItem = new JMenuItem();
        alterMenuItem = new JMenuItem();
        historyMenuItem = new JMenuItem();
        rechargeMenuItem = new JMenuItem();
        complainMenuItem = new JMenuItem();
        panel2 = new JPanel();
        selectTxt = new JTextField();
        selectButton = new JButton();
        selectLabel = new JLabel();
        chooseTxt = new JTextField();
        chooseLabel = new JLabel();
        buyButton = new JButton();
        detailsButton = new JButton();
        scrollPane1 = new JScrollPane();
        scenicTable = new JTable();
        ascButton = new JButton();
        descButton = new JButton();
        page1 = new JButton();
        page2 = new JButton();
        page3 = new JButton();
        nextPage = new JButton();

        //======== this ========
        setTitle("\u6656\u54e5\u65c5\u793e\u4e3b\u9875\u9762");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u4e2a\u4eba\u4e2d\u5fc3");
                menu1.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 30));

                //---- checkMenuItem ----
                checkMenuItem.setText("\u67e5\u770b\u4e2a\u4eba\u4fe1\u606f");
                checkMenuItem.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 22));
                checkMenuItem.addActionListener(e -> checkMenuItemActionPerformed(e));
                menu1.add(checkMenuItem);

                //---- alterMenuItem ----
                alterMenuItem.setText("\u4fee\u6539\u4e2a\u4eba\u4fe1\u606f");
                alterMenuItem.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 22));
                alterMenuItem.addActionListener(e -> alterMenuItemActionPerformed(e));
                menu1.add(alterMenuItem);

                //---- historyMenuItem ----
                historyMenuItem.setText("\u67e5\u770b\u5386\u53f2\u8ba2\u5355");
                historyMenuItem.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 22));
                historyMenuItem.addActionListener(e -> historyMenuItemActionPerformed(e));
                menu1.add(historyMenuItem);

                //---- rechargeMenuItem ----
                rechargeMenuItem.setText("\u5145\u503c");
                rechargeMenuItem.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 22));
                rechargeMenuItem.addActionListener(e -> rechargeMenuItemActionPerformed(e));
                menu1.add(rechargeMenuItem);

                //---- complainMenuItem ----
                complainMenuItem.setText("\u6295\u8bc9");
                complainMenuItem.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 22));
                complainMenuItem.addActionListener(e -> complainMenuItemActionPerformed(e));
                menu1.add(complainMenuItem);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== panel2 ========
        {
            //搜索框
            panel2.setLayout(null);
            panel2.add(selectTxt);
            selectTxt.setBounds(425, 30, 500, 40);
            selectTxt.setFont(new Font("华文楷体", 0, 20));

            selectTxt.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                     selectScenic = selectTxt.getText().trim();
                     fillTable(scenicController.selectScenic(selectScenic,mode,pageNo));
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                     selectScenic = selectTxt.getText().trim();
                     fillTable(scenicController.selectScenic(selectScenic,mode,pageNo));
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                     selectScenic = selectTxt.getText().trim();
                    fillTable(scenicController.selectScenic(selectScenic,mode,pageNo));
                }
            });


            //---- selectButton ----
            selectButton.setText("\u641c\u7d22");
            selectButton.setFont(new Font("\u534e\u6587\u65b0\u9b4f", Font.PLAIN, 22));
            selectButton.addActionListener(e -> selectButtonActionPerformed(e));
            panel2.add(selectButton);
            selectButton.setBounds(935, 30, 100, 40);

            //---- selectLabel ----
            selectLabel.setText("\u666f\u70b9\u641c\u7d22:");
            selectLabel.setFont(new Font("\u534e\u6587\u65b0\u9b4f", Font.PLAIN, 22));
            panel2.add(selectLabel);
            selectLabel.setBounds(305, 15, 125, 70);
            panel2.add(chooseTxt);
            chooseTxt.setBounds(435, 765, 475, 45);
            chooseTxt.setFont(new Font("华文楷体", 0, 20));
            chooseTxt.setEditable(false);

            //---- chooseLabel ----
            chooseLabel.setText("\u9009\u62e9\u666f\u70b9\uff1a");
            chooseLabel.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 22));
            panel2.add(chooseLabel);
            chooseLabel.setBounds(310, 750, 110, 60);

            //---- buyButton ----
            buyButton.setText("\u8d2d\u7968");
            buyButton.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 22));
            buyButton.addActionListener(e -> buyButtonActionPerformed(e));
            panel2.add(buyButton);
            buyButton.setBounds(955, 765, 105, 45);

            //---- detailsButton ----
            detailsButton.setText("\u67e5\u770b\u8be6\u60c5");
            detailsButton.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 22));
            detailsButton.addActionListener(e -> detailsButtonActionPerformed(e));
            panel2.add(detailsButton);
            detailsButton.setBounds(1070, 765, 150, 45);

            //======== scrollPane1 ========
            {

                //---- scenicTable ----
                /////////////////////////////////////
                scenicTable.setRowHeight(35);
                scenicTable.getTableHeader().setFont(new Font("华文楷体", 0, 20));
                DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
                tcr.setHorizontalAlignment(JLabel.CENTER);
                scenicTable.setDefaultRenderer(Object.class,tcr);
                scenicTable.setFont(new Font("华文楷体", 0, 18));
                scenicTable.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
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
                        "\u666f\u70b9\u540d", "\u6240\u5728\u5730", "\u5f00\u653e\u65f6\u95f4", "\u7968\u4ef7", "\u8bc4\u8bba\u6570", "\u8be6\u7ec6\u63cf\u8ff0"
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

                scrollPane1.setViewportView(scenicTable);
                {//设置宽度
                ////////////////////
                scenicTable.getColumnModel().getColumn(0).setMinWidth(100);
                scenicTable.getColumnModel().getColumn(1).setMinWidth(100);
                scenicTable.getColumnModel().getColumn(3).setMaxWidth(150);
                scenicTable.getColumnModel().getColumn(2).setMinWidth(100);
                scenicTable.getColumnModel().getColumn(4).setMaxWidth(150);
                scenicTable.getColumnModel().getColumn(5).setMinWidth(400);}
            }

            //给table监听
            scenicTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    //得到选中的行列的索引值
                    int row = scenicTable.getSelectedRow();  //行
                    //得到选中的单元格值，表格中的都是字符串
                    //只要景点名
                    Object value = scenicTable.getValueAt(row,0);
                    String scenicName =value.toString();
                    chooseTxt.setText(scenicName);
                }
            });

            panel2.add(scrollPane1);
            scrollPane1.setBounds(35, 90, 1545, 635);

            //---- ascButton ----
            ascButton.setText("\u5347\u5e8f");
            ascButton.setFont(ascButton.getFont().deriveFont(ascButton.getFont().getSize() + 3f));
            ascButton.addActionListener(e -> ascButtonActionPerformed(e));
            panel2.add(ascButton);
            ascButton.setBounds(1280, 35, 120, 40);

            //---- descButton ----
            descButton.setText("\u964d\u5e8f");
            descButton.setFont(descButton.getFont().deriveFont(descButton.getFont().getSize() + 3f));
            descButton.addActionListener(e -> descButtonActionPerformed(e));
            panel2.add(descButton);
            descButton.setBounds(1425, 35, 110, 40);

            //---- page1 ----
            page1.setText("1");
            page1.addActionListener(e -> page1ActionPerformed(e));
            panel2.add(page1);
            page1.setBounds(new Rectangle(new Point(1230, 740), page1.getPreferredSize()));

            //---- page2 ----
            page2.setText("2");
            page2.addActionListener(e -> page2ActionPerformed(e));
            panel2.add(page2);
            page2.setBounds(new Rectangle(new Point(1325, 740), page2.getPreferredSize()));

            //---- page3 ----
            page3.setText("3");
            page3.addActionListener(e -> page3ActionPerformed(e));
            panel2.add(page3);
            page3.setBounds(new Rectangle(new Point(1415, 740), page3.getPreferredSize()));

            //---- nextPage ----
            nextPage.setText("\u4e0b\u4e00\u9875");
            nextPage.addActionListener(e -> nextPageActionPerformed(e));
            panel2.add(nextPage);
            nextPage.setBounds(new Rectangle(new Point(1505, 740), nextPage.getPreferredSize()));

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel2.getComponentCount(); i++) {
                    Rectangle bounds = panel2.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel2.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel2.setMinimumSize(preferredSize);
                panel2.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel2, BorderLayout.CENTER);
        setSize(1615, 930);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private String selectScenic;
    private int pageNo = 1;
    private String mode = "desc";


    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem checkMenuItem;
    private JMenuItem alterMenuItem;
    private JMenuItem historyMenuItem;
    private JMenuItem rechargeMenuItem;
    private JMenuItem complainMenuItem;
    private JPanel panel2;
    private JTextField selectTxt;
    private JButton selectButton;
    private JLabel selectLabel;
    private JTextField chooseTxt;
    private JLabel chooseLabel;
    private JButton buyButton;
    private JButton detailsButton;
    private JScrollPane scrollPane1;
    private JTable scenicTable;
    private JButton ascButton;
    private JButton descButton;
    private JButton page1;
    private JButton page2;
    private JButton page3;
    private JButton nextPage;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
