package com.llh.utils;

    import java.sql.*;

    public class DB {
        static{
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");//加载数据库驱动
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        private DB() {}

        //获得Connection
        public static Connection getConn(){
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/llh", "root","880818");//连接数据库
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            return conn;
        }

        //获得Statement
        public static Statement getStmt(Connection conn){
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return stmt;
        }

        //获取PreparedStmt，且把sql搞进去
        public static PreparedStatement getPStmt(Connection conn,String sql){
            PreparedStatement pstmt = null;
            try {
                pstmt = conn.prepareStatement(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return pstmt;
        }
        //增删改
        public static int executeUpdate(Connection conn,String sql) throws SQLException {
            Statement stmt = conn.createStatement();
            int result = stmt.executeUpdate(sql);
            //关掉资源
            closeSt(stmt);
            return  result;

        }
        public  static  int executeUpdate(Statement stmt,String sql)throws SQLException{
            return stmt.executeUpdate(sql);

        }
        public  static int executeUpdate(PreparedStatement ps,String sql)throws SQLException{
            return  ps.executeUpdate(sql);
        }

        //查
        //Statement ：查询
        //返回一个结果集
        public static ResultSet executeQuery(Statement stmt,String sql){
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return rs;
        }

        //PreparedStatement：查询
        //返回一个结果集
        public static ResultSet executeQuery(PreparedStatement ps,String sql){
            ResultSet rs = null;
            try {
                rs = ps.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return rs;
        }

        //查询数据库里的数据，返回结果集
        public static ResultSet executeQuery(Connection conn,String sql){//重载
            ResultSet rs = null;
            try {
                rs = conn.createStatement().executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return rs;
        }


        // 释放资源
        public static void close(Connection conn, Statement stmt, ResultSet rs){
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
            if(conn != null)
                try{
                    conn.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
        }


        //关闭PreparedStatement
        public static  void closePs(PreparedStatement ps){
            if(ps != null)
                try{
                    ps.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }

        }
        public  static  void closeSt(Statement stmt){
            if(stmt != null)
                try{
                    stmt.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
        }
        public  static  void closeConn(Connection conn){
            if(conn != null)
                try{
                    conn.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }

        }
        public  static  void closeRs(ResultSet rs){
            if(rs != null)
                try{
                    rs.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }

        }
    }


