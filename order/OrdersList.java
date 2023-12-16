package order;

import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrdersList {

    // 订单列表启动类
    public static void main(String[] args) {

        Connection conn = null;

        PreparedStatement pstmt = null;

        ResultSet rs = null;

        // 根据商品编码左关联查询商品的名称
        String sql = "select o.code,o.pcode,g.title,o.price,o.ctime from `order` o LEFT JOIN goods g ON o.pcode = g.code";

        try {
            // 连接到数据库并创建Statement对象
            conn = DbUtil.getCon();
            pstmt = conn.prepareStatement(sql);
            // 执行查询语句
            rs = pstmt.executeQuery();
            System.out.println("===============订单列表=================");
            System.out.println("订单编号 商品编码 商品名称 订单价格 下单时间");
            // 获取查询的值
            while (rs.next()) {
            //  打印数据
                System.out.println(rs.getString("code")
                        +" "+rs.getString("pcode")
                        +" "+rs.getString("title")
                        +" "+rs.getString("price")
                        +" "+rs.getString("ctime"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 关闭数据库
            DbUtil.close(conn,pstmt);
        }
    }

}
