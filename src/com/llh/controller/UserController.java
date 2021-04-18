package com.llh.controller;

import com.llh.bean.HistoryOrder;
import com.llh.bean.Modify;
import com.llh.entity.Complain;
import com.llh.entity.Scenic;
import com.llh.entity.User;
import com.llh.service.UserService;
import com.llh.service.UserServiceimpl;
import com.llh.utils.CheckPic;

import java.io.File;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class UserController  {
    //这里new了一个UserService层的对象
    private UserService userService =  new UserServiceimpl();

    //登陆方法
    //返回0为用户，1为管理员，2为没登陆成功
    public User login(String phoneNumber, char[] pwd){
        return  userService.login(phoneNumber,pwd);
    }

    //注册方法
    //返回的boolean若为true则注册成功，否则则注册失败
    public boolean register(String userName, String phoneNumber, char[] pwd) {
        //从controller调用service层
        return userService.register(userName, phoneNumber, pwd);
    }
    public boolean modify(User user, Modify modify){
        //判断用户传入的图片路径是否为图片,若不是则返回
        boolean check  = CheckPic.checkPic(modify.getModifyHead());
        if(check == false){
            return  false;
        }
        else{
            return userService.modify(user,modify);
        }

    }
    public  boolean recharge(User user){
        return userService.recharge(user);
    }

    public boolean judgeTime(Calendar calendar,Calendar nowTime) {
        return userService.judgeTime(calendar,nowTime);
    }

    public boolean buyTicket(Calendar calendar, Calendar nowTime, Scenic scenic, User user, double totalMonney,int tiketsNum)
    {
        boolean b =scenic.getTicketsNumber() < tiketsNum || user.getBalance() < totalMonney ;
        if(b){
            return false;
        }
        else{
            return userService.buyTicket(calendar,nowTime,scenic,user,totalMonney);
        }


    }

    public List<HistoryOrder>  orderInquiry(User user){
        int userId = user.getuID();
        return userService.orderInquiry(userId);
    }

    public boolean judgeFreezeTime(Calendar beginCalendar, Calendar endCalendar) {
        return userService.judgeFreezeTime(beginCalendar,endCalendar);
    }

    public boolean freezeUser(String begin, String end, String phone) {
        return  userService.freezeUser(begin,end,phone);
    }

    public int checkFreezeTime(String phoneNumber) {
        return userService.checkFreezeTime(phoneNumber);
    }


    public boolean unfreeze(User user) {
        return  userService.unfreeze(user);
    }

    public boolean deleteFreeze(String phoneNumber) {
        return  userService.deleteFreeze(phoneNumber);
    }

    public boolean sendComplain(String content, int id) {
        if(content.equals("")){
            return false;
        }
        else{
            return userService.sendComplain(content,id);
        }
    }

    public List<Complain> selectComplain(int id, String mode) {
        return userService.selectComplain(id,mode);
    }

    public List<Complain> selectComplainAdmin(String mode) {
        return  userService.selectComplainAdmin(mode);
    }


    public boolean answerComplain(String id, String content) {
        if("".equals(id)||"".equals(content)){
            return false;
        }
        else {
            return userService.answerComplain(id,content);
        }
    }
}
