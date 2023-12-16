package goods;

import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class GoodsUpdate {

    // 商品新增启动类
    public static void main(String[] args) {

        Connection conn = null;

        PreparedStatement pstmt = null;

        ResultSet rs = null;

        String sql = "update `goods` set title = ?, description= ?, price = ? where code = ?";

        String sql1 = "select code from `goods` where code = ?";

        // 新增的数据
        String code = "1702529553060";
        String title = "是搞也加";
        String description = "1702529553060";
        String price = "100";

        try {
            // 连接到数据库并创建Statement对象
            conn = DbUtil.getCon();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setObject(1,title);
            // 修改之前先判断是否存在相同的CODE
            rs = pstmt.executeQuery();
            String code1 = "";
            while (rs.next()) {
                code1 = rs.getString("code");
            }
            // 当查询出来的数据不为空，且与当前的商品编码不一致时，表示商品名称已存在
            if(!"".equals(code1)&&!code1.equals(code)){
                System.out.println("商品名称已存在！");
            }else{
                pstmt = conn.prepareStatement(sql);
                // 配置新增数据
                pstmt.setObject(1,title);
                pstmt.setObject(2, description);
                pstmt.setObject(3, price);
                pstmt.setObject(4,code);

                // 执行SQL 操作
                int i =  pstmt.executeUpdate();
                System.out.println(i>0?"修改成功":"修改失败，请检查商品编号是否正确");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 关闭数据库
            DbUtil.close(conn,pstmt);
        }
    }

}
