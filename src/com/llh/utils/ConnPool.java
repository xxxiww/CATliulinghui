package com.llh.utils;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Logger;

public class ConnPool implements DataSource {

    //使用LinkedList集合存放数据库连接
    //因为LinkedList底层是双向链表，便于删除和添加连接对象
    private static LinkedList<Connection> connPool = new LinkedList<Connection>();

    //在静态代码块中加载配置文件
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
            //创建10个连接对象
            for (int i = 0; i < 10; i++) {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/llh", "root","880818");
                //将创造的连接放进池子
                connPool.add(conn);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    //获得连接
    @Override
    public  Connection getConnection() throws SQLException {
        Connection conn = null;
        //如果数据库连接池里有连接对象
        if(connPool.size() > 0){
            //从集合中获取一个连接
            //removeFirst()这个方法返回此列表的第一个元素
             conn = connPool.removeFirst();
        }
        //如果池子没连接对象，就创一个新的连接对象
        else{
            conn = getFreeConn();
        }
        return conn;
    }

    //当池子里头没连接的时候调用这个方法获取一个新的连接
    public  Connection getFreeConn(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/llh", "root","880818");//连接数据库
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return conn;

    }

    //放回连接
    public static void close(Connection conn,Statement stmt, ResultSet rs){
        if(rs != null)
            try{
                rs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        if(stmt != null)
            try{
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        connPool.add(conn);
    }



    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
