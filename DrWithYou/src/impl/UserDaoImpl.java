package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.AppException;
import util.DBUtil;
import util.Token;

import dao.UserDao;

public class UserDaoImpl implements UserDao{

	/**
	 * 医生登录
	 * @return  0  登录失败
	 *          1  医生登录
	 */
	public int login(String name, String password, String token) throws AppException {

        int id;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 定义及预处理sql语句
            String str = "UPDATE user SET token = ? WHERE username = ? AND password = ? AND bedoctor = 1";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setString(1, token);
            st.setString(2, name);
            st.setString(3, password);

            // 执行sql语句
            // id = 0 更新不成功
            id = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new AppException("/impl/UserDaoImpl/login");
        }finally
        {
            // 关闭连接
            DBUtil.closeResultSet(rs);     // 关闭数据集
            DBUtil.closeStatement(st);     //     sql语句
            DBUtil.closeConection(conn);   //     连接
        }
        return id;
	}
	
	/**
	 * 患者注册
	 * @param password(已加密)
	 * @return 注册成功 true
	 *         注册失败 false 
	 */
	public String register(String username, String password) throws AppException{
		
		String message = "";
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 定义及预处理sql语句
            String str = "INSERT INTO user VALUES(?, ?, 0)";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setString(1, username);
            st.setString(2, password);

            // ִ执行新增语句
            int result = -1;
            result = st.executeUpdate();

            // 判断处理结果
            if(result > 0)
            	message = "true";
            else
            	message = "false";

        } catch (SQLException e) {
            e.printStackTrace();
            throw new AppException("/impl/UserDaoImpl/register");
        }finally
        {
            // 关闭连接
            DBUtil.closeResultSet(rs);     // 关闭数据集
            DBUtil.closeStatement(st);     //     sql语句
            DBUtil.closeConection(conn);   //     连接
        }
		
		return message;
	}
	
	/**
	 * 患者登录
	 * @return "true""false"
	 */
	public int adLogin(String name, String password, String token) throws AppException{
		int id;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 定义及预处理sql语句
            String str = "UPDATE user SET token = ? WHERE username = ? AND password = ? AND bedoctor = 0";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setString(1, token);
            st.setString(2, name);
            st.setString(3, password);

            // 执行sql语句
            id = st.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            throw new AppException("/impl/UserDaoImpl/adLogin");
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
