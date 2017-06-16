package dao.impl;

import dao.UserDao;
import dbUtil.AppException;
import dbUtil.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jh on 2017/4/12.
 */
public class UserDaoImpl implements UserDao{

    public int login(String name, String password) throws AppException {

        boolean bedoctor;
        int id = -1;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 定义及预处理sql语句
            String str = "SELECT bedoctor FROM doctor WHERE username = ? AND password = ?";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setString(1, name);
            st.setString(2, password);

            // ִ执行sql语句
            rs = st.executeQuery();

            // 判断处理结果
            if(rs.next()){
                bedoctor = rs.getBoolean("bedoctor");
                id = 1;
                //id = rs.getConcurrency();
                System.out.println("dao");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new AppException("/dao/impl/UserDaoImpl/login");
        }finally
        {
            // 关闭连接
            DBUtil.closeResultSet(rs);     // 关闭数据集
            DBUtil.closeStatement(st);     //     sql语句
            DBUtil.closeConection(conn);   //     连接
        }
        return id;
    }

}
