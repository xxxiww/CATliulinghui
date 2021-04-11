package com.llh.dao;

import com.llh.entity.User;
import com.llh.utils.DB;

import java.security.KeyStore;
import java.sql.*;

public class UserDaoimpl  implements  UserDao {


    @Override
    public boolean register(String userName, String phoneNumber, String password)  {
        //标识是否登录成功
        boolean registeSuccess = false;
        //连接数据的基本操作
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String headImg = "C:/java/demo/test/images/headxiang.jpg";
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
                String sql1 = "insert into user(phone_number,password,user_name,head_img,status,balance) values('"+phoneNumber+"','"+password+"','"+userName+"','"+headImg+"','0','0')";
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
            {
                int status = rs.getInt("status");
                if(status == 2){
                    //冻结了
                   return  null;
                }
                else if(status == 0||status ==1){
                    //用户或管理员
                    user = new User(rs.getInt("id"), rs.getString("user_name"),rs.getString("phone_number"),rs.getString("password"),rs.getDouble("balance"), rs.getInt("status"),rs.getString("head_img"));
                }
            }
            else{
                //不存在该用户
                return null;
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
}



