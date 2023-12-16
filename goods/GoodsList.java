package goods;

import util.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GoodsList {

    // 商品列表启动类
    public static void main(String[] args) {

        Connection conn = null;

        PreparedStatement pstmt = null;

        ResultSet rs = null;

        String sql = "select code,title,description,price from goods";

        try {
            // 连接到数据库并创建Statement对象
            conn = DbUtil.getCon();
            pstmt = conn.prepareStatement(sql);
            // 执行查询语句
            rs = pstmt.executeQuery();
            System.out.println("===============商品列表=================");
            System.out.println("商品编码 商品名称 商品描述 商品价格");
            // 获取查询的值
            while (rs.next()) {
            //  打印数据
                System.out.println(rs.getString("code")
                        +" "+rs.getString("title")
                        +" "+rs.getString("description")
                        +" "+rs.getString("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 关闭数据库
            DbUtil.close(conn,pstmt);
        }
    }

}
