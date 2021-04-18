package com.llh.dao;

import com.llh.bean.HistoryOrder;
import com.llh.bean.Modify;
import com.llh.entity.Complain;
import com.llh.entity.Scenic;
import com.llh.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface UserDao {
    //注册
    boolean register(String userName, String phoneNumber, String password);
    //登录
    User login(String phoneNumber, String password);
    //修改个人信息
    boolean modify(User user, Modify modify);
    //充值
    boolean recharge(User user);


    int buyTicketUser(Connection conn, User user, Scenic scenic, double totalMonney);
    int buyTicketScenic(Connection conn,Scenic scenic,int ticketsNum);

    int buyTicketOrder (Connection conn,User user,String order,String schedule,int ticketsNum,int scenicId);
    List<HistoryOrder> orderInquiry(int userId);

    int freezeUser(String begin, String end, String phone);

    int setFreezeField(String phone);

    int updateFreeze(String begin, String end, String phone);

    Date[] findFreezeTime(String phoneNumber);

    int unfreeze(int id);

    int deleteFreeze(String phoneNumber);

    int sendComplain(Complain complain);

    List<Complain> selectAllComplain(int id);

    List<Complain> selectAscComplain(int id);

    List<Complain> selectDescComplain(int id);

    List<Complain> selectOkComplain(int id);

    List<Complain> selectNoComplain(int id);

    List<Complain> selectAllComplainAd();

    List<Complain> selectAscComplainAd();

    List<Complain> selectDescComplainAd();

    List<Complain> selectOkComplainAd();

    List<Complain> selectNoComplainAd();

    int answerComplain(int id, String content,String answerTime);

    boolean selectPeopleByPhoneNum(String phone);
}



