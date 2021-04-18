package test;

import com.llh.view.JTimeChooser;

import javax.swing.*;
import java.util.Calendar;

public class ttt {
    //返回的数据格式
    private static String s = "yyyy-MM-dd HH:mm:ss";
    public static void main(String[] args) {
       /* HongYeLingGuDate guDate = new HongYeLingGuDate(s);
        guDate.creatDatePicker(new SelectHYDateAbstract() {

            @Override
            public void clickOnSwingToTime(String time) {
                // TODO Auto-generated method stub
                System.out.println("你选择的日期是="+time);
            }
        });*/
        JTimeChooser chooser = new JTimeChooser((JDialog) null);
        Calendar calendar = chooser.showTimeDialog();
        int year=calendar.get(Calendar.YEAR);


        System.out.println(year);


}}
