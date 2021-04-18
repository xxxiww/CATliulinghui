package com.llh.dao;

import com.llh.bean.ModifyScenic;
import com.llh.entity.Scenic;
import com.llh.utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ScenicDaoimpl implements ScenicDao {
    public Scenic scenic = null; //实体类

    public boolean updateScenic(ModifyScenic modifyScenic){

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //调用工具类，注册驱动并获取连接对象
            conn = DB.getConn();
            if(modifyScenic.getImg().equals("")){
                String sqlUpdate = "update scenic set scenic_name = ?,location= ?,description= ? ,open_time = ?,tickets_number = ? ,price = ?";
                ps = DB.getPStmt(conn, sqlUpdate);
                ps.setString(1,modifyScenic.getName());
                ps.setString(2, modifyScenic.getLocal() );
                ps.setString(3, modifyScenic.getDes());
                ps.setString(4, modifyScenic.getOpenTime());
                ps.setInt(5,modifyScenic.getNumberTickets());
                ps.setDouble(6,modifyScenic.getPrice()); }
            else{
            String sqlUpdate = "update scenic set scenic_name = ?,location= ?,description= ? ,open_time = ?,tickets_number = ? ,scenic_img = ?,price = ?";
            ps = DB.getPStmt(conn, sqlUpdate);
            ps.setString(1,modifyScenic.getName());
            ps.setString(2, modifyScenic.getLocal() );
            ps.setString(3, modifyScenic.getDes());
            ps.setString(4, modifyScenic.getOpenTime());
            ps.setInt(5,modifyScenic.getNumberTickets());
            ps.setString(6, modifyScenic.getImg());
            ps.setDouble(7,modifyScenic.getPrice());}
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


    public boolean deleteByName(String scenicName){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //调用工具类，注册驱动并获取连接对象
            conn = DB.getConn();
            String sqlDelete = "delete from scenic where scenic_name = ?";
            ps = DB.getPStmt(conn, sqlDelete);
            ps.setString(1,scenicName);
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
    public  boolean addScenic(ModifyScenic modifyScenic){
        Connection conn = null;
        Statement stmt = null;
        try {
            //调用工具类，注册驱动并获取连接对象
            conn = DB.getConn();
            String sqlAdd ="insert into scenic (scenic_name,location,description,price,open_time,tickets_number,scenic_img,comments_number) values('"+modifyScenic.getName()+"','"+modifyScenic.getLocal()+"','"+modifyScenic.getDes()+"',"+modifyScenic.getPrice()+",'"+modifyScenic.getOpenTime()+"',"+modifyScenic.getNumberTickets()+",'"+modifyScenic.getImg()+"',0)";
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(sqlAdd);
            if (count == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            DB.closeSt(stmt);
            DB.closeConn(conn);
        }
        return false;

    }

    public List<Scenic> selectScenic(String select,String mode,int pageNo) {
        List<Scenic> scenicList = new ArrayList<>();
        String sqlSelect;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int pageSize = 10;
        try {
            conn = DB.getConn();
            //调用工具类，注册驱动并获取连接对象
            if ("desc".equals(mode)) {
                sqlSelect = "select * from scenic where scenic_name like ? or description like ? or location like ? order by comments_number desc limit ?,?";
            } else {
                sqlSelect = "select * from scenic where scenic_name like ? or description like ? or location like ? order by comments_number asc limit ?,?";
            }
            ps = conn.prepareStatement(sqlSelect);
            ps.setString(1, "%"+select+"%");
            ps.setString(2,"%"+select+"%");
            ps.setString(3, "%"+select+"%");
            ps.setInt(4, (pageNo - 1) * pageSize);
            ps.setInt(5, pageSize);
            rs = ps.executeQuery();
            while(rs.next()){
                Scenic sc = new Scenic(rs.getInt("id"),rs.getString("scenic_name"),rs.getString("description"),rs.getString("location"),rs.getInt("comments_number"),rs.getInt("price"),rs.getString("open_time"),rs.getInt("tickets_number"),rs.getString("scenic_img"));
                scenicList.add(sc);
            }
            return scenicList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            DB.closePs(ps);
            DB.closeConn(conn);

        }
        return null;



    }

    public Scenic selectByName(String lookScenic){
        Scenic scenic = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs  = null;
        try {
            //调用工具类，注册驱动并获取连接对象
            conn = DB.getConn();
            stmt = conn.createStatement();
            String sqlFind = "select *  from scenic where scenic_name = '"+lookScenic+"'";
            rs = stmt.executeQuery(sqlFind);
            while(rs.next()){
                scenic = new Scenic(rs.getInt("id"),rs.getString("scenic_name"),rs.getString("description"),rs.getString("location"),rs.getInt("comments_number"),rs.getInt("price"),rs.getString("open_time"),rs.getInt("tickets_number"),rs.getString("scenic_img"));
            }
            return scenic;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            DB.close(conn,stmt,rs);

        }
        return scenic;

    }
}
