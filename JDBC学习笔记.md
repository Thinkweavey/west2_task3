# JDBC概念
JDBC( Java DataBaseConnectivity java数据库连接)是一组执行sql语句的JavaAPI,它是由一组用Java语言编写的类和接口组成的，可以帮助开发人员快速实现关系型数据库的连接。
其实就是java官方提供的一套规范(接口)，并不神秘。
# 数据库驱动包：
编写jdbc代码需要导入这个驱动包！！

MySQL :: Download Connector/J

mysql-connector-java-5.1.47.jar

# JDBC快速入门
1.注册数据库驱动

2.创建数据库连接对象

3.创建执行sql语句的对象

4.将执行结果返回到结果集

5.打印输出

6.释放资源

***模板代码（基础）***
记得导入上面的jar包到lib目录下，并且要把jar包Add as library

```package MyExercise.out.production.MyExercise.MyExercise;
import java.sql.*;
public class JDBCTest01 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.创建数据库连接对象
        String url="jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEcoding=utf8&useSSL=true";
        String username="root";
        String password="123456";
        Connection conn= DriverManager.getConnection(url,username,password);
        // 3.创建执行sql语句的对象
        Statement st=conn.createStatement();
        //4.返回结果集
        String sql="select * from users";
        ResultSet rs=st.executeQuery(sql);
        //5.处理结果,打印table的数据
        while (rs.next()){
            System.out.println("id="+rs.getObject("id"));
            System.out.println("name="+rs.getObject("name"));
            System.out.println("email="+rs.getObject("email"));
        }
        //6.关闭资源
        rs.close();
        conn.close();
        st.close();
    }
 
}
 ```

# JDBC功能详解

## DriverManager驱动管理对象
(1)注册驱动:
注册给定的驱动程序: staticvoid registerDriver(Driver driver);在com.mysql.jdbc.Driver类中存在静态代码块；写代码有固定写法:Class.forName(“com.mysql.jdbc.Driver”);

(2)获取数据库连接对象

具体实现是通过DriverManager.getConnection（url,username,password）；

## Connection数据库连接对象
(1)创建sql执行对象

conn.createStatement();

(2)可以执行事务的提交，回滚操作

conn.rollback();conn.setAutoCommit(false);

## Statement执行sql语句的对象
用于向数据库发送要执行的sql语句（增删改查），其中有两个重要方法：executeUpdate(String sql)和executeQuery(String sql)，前者用于DML操作，后者用于DQL操作

## ResultSet结果集对象
(1)打印输出时判断结果集是否为空，rs.next()

(2)若知字段类型可使用指定类型如,rs.getInt()获取数据
---
# 提取工具类并完成增删改查操作
在上面介绍了可以通过jdbc对数据库进行增删改查操作，但是如果每次对数据库操作一次都要重新加载一次驱动，建立连接等重复性操作的话，会造成代码的冗余，因此下面通过封装一个工具类来实现对数据库的增删改查操作。

## (1)建立配置文件db.properties文件 
properties文件 是java支持的一种配置文件类型(所谓支持是因为Java提供了properties类,来读取properties文件中的信息)。记得一定要将此文件直接放在src目录下！！！不然后面执行可能找不到此配置文件！！
```driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSL=true
username=root
password=lcl403020
```
## (2)建立工具类JdbcUtils.java 
有了这个工具类，之后的增删改查操作可直接导入这个工具类完成获取连接，释放资源的操作，很方便，接着往下看。
```package jdbcFirstDemo.src.lesson02.utils;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
public class JdbcUtils {
    private static String driver=null;
    private static String url=null;
    private static String username=null;
    private static String password=null;
    static  {
        try{
            InputStream in=JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties=new Properties();
            properties.load(in);
 
            driver=properties.getProperty("driver");
            url=properties.getProperty("url");
            username=properties.getProperty("username");
            password=properties.getProperty("password");
            //驱动只需要加载一次
            Class.forName(driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }
    //释放连接资源
    public static void release(Connection conn, Statement st, ResultSet rs)  {
        if(rs!=null){
            try{
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
 
        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
 
    }
}
```
## (3)插入数据（DML）
```package jdbcFirstDemo.src.lesson02;
import jdbcFirstDemo.src.lesson02.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class TestInsert {
    public static void main(String[] args) {
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try{
            conn= JdbcUtils.getConnection();
            st=conn.createStatement();
            String sql="insert into users  (id, name, password, email, birthday) VALUES (7,'cll',406020,'30812290','2002-03-03 10:00:00')";
            int i=st.executeUpdate(sql);
            if(i>0){
                System.out.println("插入成功！");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
```
## (4)修改数据（DML）
```package jdbcFirstDemo.src.lesson02;
import jdbcFirstDemo.src.lesson02.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class TestUpdate {
    public static void main(String[] args) {
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try{
            conn= JdbcUtils.getConnection();
            st=conn.createStatement();
            String sql="update users set name='haha' where id=2";
            int i=st.executeUpdate(sql);
            if(i>0){
                System.out.println("修改成功！");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
```

