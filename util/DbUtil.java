package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 数据库连接工具类 JDBC
 */
public class DbUtil {
    //数据库连接地址 127.0.0.代表连接地址  3306 端口号   db代表数据库名称
    static String url = "jdbc:mysql://127.0.0.1:3306/shopping?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";  //mysql8链接地址
    static String user = "root";
    static String pwd = "*HeMin5201314*";
   static String driver = "com.mysql.cj.jdbc.Driver"; //mysql8.0以上版本

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    // 连接数据库方法
    public static Connection getCon() {
        try {
            return DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    //关闭数据库连接
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (null != resultSet) resultSet.close();
            if (null != statement) statement.close();
            if (null != connection) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 调用上一个关闭方法
    public static void close(Connection connection, Statement statement) {
        close(connection, statement, null);
    }


}



