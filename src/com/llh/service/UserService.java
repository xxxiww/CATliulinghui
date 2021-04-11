package com.llh.service;

import com.llh.entity.User;

import java.sql.SQLException;

public interface UserService {
    /**
     * @param
     * @param
     * @param
     * @return
     */
    //用户注册功能
    boolean register(String userName, String phoneNumber, char[] pwd);

    //用户登录功能
    User login(String phoneNumber, char[] pwd);

}