## (5)删除数据（DML）
```package jdbcFirstDemo.src.lesson02;
import jdbcFirstDemo.src.lesson02.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class TestDelete {
    public static void main(String[] args) {
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try{
            conn= JdbcUtils.getConnection();
            st=conn.createStatement();
            String sql="delete from users where id=1";
            int i=st.executeUpdate(sql);
            if(i>0){
                System.out.println("删除成功！");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
```

## (6)查询数据(DQL)
```package jdbcFirstDemo.src.lesson02;
import jdbcFirstDemo.src.lesson02.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class TestQuery {
    public static void main(String[] args) throws SQLException {
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        conn= JdbcUtils.getConnection();
        st=conn.createStatement();
        //sql
        String sql="select * from users";
        rs=st.executeQuery(sql);
        while (rs.next()){
            System.out.println(rs.getString("name"));
        }
    }
}
```
---
# SQL注入问题
几乎所有关系型数据库都会存在sql注入问题（可以自行百度），sql注入问题是很重要的安全性问题，简单而言就是可以通过拼接sql语句。使得sql语句逻辑结果为true，从而欺骗服务器获得想要的信息。
# 解决SQL注入问题PreparedStatement
PreparedStatement对象比statement对象能防止sql注入问题更安全且效率更高，我们先不急着展示如何解决sql注入，我们先简单来看PreparedStatement对象和Statement对象在操作数据库上的区别
(1)插入数据（DML）
与前面statement对象插入数据不同的是：1.这里的sql用了？占位符代替；2.创建方式不同，这里是conn.prepareStatement（sql）3.插入方式不同，这里是对每个参数直接设置，如st.setInt()

