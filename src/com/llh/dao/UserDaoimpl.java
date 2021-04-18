package com.llh.dao;

import com.llh.bean.HistoryOrder;
import com.llh.bean.Modify;
import com.llh.entity.Complain;
import com.llh.entity.Scenic;
import com.llh.entity.User;
import com.llh.utils.DB;

import java.security.KeyStore;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

public class UserDaoimpl  implements  UserDao {


    @Override
    public boolean register(String userName, String phoneNumber, String password)  {
        //标识是否注册成功
        boolean registeSuccess = false;
        //连接数据的基本操作
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String headImg = "images/headxiang.jpg";
        try{
            //调用工具类，注册驱动并获取连接对象
            conn = DB.getConn();
            //获取数据库操作对象
            String sqlPhone = "select * from user where phone_number = ?";
            //用PreparedStatement目的是防止sql注入
            ps = DB.getPStmt(conn,sqlPhone);
            ps.setString(1, phoneNumber);
            //执行sql语句
            rs = ps.executeQuery();
            //处理查询结果集
            if(true == rs.next())
                //若有记录，返回false
                return false;
            if(false == rs.next()){
                //数据库中没有重叠的手机号
                String sql1 = "insert into user(phone_number,password,user_name,head_img,status,balance) values('"+phoneNumber+"','"+password+"','"+userName+"','"+headImg+"',0,0)";
                int count = DB.executeUpdate(conn,sql1);
                if(count == 1){
                    registeSuccess = true;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //释放资源
            DB.closeRs(rs);
            DB.closePs(ps);
            DB.closeConn(conn);
        }
        return registeSuccess;


    }

    @Override
    public User login(String phoneNumber, String password) {
        //标识用户的登录
        int loginSuccess = 0 ;
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            //调用工具类，注册驱动并获取连接对象
            conn = DB.getConn();
            //sql语句：查找数据库中是否有这么一个字段是phoneNum和password同时等于传过来的数据
            String findSql = "select * from user where phone_number = ? and password = ? ";
            ps = DB.getPStmt(conn,findSql);
            ps.setString(1, phoneNumber);
            ps.setString(2,password);
            //执行sql语句
            rs = ps.executeQuery();
            //处理查询结果集
            if(rs.next() )
                //有结果集
            {        //用户或管理员
                    user = new User(rs.getInt("id"), rs.getString("user_name"),rs.getString("phone_number"),rs.getString("password"),rs.getDouble("balance"), rs.getInt("status"),rs.getString("head_img"));

            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            DB.closeRs(rs);
            DB.closePs(ps);
            DB.closeConn(conn);

        }
        return user;

    }

    @Override
    public boolean modify(User user, Modify modify) {

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //调用工具类，注册驱动并获取连接对象
            conn = DB.getConn();
            String sqlUpdate = "update user set user_name = ? ,password = ?,head_img = ? where phone_number = ?";
            ps = DB.getPStmt(conn, sqlUpdate);
            ps.setString(1, modify.getModifyName());
            ps.setString(2, modify.getModifyPassword());
            ps.setString(3, modify.getModifyHead());
            ps.setString(4,user.getPhoneNumber());
            int count = ps.executeUpdate();
            if (count == 1) {
                user.setUserName(modify.getModifyName());
                user.setHeadImg(modify.getModifyHead());
                user.setPassword(modify.getModifyPassword());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            DB.closePs(ps);
            DB.closeConn(conn);
        }
        return false;

    }

    @Override
    public boolean recharge(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //调用工具类，注册驱动并获取连接对象
            conn = DB.getConn();
            String sqlUpdate = "update user set balance = ?  where phone_number = ?";
            ps = DB.getPStmt(conn, sqlUpdate);
            ps.setString(1,String.valueOf(user.getBalance()));
            ps.setString(2,user.getPhoneNumber());
            int count = ps.executeUpdate();
            if (count == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            DB.closePs(ps);
            DB.closeConn(conn);
        }
        return false;



    }



    public int buyTicketUser(Connection conn,User user,Scenic scenic,double totalMonney){
        String userSql = "update user set balance = balance - "+totalMonney+" where id = "+user.getuID()+"";
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(userSql);
            return count;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeSt(stmt);
        }
        return 0;
    }

    public int buyTicketScenic(Connection conn,Scenic scenic,int ticketsNum){
        String scenicSql = "update scenic set tickets_number = tickets_number - "+ticketsNum+" where id = "+scenic.getsID()+"";
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(scenicSql);
            return count;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeSt(stmt);
        }
        return 0;
    }

