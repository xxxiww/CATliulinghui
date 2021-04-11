package com.llh.service;

import com.llh.dao.UserDao;
import com.llh.dao.UserDaoimpl;
import com.llh.entity.User;
import com.llh.utils.DESUtils;

import java.sql.SQLException;

public class UserServiceimpl implements UserService {
    private UserDao userDao = new UserDaoimpl();//Dao层user
    public User user = null; //实体类
    private String key = "huigelvsheyyds"; //密码加密的秘钥


    @Override
    public boolean register(String userName, String phoneNumber, char[] pwd) {
        String pwds = new String(pwd);
        String password = DESUtils.encode(key, pwds);
        return  userDao.register(userName, phoneNumber, password);
    }

    @Override
    public User login(String phoneNumber, char[] pwd) {
        //将密码加密，将加密后的密码传给dao
        String pwds = new String(pwd);
        String passsword = DESUtils.encode(key,pwds);
        return userDao.login(phoneNumber,passsword);
    }
}