```package jdbcFirstDemo.src.lesson03;
import jdbcFirstDemo.src.lesson02.utils.JdbcUtils;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
public class TestInsert {
    public static void main(String[] args) throws SQLException {
        Connection conn=null;
        PreparedStatement st=null;
        conn= JdbcUtils.getConnection();
        //PreparedStatement 和Statement 区别
        String sql="insert into users(id, name, password, email, birthday) VALUES (?,?,?,?,?)";
        st=conn.prepareStatement(sql);
        //手动给参数赋值
        st.setInt(1,6);
        st.setString(2,"qin");
        st.setString(3,"222");
        st.setString(4,"393003@");
 
        //注意点：sql.data  数据库
        //    这里要用这个！！！util.data   java
        st.setDate(5,new java.sql.Date(new Date().getTime()));
        //执行
        int i=st.executeUpdate();
        if(i>0) {
            System.out.println("插入成功");
 
        }
        JdbcUtils.release(conn,st,null);
 
    }
}
```
(2)删除数据（DML）
```package jdbcFirstDemo.src.lesson03;
import jdbcFirstDemo.src.lesson02.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
public class TestDelete {
    public static void main(String[] args) throws SQLException {
        //建立连接-预编译-
        Connection conn=null;
        PreparedStatement st=null;
        conn= JdbcUtils.getConnection();
        //PreparedStatement 和Statement 区别
        String sql="delete from users where id=2;";
        st=conn.prepareStatement(sql);
 
        //执行
        int i=st.executeUpdate();
        if(i>0) {
            System.out.println("删除成功！！");
 
        }
        JdbcUtils.release(conn,st,null);
 
    }
}
```
(3)修改数据（DML）
```package jdbcFirstDemo.src.lesson02;
import jdbcFirstDemo.src.lesson02.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class TestUpdate {
    public static void main(String[] args) {
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try{
            conn= JdbcUtils.getConnection();
            st=conn.createStatement();
            String sql="update users set name='haha' where id=4";
            int i=st.executeUpdate(sql);
            if(i>0){
                System.out.println("修改成功！");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
```
(4)查询数据（DML）
```package jdbcFirstDemo.src.lesson03;
 
import jdbcFirstDemo.src.lesson02.utils.JdbcUtils;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class TestQuery {
    public static void main(String[] args) throws SQLException {
        Connection conn=null;
        PreparedStatement st=null;
        ResultSet rs=null;
        conn= JdbcUtils.getConnection();
        String sql="select *from users where id=?";
        st=conn.prepareStatement(sql);
        st.setInt(1,7);//(第几个参数，参数值为多少)
        rs=st.executeQuery();
        if(rs.next()){
            System.out.println(rs.getString("name"));
        }
        JdbcUtils.release(conn,st,rs);
    }
 
}
```
---
# JDBC操作事务
操作的表
![img](https://img-blog.csdnimg.cn/0e0d36390582410083afd75612aa55e1.png)
 代码：
 ```package jdbcFirstDemo.src.lesson04;
import jdbcFirstDemo.src.lesson02.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class JDBC_transaction {
    public static void main(String[] args) {
        Connection conn=null;
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);//开启事务同时关闭自动提交按钮
            String sql1 = "update account set money=money-1000 where name='张三'";
            st = conn.prepareStatement(sql1);
            st.executeUpdate();
         //   int x=1/0;
            String sql2 = "update account set money=money+1000 where name='李四'";
            st = conn.prepareStatement(sql2);
            st.executeUpdate();
            //事务结束，提交
            conn.commit();
            System.out.println("事务执行成功！！");
        }catch (SQLException  e){
            try{conn.rollback();}
            catch (SQLException e1){e1.printStackTrace();}
        }
 
    }
}
```
*** 细节说明：***
如果中间有异常发生，则事务发生回滚
![img](https://img-blog.csdnimg.cn/9b217e2b1b774d7f896272c6734053d4.png)
---
# 连接池DBCP和C3P0
## jar包的配置
编写连接池本质只需要实现一个接口 DataSource，无论什么数据源，DataSource接口不会变，方法就不会变。

说明一下：.zip是windows系统下载的，tar.gz是linux系统下载的，.bin是二进制class文件，.source是源码文件，一般下载.bin就可以了，除非你是要研究源码，我这4个jar包都是下载的.bin文件

DBCP用到的jar包：

(1)commons-dbcp-1.4

下载链接：Index of /dist/commons/dbcp/source

(2)commons-pool-1.6

下载链接：Index of /dist/commons/pool

C3P0用到的jar包：

(1)C3P0-0.9.5.5.jar

下载连接：Download c3p0-0.9.5.5.bin.zip (c3p0:JDBC DataSources/Resource Pools)

(2)mchange-commons-pool-java-0.2.19

下载连接：https://mvnrepository.com/artifact/com.mchange/mchange-commons-java/0.2.19
### (1)连接池DBCP
在最开始的时候，我们有编写一个工具类（JdbcUtils.java）和一个配置文件(db.properties)来完成增删改查操作

通过连接池来完成增删改查操作也是一样的道理，我们要编写一个工具类（DBCPUtils.java）和一个配置文件(dbcpconfig.properties)来完成增删改查操作

首先工具类DBCPUtils.java

需要关注的点：1.通过工厂模式创建数据源datasource；2.通过数据源里自带的连接来获取连接
```package jdbcFirstDemo.src.lesson05.utils;
 
import org.apache.commons.dbcp.BasicDataSourceFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
public class DBCPUtils {
    private static DataSource datasource = null;
    static {
        try {
            InputStream in = DBCPUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
            Properties properties = new Properties();
            properties.load(in);
            //创建数据源，  工厂模式
            datasource = BasicDataSourceFactory.createDataSource(properties);
 
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //获取连接
    public static Connection getConnection() throws SQLException {
        //数据源里自带连接
        return datasource.getConnection();
    }
 
    //释放连接资源
    public static void release(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
 
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
 
    }
}
```
其次配置文件(dbcpconfig.properties)

这个配置文件可以去apache官网下载也可以自己写，代码不多
```driverClassName=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSL=true
username=root
password=lcl403020
initialSize=10
maxActive=50
 
maxIdle=20
minIdle=5
#最长等待时间为60s
maxWait=60000
connectionProperties=useUnicode=true;characterEncoding=UTF8
defaultAutoCommit=true
defaultReadOnly=
defaultTransactionIsolation=READ_UNCOMMITTED
```
最后测试TestDBCP_Insert.java
```package jdbcFirstDemo.src.lesson05;
 
import jdbcFirstDemo.src.lesson05.utils.DBCPUtils;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
public class TestDBCP_Insert {
    public static void main(String[] args) throws SQLException {
        Connection conn=null;
        PreparedStatement st=null;
        conn= DBCPUtils.getConnection();
        //PreparedStatement 和Statement 区别
        String sql="insert into users(id, name, password, email, birthday) VALUES (?,?,?,?,?)";
        st=conn.prepareStatement(sql);
        //手动给参数赋值
        st.setInt(1,8);
        st.setString(2,"wwn");
        st.setString(3,"6421");
        st.setString(4,"3ffa23@");
        //注意点：sql.data  数据库
        //    这里要用这个！！！util.data   java
        st.setDate(5,new java.sql.Date(new Date().getTime()));
        //执行
        int i=st.executeUpdate();
        if(i>0) {
            System.out.println("插入成功");
        }
        DBCPUtils.release(conn,st,null);
 
    }
 
}
```
### (2)连接池C3P0
同理DBCP连接池，C3P0也需要工具类（C3P0Utils.java），配置文件（C3P0-config.xml）

首先配置文件C3P0-config.xml

注意：在DBCP连接池中用的匹配文件是dbcpconfig.properties，而在C3P0中的配置文件是C3P0-config.xml，下面的配置文件给出了默认配置和c3p0命名配置，正常来说工具类会根据有没有传递进来参数“c3p0”来匹配哪个配置的，但是我如果按下面的xml文件去跑我的TestC3P0_insert.java是会报错的
```<?xml version="1.0" encoding="UTF-8"?>
 
<c3p0-config>
    <!--
    c3p0的缺省（默认）配置
    如果在代码中"ComboPooledDataSource ds=new ComboPooledDataSource();"这样写就表示使用的是c3p0的缺省（默认）
    -->
    <default-config>
    <named-config name="c3p0">
        <property name="driverClass">com.mysql.jdbc.Driver</property>
        <property name="jdbcUrl">jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=true</property>
        <property name="user">root</property>
        <property name="password">lcl403020</property>
 
        <property name="acquiredIncrement">5</property>
        <property name="initialPoolSize">10</property>
        <property name="minPoolSize">5</property>
        <property name="maxPoolSize">20</property>
    </named-config>
    </default-config>
    <!--
          c3p0的命名配置
        如果在代码中"ComboPooledDataSource ds=new ComboPooledDataSource("MySQL");"这样写就表示使用的是mysql的缺省（默认）-->
    <named-config name="MySQL">
        <property name="driverClass">com.mysql.cj.jdbc.Driver</property>
        <property name="jdbcUrl">jdbc:mysql://localhost:3306/jdbcstudy?userUnicode=true&amp;characterEncoding=utf8&amp;uesSSL=true&amp;serverTimezone=UTC</property>
        <property name="user">root</property>
        <property name="password">123456</property>
 
        <property name="acquiredIncrement">5</property>
        <property name="initialPoolSize">10</property>
        <property name="minPoolSize">5</property>
        <property name="maxPoolSize">20</property>
    </named-config>
</c3p0-config>
```















































































































































