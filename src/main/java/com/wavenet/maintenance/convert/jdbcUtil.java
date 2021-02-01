/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: test.java
 * @Package com.wavenet.maintenance.convert
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-05-22 10:00
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.convert;

import com.wavenet.maintenance.dao.entity.Trajectory;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * @ClassName: test
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-05-22 10:00     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
    public class jdbcUtil {

    public static int jdbcIns(Trajectory trajectory) throws ClassNotFoundException, SQLException {
         final String DB_URL = "jdbc:oracle:thin:@106.75.229.99:1522:xe";
        // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称
         final String USER = "YP_GDYHJGPT";
         final String PASS = "GDYHJGPT_YP2020";

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        // 注册驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        // 创建链接
        String shape = trajectory.getShape();

        //TRAJECTORY表中生成轨迹
        String sql3= "commit; " +
                "END; ";
        conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
        String sql = "declare " +
                "v_line clob; " +
                "begin " +
                "v_line:='LINESTRING (" +shape + ")'; " +
                "insert into TRAJECTORY " +
                "(objectid,gps_code,patrol_code,person_code,uplaod_date,x,y,type,road_section_code,speed,mileage,SHAPE) " +
                "values (r76.nextval,?,?,?,sysdate,?,?,?,?,?,?,sde.st_linefromtext(v_line,4326)); " +sql3;
        stat = conn.prepareStatement(sql);
        stat.setString(1,trajectory.getGpsCode());
        stat.setString(2,trajectory.getPatrolCode());
        stat.setString(3,trajectory.getPersonCode());
        stat.setDouble(4,trajectory.getX());
        stat.setDouble(5,trajectory.getY());
        stat.setString(6,trajectory.getType());
        if(trajectory.getRoadSectionCode()==null ||("").equals(trajectory.getRoadSectionCode())){
            stat.setString(7,"");
        }else {
            stat.setString(7,trajectory.getRoadSectionCode());
        }

        stat.setDouble(8,trajectory.getSpeed());
        stat.setString(9,trajectory.getMileage());
//        stat.setString(1,trajectory.getShape());
        int row = stat.executeUpdate();
        // 关闭
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (stat != null) {
                        stat.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            return row;
        }

}
