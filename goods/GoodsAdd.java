package goods;

import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class GoodsAdd {

    // 商品新增启动类
    public static void main(String[] args) {

        Connection conn = null;

        PreparedStatement pstmt = null;

        ResultSet rs = null;

        String sql = "insert into `goods`(id,code,title,description,price) values (? ,? ,? ,? ,? )";

        String sql1 = "select code from goods where title = ?";

        // 新增的数据
        String code = String.valueOf((new Date()).getTime()); // 当前时间戳做编码
        String title = "高爷家猫砂";
        String description = "高爷家猫砂来了呀";
        String price = "85";

        try {
            // 连接到数据库并创建Statement对象
            conn = DbUtil.getCon();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setObject(1,title);
            // 新增之前先判断是否存在相同的CODE
            rs = pstmt.executeQuery();
            String code1 = "";
            while (rs.next()) {
                code1 = rs.getString("code");
            }
            if(!"".equals(code1)){
                System.out.println("商品名称已存在！");
            }else{
                pstmt = conn.prepareStatement(sql);
                // 配置新增数据
                pstmt.setObject(1,null);
                pstmt.setObject(2,code);
                pstmt.setObject(3,title);
                pstmt.setObject(4, description);
                pstmt.setObject(5, price);

                // 执行SQL 操作
                int i =  pstmt.executeUpdate();
                System.out.println(i>0?"新增成功":"新增失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 关闭数据库
            DbUtil.close(conn,pstmt);
        }
    }

}
