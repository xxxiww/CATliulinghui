package com.llh.service;

import com.llh.bean.HistoryOrder;
import com.llh.bean.Modify;
import com.llh.dao.UserDao;
import com.llh.dao.UserDaoimpl;
import com.llh.entity.Complain;
import com.llh.entity.Scenic;
import com.llh.entity.User;
import com.llh.utils.DB;
import com.llh.utils.DESUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    @Override
    public boolean modify(User user, Modify modify) {
        //将密码加密，将加密后的密码传给dao
        String passsword = DESUtils.encode(key,modify.getModifyPassword());
        modify.setModifyPassword(passsword);
        return userDao.modify(user,modify);

    }

    @Override
    public boolean recharge(User user) {
        return userDao.recharge(user);
    }

    @Override
    public boolean judgeTime(Calendar calendar,Calendar nowTime) {
        //比较当前时间和用户选择的时间，若用户选择的时间在当前时间之前，返回false
        if(nowTime.after(calendar)){
            return  false;
        }
        else {
            //若用户选择的时间在当前时间+30天之后，返回false
            nowTime.add(Calendar.DATE,30);
            if(calendar.after(nowTime)){
                return false;
            }else {
                nowTime.add(Calendar.DATE,-30);
                return true;
            }

        }
    }

    @Override
    public boolean buyTicket(Calendar calendar, Calendar nowTime, Scenic scenic, User user, double totalMonney) {
        Connection conn = DB.getConn();
        boolean buySuccess = true;
        try {
            conn = DB.getConn();
            conn.setAutoCommit(false);
            //买了几张票
            int ticketsNum = (int) (totalMonney / scenic.getPrice());
            //Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date scheduleTime = calendar.getTime();
            String schedule = sdf.format(scheduleTime);

            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            Date orderTime = nowTime.getTime();
           //  Date orderTime = (Date) calendar.getTime();
            String order = sdf2.format(orderTime);

            //景点的id
            int scenicId = scenic.getsID();

            int count1 = userDao.buyTicketUser( conn, user, scenic,totalMonney);
            int count2 = userDao.buyTicketScenic(conn,scenic,ticketsNum);
            int count3 = userDao.buyTicketOrder (conn,user,order,schedule,ticketsNum,scenicId);
            if(count1==0||count2==0||count3==0){
                conn.rollback();
                return false;
            }
            conn.commit();
            //修改user和scenic中的数据
            user.setBalance(user.getBalance()-totalMonney);
            scenic.setTicketsNumber(scenic.getTicketsNumber()-ticketsNum);

            return buySuccess;

        } catch (Exception e) {
            //回滚事务
            if(conn!= null){
                try {
                    conn.rollback();
                    return false;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            DB.closeConn(conn);
        }
        return false;


    }
    public List<HistoryOrder> orderInquiry(int userId){
        return userDao.orderInquiry(userId);
    }

    @Override
    public boolean judgeFreezeTime(Calendar beginCalendar, Calendar endCalendar) {
        //现在的时间
        Calendar nowTime = Calendar.getInstance();
        //结束时间要在开始时间前
        if(beginCalendar.after(endCalendar)){
            //如果开始时间在结束时间前，返回false
            return false;
        }
        //开始时间必须在现在的时间后面
        else return beginCalendar.after(nowTime);
    }

    @Override
    public boolean freezeUser(String begin, String end, String phone) {
        //不可以冻结管理员
        boolean select  = userDao.selectPeopleByPhoneNum(phone);
        if(select) {
            //两种情况：1.该用户已经被冻结了（要重新设置新的冻结时间） 2.该用户没有被冻结
            int countFreeze = userDao.freezeUser(begin, end, phone);//insert
            int countStatus;
            //countFreeze等于0的情况有1.没找到该用户 2.已经有了该用户的冻结记录
            if (countFreeze == 0) {
                //update
                int countUpdateFreeze = userDao.updateFreeze(begin, end, phone);
                if (countUpdateFreeze == 1) {
                    countStatus = userDao.setFreezeField(phone);
                    if (countStatus == 1) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    //找不到该用户
                    return false;
                }
            } else {
                //2.该用户没有被冻结
                countStatus = userDao.setFreezeField(phone);
                if (countStatus == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        else{
            return false;
        }
    }

    @Override
    public int checkFreezeTime(String phoneNumber) {
        //从userDao里获取冻结表中的beginTime和endTime
        //判断当前时间是否在begin前面或在end后面，若是返回true，否则返回false
        Date[] dates = userDao.findFreezeTime(phoneNumber);
        //dates[0] 为 begin dates[1]为end time
        DateFormat df  = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar c = Calendar.getInstance();
        Date now= c.getTime();

        Date begin = dates[0];
        Date end = dates[1];


        if(end != null && now.getTime()>end.getTime()){
            //现在的时间在end后面
            return 1;
        }
        else if(begin != null && now.getTime()<begin.getTime()){
            //现在的时间在begin前面
            return 2;
        }
        else{
            //正在冻结时间内内
            return  0;
        }

    }

    @Override
    public boolean unfreeze(User user) {
        int count = userDao.unfreeze(user.getuID());
        if(count==1){
            user.setStatus(0);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean deleteFreeze(String phoneNumber) {
        int count =  userDao.deleteFreeze(phoneNumber);
        if(count==1){
            return true;
        }
        else{
            return  false;
        }
    }

    @Override
    public boolean sendComplain(String content, int id) {
        //获得当前时间
        Calendar now = Calendar.getInstance();
        Date nowTime = now.getTime();
        //转换成字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTimeTime = sdf.format(nowTime);
        Complain complain = new Complain(0,id,content,null,"未回复",nowTimeTime,null);
        int count  = userDao.sendComplain(complain);
        if(count==1){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public List<Complain> selectComplain(int id, String mode) {
        if(mode.equals("all")){
            return userDao.selectAllComplain(id);
        }
        if(mode.equals("asc")){
            return userDao.selectAscComplain(id);
        }
        if(mode.equals("desc")){
            return userDao.selectDescComplain(id);
        }
        if(mode.equals("已回复")){
            return userDao.selectOkComplain(id);
        }
        else{
            return userDao.selectNoComplain(id);
        }

    }

    @Override
    public List<Complain> selectComplainAdmin(String mode) {
        if(mode.equals("all")){
            return userDao.selectAllComplainAd();
        }
        if(mode.equals("asc")){
            return userDao.selectAscComplainAd();
        }
        if(mode.equals("desc")){
            return userDao.selectDescComplainAd();
        }
        if(mode.equals("已回复")){
            return userDao.selectOkComplainAd();
        }
        else{
            return userDao.selectNoComplainAd();
        }
    }

    @Override
    public boolean answerComplain(String id, String content) {
        //获得当前时间
        Calendar now = Calendar.getInstance();
        Date nowTime = now.getTime();
        //转换成字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTimeTime = sdf.format(nowTime);
        int count =  userDao.answerComplain(Integer.parseInt(id),content,nowTimeTime);
        if(count == 1){
            return true;
        }
        else{
            return false;
        }
    }
}
