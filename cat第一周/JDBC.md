# 注册驱动的另一种方式--常用

用反射来驱动，注意：要catch异常



![image-20210326083754826](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210326083754826.png)

# 从属性资源文件中读取连接数据库信息

**1.Properties类是什么？**

Properties（Java.util.Properties），该类主要用于读取Java的配置文件，不同的编程语言有自己所支持的配置文件，配置文件中很多变量是经常改变的，为了方便用户的配置，能让用户够脱离程序本身去修改相关的变量设置。就像在Java中，其配置文件常为.properties文件，是以键值对的形式进行参数配置的。

getBundle(这是一个.properties的文件//jdbc.properties)

![image-20210326084914847](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210326084914847.png)

# 处理查询结果集--select

1.结果集

![image-20210326085401470](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210326085401470.png)

查询的结果，自动封装到ResultSet对象里。

![image-20210326085454610](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210326085454610.png)

故在释放资源的时候，释放的顺序为：

1.rs

2.stmt

3.conn

执行查询的方法是***executeQuery***

执行增删改的方法是***executeUpdate***

executeQuery返回的是单个ResultSet对象（接口），返回的类型是接口，但是接口是不能实例化的，但是接口可以声明一个引用，指向其实现类，也就是说，在实际中返回值都是这个接口的实现类的对象。所谓面向接口编程是指我们在编写代码时对数据参数的定义尽量写成接口，待真正实现的时候再用实际类型代替一大特点。思想：**可以返回接口，接口虽然不能被实例化，但是接口的实现类都可以向上转型为接口**

**好处**：代码的耦合性降低，在运行时我只需修改实现类类型，就可以实现不同的功能，而不必要修改接口的代码。表面上是返回的接口，其实返回的是接口的实现类。

![image-20210326085840519](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210326085840519.png)

一、接口作为方法的参数进行传递：必须传递进去一个接口的实现类对象。（跟接口一样）

例：

```java
//抽烟接口
public interface Smoking{
       void smoking();
}
//学生类
public class Student implements Smoking {
    public void smoking() {
        System.out.println("Students are not allowed to smoke!");
    }
}
//测试类
public class Test{
    public static void main(String[] args) {
        Student s = new Student(); //改成多态调用：Smoking s = new Student();        
        smoking(s); //打印结果：Students are not allowed to smoke!
    }
 
    public static void smoking(Smoking s) { //接口作为参数,事实上是传入了一个接口的实现类。
        s.smoking();
    }
}

```

二、接口作为方法的返回值进行传递：必须返回一个接口的实现类的对象。

例：

```java
//抽烟接口
public interface Smoking{
       void smoking();
}
 

//学生类
public class Student implements Smoking {
    public void smoking() {
        System.out.println("Students are not allowed to smoke!");
    }
}
//测试类
public class Test {
    public static void main(String[] args) {
        Smoking s = smoking(); //相当于Smoking s  = new Student();
        s.smoking(); //打印结果：Students are not allowed to smoke!
    }
 
    public static Smoking smoking() {
        return new Student();//返回接口实现类的对象。
    }
}

```

在ResultSet接口下有一个next（）方法

![image-20210326091715114](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210326091715114.png)

## 遍历结果集--next()方法

如果下一行有数据，就返回true，否则返回false。

最开始的时候光标不指向任何数据，指向最上面。

![image-20210326092039535](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210326092039535.png)

![image-20210326092751203](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210326092751203.png)

getString()方法是ResultSet接口里的一个方法，他的返回值是String类型。

参数可以为**表的字段名**和**表的列数**，需要注意的是JDBC中的列是从1开始的。

若是你在sql语句select empno as a。。

在getString传参数的时候要传入重新命名的名字，即a。

因为列的名称不是表中的列名称，而是查询结果集的列名称。

![image-20210326093358381](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210326093358381.png)

若是你想以特定的类型取出数据。如getInt（），getDouble（）。

![image-20210326093456342](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210326093456342.png)

# 用idea开发JDBC代码配置驱动

![image-20210326102826467](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210326102826467.png)

![image-20210326102929440](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210326102929440.png)

***点java***

![image-20210326102954022](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210326102954022.png)

![image-20210326103027376](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210326103027376.png)

选择jar包导入哪个模块。

![image-20210326103055923](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210326103055923.png)

点击apply，点击ok

![image-20210326103143131](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210326103143131.png)

![image-20210326103830565](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210326103830565.png)

# 回顾JDBC

```java
//Main.java
import java.sql.*;

public class Main {
    /***
     本文使用的数据库名称为user,账号密码均为root,
     数据库含表userinfo,
     该表有三个字段，id(INT),userinfo(VARCHAR),password(VARCHAR)
     读者可根据数据库的实际情况对语句进行修改
     ***/
    public static void main(String[] args) {
        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //1,加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.创建连接
            //此处按照实际的数据库名称和账号密码进行修改
            //格式为jdbc:mysql://127.0.0.1:3306/数据库名称?useSSL=true&characterEncoding=utf-8&user=账号名&password=密码
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user?useSSL=true&characterEncoding=utf-8&user=root&password=root");
            System.out.println("创建连接成功");
            //3.写sql
            //根据数据库实际的表名写SQL语句
            String sql="select * from userinfo";
            //4.得到statement对象执行sql
            statement = connection.prepareStatement(sql);
            //5.得到结果集
            rs = statement.executeQuery();
            //6.处理结果集
            while(rs.next()){
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //7.关闭
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement!=null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("关闭成功");
        }
    }
}
 
```

# 模拟登录功能

![image-20210330235223846](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210330235223846.png)

pd的安装

![image-20210330235404316](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210330235404316.png)



![image-20210330235413923](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210330235413923.png)

![image-20210330235506306](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210330235506306.png)

![image-20210331163142178](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210331163142178.png)

用户表：id；用户名；密码；真实姓名

界面初始化需要返回值：用户名和密码

要用容器来接收，Map合适

![image-20210331165629153](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210331165629153.png)

```
//初始化界面

```

![image-20210331165726491](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210331165726491.png)

## 登陆方法的实现

![image-20210331171419304](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210331171419304.png)

若是匹配，就查出来一条记录。

# sql注入现象

![image-20210331171649853](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210331171649853.png)

![image-20210331172232179](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210331172232179.png)

![image-20210331172604244](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210331172604244.png)

## 如何解决sql注入现象

PreparedStatement接口//预编译的数据库操作对象

解决办法：只要用户提供的信息不参与sql语句的编译过程，问题就解决了。即使含有sql语句的关键字，也不参与编译。

PreparedStatement原理：先对sql语句的框架进行编译，然后再给sql语句传值。

![image-20210331191652383](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210331191652383.png)

![image-20210331203140334](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210331203140334.png)

先执行sql语句，再给占位符传值。

使用setXXX给占位符传值，第一个占位符就写1，第二个占位符就写2.

# Statement和PreparedStatement对比

Statement：存在sql注入，编译一次，执行一次。

PreparedStatement：解决了sql注入，效率略高，编译一次，执行n次。会在编译阶段做类型的安全检查。

![image-20210401204618047](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210401204618047.png)

## Statement的用途

升序，降序

![image-20210401205318335](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210401205318335.png)

# PreparedStatement实现增删改

![image-20210401210231172](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210401210231172.png)

![image-20210401210350039](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210401210350039.png) 

![image-20210401210831478](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210401210831478.png)

# 单机事务--一个数据库

jdbc只要执行任意一条DML语句，就会提交一次。

```mysql
creat table(
    balance double(7,2),//7位有效数字，2位小数
    。。。
)
```

```java
重点为三行代码：
//获取连接
conn = DriverManager.getConnection(url,user,password);
//开始事务
conn.setAutoCommit(false);
.....
conn.commit();
//若事务出现异常，在catch里面rollback
//需要try catch
conn.rollback();
```



![image-20210401221144048](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210401221144048.png)

拿到connection之后将自动提交模式修改为手动提交模式

![image-20210401221411573](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210401221411573.png)

![image-20210401221420855](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210401221420855.png)

# JDBC工具类的封装

建一个包名字叫**utils**，意为工具包。

![image-20210401225424409](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210401225424409.png)

![image-20210401230352059](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210401230352059.png)

# 模糊查询

![image-20210401231256782](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210401231256782.png)

# 乐观锁和悲观锁

## 悲观锁

![image-20210402002320258](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210402002320258.png)

在这个select语句后面加一个**for update**就是行级锁，当前事务还没结束时，其他事务不能对这几条对这些记录进行修改，不允许并发。

## 乐观锁

允许并发

![image-20210402003141641](C:\Users\laura\AppData\Roaming\Typora\typora-user-images\image-20210402003141641.png)

http://c.biancheng.net/view/1206.html