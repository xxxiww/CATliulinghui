package com.llh.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFrame extends JFrame
{
     JLabel lab = new JLabel("颜色哈哈啊哈");
     JComboBox <abab> list = new JComboBox<>();
     JLabel a1 = new JLabel("cao");

    public MyFrame(String title)//构造方法
    {
        super(title);
// 内容面板 (ContentPane)
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());


// 向内容面板里添加控件
        contentPane.add(a1,BorderLayout.CENTER);


    }
    private void updateColor(){
        abab item = (abab)list.getSelectedItem();
        lab.setForeground(item.color);


    }
    public void showTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String timestr = sdf.format(new Date());
//重新给timelable设置
        lab.setText( timestr );
    }
    private  class abab{
        public String text;
        public Color color;
        //构造方法
        public abab(String text, Color color) {
            this.text = text;
            this.color = color;
        }

        @Override
        public String toString() {
            return  this.text;
        }
    }
}

