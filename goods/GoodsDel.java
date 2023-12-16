package goods;

import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class GoodsDel {

    // 商品删除启动类
    public static void main(String[] args) {

        Connection conn = null;

        PreparedStatement pstmt = null;

        ResultSet rs = null;

        String sql = "delete from `goods` where code = ?";

        String sql1 = "select code from `order` where pcode = ?";

        // 新增的数据
        String code = "10004";

        try {
            // 连接到数据库并创建Statement对象
            conn = DbUtil.getCon();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setObject(1,code);
            // 新增之前先判断是否存在相同的CODE
            rs = pstmt.executeQuery();
            String code1 = "";
            while (rs.next()) {
                code1 = rs.getString("code");
            }
            if(!"".equals(code1)){
                System.out.println("已存在改商品的订单，不能删除！");
            }else{
                pstmt = conn.prepareStatement(sql);
                pstmt.setObject(1,code);
                int i =  pstmt.executeUpdate();
                System.out.println(i>0?"删除成功":"删除失败，请检查商品编号是否正确");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 关闭数据库
            DbUtil.close(conn,pstmt);
        }
    }

}