    public int buyTicketOrder (Connection conn,User user,String order,String schedule,int ticketsNum,int scenicId)   {
        String ticketSql ="insert into scenic_order (order_phone,order_time,schedule_time,user_id,order_ticket_number,scenic_id) values ('"+user.getPhoneNumber()+"','"+order+"','"+schedule+"',"+user.getuID()+","+ticketsNum+","+scenicId+")" ;
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(ticketSql);
            return count;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeSt(stmt);
        }
        return 0;
    }

    public List<HistoryOrder>  orderInquiry(int userId){
        List<HistoryOrder> historyOrderList = new ArrayList<>();
        String orderHistorySql=" select o.order_id as orderId,s.scenic_name as scenicName,o.order_time as orderTime,o.schedule_time as scheduleTime,o.order_ticket_number as ticketNum,(o.order_ticket_number * s.price) as total from scenic_order o  join scenic s on o.scenic_id = s.id where o.user_id = "+userId+"";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DB.getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(orderHistorySql);

            while(rs.next()){

                HistoryOrder historyOrder = new HistoryOrder(rs.getInt("orderId"),rs.getString("scenicName"),new java.util.Date(rs.getDate("orderTime").getTime()),new java.util.Date(rs.getDate("scheduleTime").getTime()),rs.getInt("ticketNum"),rs.getDouble("total"));
                historyOrderList.add(historyOrder);
            }
            return historyOrderList;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeRs(rs);
            DB.closeSt(stmt);
            DB.closeConn(conn);
        }
        return historyOrderList;
    }

    @Override
    public int freezeUser(String begin, String end, String phone) {
        String insertFreezeSql = "insert into freeze (user_phone,begin_time,end_time) values ('"+phone+"','"+begin+"','"+end+"')";
        Statement stmt = null;
        Connection conn = null;
        try{
            conn  = DB.getConn();
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(insertFreezeSql);
            return count;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeSt(stmt);
            DB.closeConn(conn);
        }
        return 0;
    }

    @Override
    public int setFreezeField(String phone) {
        String updateSql = "update user set status = 2 where phone_number = '"+phone+"'";
        Statement stmt = null;
        Connection conn = null;
        try{
            conn  = DB.getConn();
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(updateSql);
            return count;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeSt(stmt);
            DB.closeConn(conn);
        }
        return 0;
    }

    @Override
    public int updateFreeze(String begin, String end, String phone) {
        String updateSql = "update freeze  set begin_time = '"+begin+"',end_time = '"+end+"'where user_phone = '"+phone+"' ";
        Statement stmt = null;
        Connection conn = null;
        try{
            conn  = DB.getConn();
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(updateSql);
            return count;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeSt(stmt);
            DB.closeConn(conn);
        }
        return 0;

    }

    @Override
    public Date[] findFreezeTime(String phoneNumber) {
        Date [] date;
        date = new Date[2];
        String findFreezeTime = "select * from freeze where user_phone = '"+phoneNumber+"'";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DB.getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(findFreezeTime);

            while(rs.next()){

              date[0] = new java.util.Date(rs.getTimestamp("begin_time").getTime());
              date[1] = new java.util.Date(rs.getTimestamp("end_time").getTime());

            }
            return date;


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeRs(rs);
            DB.closeSt(stmt);
            DB.closeConn(conn);
        }
        return date;
    }

    @Override
    public int unfreeze(int id) {
        String updateSql = "update user  set status = 0 where id = "+id+" ";
        Statement stmt = null;
        Connection conn = null;
        try{
            conn  = DB.getConn();
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(updateSql);
            return count;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeSt(stmt);
            DB.closeConn(conn);
        }
        return 0;

    }

    @Override
    public int deleteFreeze(String phoneNumber) {
        String deleteSql = "delete from freeze where user_phone = '"+phoneNumber+"'";
        Statement stmt = null;
        Connection conn = null;
        try{
            conn  = DB.getConn();
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(deleteSql);
            return count;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeSt(stmt);
            DB.closeConn(conn);
        }
        return 0;

    }

    @Override
    public int sendComplain(Complain complain) {

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //调用工具类，注册驱动并获取连接对象
            conn = DB.getConn();
            String sqlInsert = "insert into complain (user_id,content,status,complain_time) values (?,?,?,?)";
            ps = DB.getPStmt(conn, sqlInsert);
            ps.setInt(1,complain.getUserId());
            ps.setString(2,complain.getContent());
            ps.setString(3,complain.getStatus());
            ps.setString(4,complain.getComplainTime());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            DB.closePs(ps);
            DB.closeConn(conn);
        }
        return 0;
    }

    @Override
    public List<Complain> selectAllComplain(int id) {
        List<Complain> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String findAllSql = "select * from complain where user_id = "+id+"";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DB.getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(findAllSql);

            while(rs.next()){
                if(rs.getString("status").equals("已回复")){
                    Complain complain  = new Complain(rs.getInt("id"),rs.getInt("user_id"),rs.getString("content"),rs.getString("answer"),rs.getString("status"),sdf.format(new java.util.Date(rs.getTimestamp("complain_time").getTime())),sdf.format(new java.util. Date(rs.getTimestamp("answer_time").getTime())));
                    list.add(complain);}
                if(rs.getString("status").equals("未回复")){
                    Complain complain  = new Complain(rs.getInt("id"),rs.getInt("user_id"),rs.getString("content"),"",rs.getString("status"),sdf.format(new java.util.Date(rs.getTimestamp("complain_time").getTime())),"");
                    list.add(complain);
                }
            }
            return list;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeRs(rs);
            DB.closeSt(stmt);
            DB.closeConn(conn);
        }
        return list;
    }

    @Override
    public List<Complain> selectAscComplain(int id) {
        List<Complain> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String findAllSql = "select * from complain where user_id = "+id+" and status = '已回复' order by answer_time asc";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DB.getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(findAllSql);

            while(rs.next()){
                    Complain complain  = new Complain(rs.getInt("id"),rs.getInt("user_id"),rs.getString("content"),rs.getString("answer"),rs.getString("status"),sdf.format(new java.util.Date(rs.getTimestamp("complain_time").getTime())),sdf.format(new java.util. Date(rs.getTimestamp("answer_time").getTime())));
                    list.add(complain);

            }
            return list;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeRs(rs);
            DB.closeSt(stmt);
            DB.closeConn(conn);
        }
        return list;

    }

    @Override
    public List<Complain> selectDescComplain(int id) {
        List<Complain> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String findAllSql = "select * from complain where user_id = "+id+" and status = '已回复' order by answer_time desc";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DB.getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(findAllSql);

            while(rs.next()){
                Complain complain  = new Complain(rs.getInt("id"),rs.getInt("user_id"),rs.getString("content"),rs.getString("answer"),rs.getString("status"),sdf.format(new java.util.Date(rs.getTimestamp("complain_time").getTime())),sdf.format(new java.util. Date(rs.getTimestamp("answer_time").getTime())));
                list.add(complain);

            }
            return list;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeRs(rs);
            DB.closeSt(stmt);
            DB.closeConn(conn);
        }
        return list;
    }

    @Override
    public List<Complain> selectOkComplain(int id) {
        List<Complain> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String findAllSql = "select * from complain where user_id = "+id+" and status = '已回复'";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DB.getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(findAllSql);

            while(rs.next()){

                    Complain complain  = new Complain(rs.getInt("id"),rs.getInt("user_id"),rs.getString("content"),rs.getString("answer"),rs.getString("status"),sdf.format(new java.util.Date(rs.getTimestamp("complain_time").getTime())),sdf.format(new java.util. Date(rs.getTimestamp("answer_time").getTime())));
                    list.add(complain);
            }
            return list;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeRs(rs);
            DB.closeSt(stmt);
            DB.closeConn(conn);
        }
        return list;
    }

    @Override
    public List<Complain> selectNoComplain(int id) {
        List<Complain> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String findAllSql = "select * from complain where user_id = "+id+" and status = '未回复'";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DB.getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(findAllSql);

            while(rs.next()){
                    Complain complain  = new Complain(rs.getInt("id"),rs.getInt("user_id"),rs.getString("content"),"",rs.getString("status"),sdf.format(new java.util.Date(rs.getTimestamp("complain_time").getTime())),"");
                    list.add(complain);

            }
            return list;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeRs(rs);
            DB.closeSt(stmt);
            DB.closeConn(conn);
        }
        return list;
    }

    @Override
    public List<Complain> selectAllComplainAd() {
        List<Complain> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String findAllSql = "select * from complain ";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DB.getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(findAllSql);

            while(rs.next()){
                if(rs.getString("status").equals("已回复")){
                    Complain complain  = new Complain(rs.getInt("id"),rs.getInt("user_id"),rs.getString("content"),rs.getString("answer"),rs.getString("status"),sdf.format(new java.util.Date(rs.getTimestamp("complain_time").getTime())),sdf.format(new java.util. Date(rs.getTimestamp("answer_time").getTime())));
                    list.add(complain);}
                if(rs.getString("status").equals("未回复")){
                    Complain complain  = new Complain(rs.getInt("id"),rs.getInt("user_id"),rs.getString("content"),"",rs.getString("status"),sdf.format(new java.util.Date(rs.getTimestamp("complain_time").getTime())),"");
                    list.add(complain);
                }
            }
            return list;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeRs(rs);
            DB.closeSt(stmt);
            DB.closeConn(conn);
        }
        return list;
    }

    @Override
    public List<Complain> selectAscComplainAd() {
        List<Complain> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String findAllSql = "select * from complain where status = '未回复' order by complain_time asc";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DB.getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(findAllSql);

            while(rs.next()){
                Complain complain  = new Complain(rs.getInt("id"),rs.getInt("user_id"),rs.getString("content"),"",rs.getString("status"),sdf.format(new java.util.Date(rs.getTimestamp("complain_time").getTime())),"");
                list.add(complain);

            }
            return list;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeRs(rs);
            DB.closeSt(stmt);
            DB.closeConn(conn);
        }
        return list;
    }

    @Override
    public List<Complain> selectDescComplainAd() {
        List<Complain> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String findAllSql = "select * from complain where status = '未回复' order by complain_time desc";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DB.getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(findAllSql);

            while(rs.next()){
                Complain complain  = new Complain(rs.getInt("id"),rs.getInt("user_id"),rs.getString("content"),"",rs.getString("status"),sdf.format(new java.util.Date(rs.getTimestamp("complain_time").getTime())),"");
                list.add(complain);

            }
            return list;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeRs(rs);
            DB.closeSt(stmt);
            DB.closeConn(conn);
        }
        return list;
    }

    @Override
    public List<Complain> selectOkComplainAd() {
        List<Complain> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String findAllSql = "select * from complain where status = '已回复'";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DB.getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(findAllSql);

            while(rs.next()){

                Complain complain  = new Complain(rs.getInt("id"),rs.getInt("user_id"),rs.getString("content"),rs.getString("answer"),rs.getString("status"),sdf.format(new java.util.Date(rs.getTimestamp("complain_time").getTime())),sdf.format(new java.util. Date(rs.getTimestamp("answer_time").getTime())));
                list.add(complain);
            }
            return list;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeRs(rs);
            DB.closeSt(stmt);
            DB.closeConn(conn);
        }
        return list;
    }

    @Override
    public List<Complain> selectNoComplainAd() {
        List<Complain> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String findAllSql = "select * from complain where  status = '未回复'";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DB.getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(findAllSql);

            while(rs.next()){
                Complain complain  = new Complain(rs.getInt("id"),rs.getInt("user_id"),rs.getString("content"),"",rs.getString("status"),sdf.format(new java.util.Date(rs.getTimestamp("complain_time").getTime())),"");
                list.add(complain);

            }
            return list;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeRs(rs);
            DB.closeSt(stmt);
            DB.closeConn(conn);
        }
        return list;
    }

    @Override
    public int answerComplain(int id, String content,String answerTime) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //调用工具类，注册驱动并获取连接对象
            conn = DB.getConn();
            String sqlInsert = "update complain set status = ?,answer = ?,answer_time = ? where id = ?";
            ps = DB.getPStmt(conn, sqlInsert);
            ps.setString(1,"已回复");
            ps.setString(2,content);
            ps.setString(3,answerTime);
            ps.setInt(4,id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            DB.closePs(ps);
            DB.closeConn(conn);
        }
        return 0;

    }

    @Override
    public boolean selectPeopleByPhoneNum(String phone) {
        String findAllSql = "select * from user where  status = 1 and phone_number = '"+phone+"'";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DB.getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(findAllSql);

            while(rs.next()){
               return false;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.closeRs(rs);
            DB.closeSt(stmt);
            DB.closeConn(conn);
        }
        return true;
    }


}



