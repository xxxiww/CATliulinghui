/*
 * Created by JFormDesigner on Sun Apr 11 21:52:59 CST 2021
 */

package com.llh.view;

import java.awt.*;
import javax.swing.*;

/**
 * @author liuling
 */
public class personnalView extends JDialog {
    public personnalView(Window owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();
        label3 = new JLabel();
        textField2 = new JTextField();
        label4 = new JLabel();
        textField3 = new JTextField();
        label6 = new JLabel();
        label7 = new JLabel();

        //======== this ========
        setTitle("\u4e2a\u4eba\u4fe1\u606f");
        setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 20));
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u4e2a\u4eba\u4fe1\u606f");
        label1.setFont(new Font("\u534e\u6587\u5f69\u4e91", Font.PLAIN, 40));
        label1.setForeground(new Color(255, 204, 255));
        contentPane.add(label1);
        label1.setBounds(190, 5, 210, 85);

        //---- label2 ----
        label2.setText("\u6635\u79f0\uff1a");
        label2.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 20));
        contentPane.add(label2);
        label2.setBounds(100, 215, 80, 35);
        contentPane.add(textField1);
        textField1.setBounds(175, 215, 245, 35);

        //---- label3 ----
        label3.setText("\u624b\u673a\u53f7\u7801\uff1a");
        label3.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 20));
        contentPane.add(label3);
        label3.setBounds(60, 265, 110, 35);
        contentPane.add(textField2);
        textField2.setBounds(175, 265, 245, 35);

        //---- label4 ----
        label4.setText("\u4f59\u989d\uff1a");
        label4.setFont(new Font("\u534e\u6587\u7425\u73c0", Font.PLAIN, 20));
        contentPane.add(label4);
        label4.setBounds(100, 310, 65, 40);
        contentPane.add(textField3);
        textField3.setBounds(175, 315, 245, 35);

        //---- label6 ----
        label6.setText("text");
        contentPane.add(label6);
        label6.setBounds(235, 105, 100, 100);

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
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    private JLabel label3;
    private JTextField textField2;
    private JLabel label4;
    private JTextField textField3;
    private JLabel label6;
    private JLabel label7;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
