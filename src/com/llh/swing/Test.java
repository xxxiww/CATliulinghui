package com.llh.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Test {

    public static String monney;
    private static void createGUI()
    {   //JFrame指一个窗口，构造方法的参数为窗口标题
        MyFrame frame = new MyFrame("Swing Demo");
        JComboBox <String> selectBox = new JComboBox<>();
        frame.add(selectBox);
        selectBox.setBounds(60, 90, 130, 45);
        selectBox.addItem("请选择面额");
        selectBox.addItem("100");
        selectBox.addItem("500");
        selectBox.addItem("1000");
        selectBox.addItemListener(new ItemListener() {
            public  double d;
            @Override
            public void itemStateChanged(ItemEvent e) {
                // TODO add your code here
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    monney = (String) selectBox.getSelectedItem();

                    System.out.println(monney);
                    d=Double.valueOf(monney);
                    System.out.println(d);


                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口其他参数，如窗口大小
        frame.setSize(400, 300);
        //显示窗口
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                createGUI();
            }
        });
    }

}