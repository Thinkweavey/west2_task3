package order;

import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class OrderAdd {

    // 订单新增启动类
    public static void main(String[] args) {

        Connection conn = null;

        PreparedStatement pstmt = null;

        ResultSet rs = null;

        String sql = "insert into `order`(id,code,pcode,price,ctime) values (? ,? ,? ,? ,? )";

        String sql1 = "select title from goods where code = ?";

        // 新增的数据
        String code = String.valueOf((new Date()).getTime()); // 当前时间戳做编码
        String pcode = "10003"; // 商品编码
        String price = "1";

        try {
            // 连接到数据库并创建Statement对象
            conn = DbUtil.getCon();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setObject(1,pcode);
            // 新增之前先判断是否存在CODE
            rs = pstmt.executeQuery();
            String title1 = "";
            while (rs.next()) {
                title1 = rs.getString("title");
            }
            if("".equals(title1)){
                System.out.println("商品编码不存在，下单失败！");
            }else{
                pstmt = conn.prepareStatement(sql);
                // 配置新增数据
                pstmt.setObject(1,null);
                pstmt.setObject(2,code);
                pstmt.setObject(3,pcode);
                pstmt.setObject(4, price);
                pstmt.setObject(5, new Date());

                // 执行SQL 操作
                int i =  pstmt.executeUpdate();
                System.out.println(i>0?"下单成功":"下单失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 关闭数据库
            DbUtil.close(conn,pstmt);
        }
    }

}
