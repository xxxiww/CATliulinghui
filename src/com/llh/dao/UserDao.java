package com.llh.dao;

import com.llh.entity.User;

import java.sql.SQLException;

public interface UserDao {
    boolean register(String userName, String phoneNumber, String password);
    User login(String phoneNumber, String password);



}



