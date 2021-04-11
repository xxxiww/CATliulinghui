package com.llh.controller;

import com.llh.entity.User;
import com.llh.service.UserService;
import com.llh.service.UserServiceimpl;

import java.sql.SQLException;

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
}
