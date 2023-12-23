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
password=lcl403020```






































































































































