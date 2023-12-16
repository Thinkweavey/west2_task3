package order;

import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderDel {

    // 订单删除启动类
    public static void main(String[] args) {

        Connection conn = null;

        PreparedStatement pstmt = null;

        ResultSet rs = null;

        String sql = "delete from `order` where code = ?";

        // 订单编号
        String code = "1702522465788";

        try {
            // 连接到数据库并创建Statement对象
            conn = DbUtil.getCon();
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,code);
            int i =  pstmt.executeUpdate();
            System.out.println(i>0?"删除成功":"删除失败,请检查订单编号是否正确");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 关闭数据库
            DbUtil.close(conn,pstmt);
        }
    }

}
