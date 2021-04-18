/*
 * Created by JFormDesigner on Fri Apr 16 20:51:00 CST 2021
 */

package com.llh.view;

import com.llh.utils.CodePicture;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author liuling
 */
public class CodePic extends JDialog {
    //答案
    private loginView log;
    private String key = "";
    public CodePic(Window owner) {
        super((JFrame) owner,"验证码",true);
        this.log = (loginView) owner;
        initComponents();
        try {
            initPic();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setVisible(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }
    private void initPic() throws IOException{
        //若输错了3次
        Object[] objs = CodePicture.createImage();
        BufferedImage image = (BufferedImage) objs[1];
        key = (String) objs[0];
        OutputStream os = null;
        os = new FileOutputStream("images/identify.jpg");
        ImageIO.write(image, "jpg", os);
        os.close();

        //放验证码
        ImageIcon icon=new ImageIcon("images/identify.jpg");
        icon.setImage(icon.getImage().getScaledInstance(250,110,Image.SCALE_DEFAULT));
        picLabel.setIcon(icon);
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        String answer = answerTextField.getText();
        if(answer.equals("")){
            JOptionPane.showMessageDialog(this, "请不要输入空的验证码",
                    "验证结果", JOptionPane.ERROR_MESSAGE);
        }
        if(answer.equals(key)){
            JOptionPane.showMessageDialog(this, "验证成功",
                    "验证结果", JOptionPane.INFORMATION_MESSAGE);
            log.setCount(log.getCount()-1);
            dispose();

        }
        else{
            JOptionPane.showMessageDialog(this, "验证失败",
                    "验证结果", JOptionPane.ERROR_MESSAGE);

        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        picLabel = new JLabel();
        label2 = new JLabel();
        answerTextField = new JTextField();
        button1 = new JButton();

        //======== this ========
        setTitle("\u9a8c\u8bc1\u7801");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(picLabel);
        picLabel.setBounds(65, 35, 250, 110);

        //---- label2 ----
        label2.setText("\u8f93\u5165\u7b54\u6848\uff1a");
        contentPane.add(label2);
        label2.setBounds(60, 180, 70, 30);

        //---- answerTextField ----
        answerTextField.setFont(answerTextField.getFont().deriveFont(answerTextField.getFont().getSize() + 3f));
        contentPane.add(answerTextField);
        answerTextField.setBounds(130, 175, 170, 45);

        //---- button1 ----
        button1.setText("\u786e\u8ba4");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(305, 180), button1.getPreferredSize()));

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
        setSize(405, 305);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel picLabel;
    private JLabel label2;
    private JTextField answerTextField;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
