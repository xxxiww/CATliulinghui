package com.llh.service;

import com.llh.bean.HistoryOrder;
import com.llh.bean.Modify;
import com.llh.entity.Complain;
import com.llh.entity.Scenic;
import com.llh.entity.User;
import com.sun.org.apache.xpath.internal.operations.Mod;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

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

    //用户修改个人信息功能
    boolean modify(User user, Modify modify);

    /**
     * 用户充值
     * @param user 用户信息
     * @return 是否充值成功
     */
    boolean recharge(User user);

    /**
     *
     * @param calendar 选择时间
     * @return 选择的时间是否在规定范围内
     */
    boolean judgeTime(Calendar calendar,Calendar nowTime);

    boolean buyTicket(Calendar calendar, Calendar nowTime, Scenic scenic, User user, double totalMonney);

    List<HistoryOrder> orderInquiry(int userId);

    boolean judgeFreezeTime(Calendar beginCalendar, Calendar endCalendar);

    boolean freezeUser(String begin, String end, String phone);

    int checkFreezeTime(String phoneNumber);

    boolean unfreeze(User user);

    boolean deleteFreeze(String phoneNumber);

    boolean sendComplain(String content, int id);

    List<Complain> selectComplain(int id, String mode);

    List<Complain> selectComplainAdmin(String mode);

    boolean answerComplain(String id, String content);
}
