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
	 *          1  登陆成功
	 */
	public int login(String name, String password, String token){

        int id = 0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "UPDATE user SET token = ? WHERE username = ? AND password = ? AND bedoctor = 1";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setString(1, token);
            st.setString(2, name);
            st.setString(3, password);

            // 执行sql语句
            // id = 0  update失败
            id = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally
        {
            // 关闭数据库连接
            DBUtil.closeResultSet(rs);   
            DBUtil.closeStatement(st);    
            DBUtil.closeConection(conn);   
        }
        return id;
	}
	
	/**
	 * 病人注册
	 * @param password(已加密)
	 * @return 注册成功 true
	 *         注册失败 false 
	 */
	public String register(String username, String password){
		
		String message = "";
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "INSERT INTO user VALUES(?, ?, 0)";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setString(1, username);
            st.setString(2, password);

            // 执行sql
            int result = -1;
            result = st.executeUpdate();

            // 处理结果
            if(result > 0)
            	message = "true";
            else
            	message = "false";

        } catch (SQLException e) {
            e.printStackTrace();
        }finally
        {
            // 关闭数据库连接
            DBUtil.closeResultSet(rs);   
            DBUtil.closeStatement(st);     
            DBUtil.closeConection(conn);   
        }
		
		return message;
	}
	
	/**
	 * 鎮ｈ�呯櫥褰�
	 * @return "true""false"
	 */
	public int adLogin(String name, String password, String token){
		int id = 0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 鍒涘缓鏁版嵁搴撹繛鎺�
            conn = DBUtil.getConnection();

            // 瀹氫箟鍙婇澶勭悊sql璇彞
            String str = "UPDATE user SET token = ? WHERE username = ? AND password = ? AND bedoctor = 0";
            st = conn.prepareStatement(str);

            // 璁剧疆鍙傛暟
            st.setString(1, token);
            st.setString(2, name);
            st.setString(3, password);

            // 鎵цsql璇彞
            id = st.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }finally
        {
            // 鍏抽棴杩炴帴
            DBUtil.closeResultSet(rs);     // 鍏抽棴鏁版嵁闆�
            DBUtil.closeStatement(st);     //     sql璇彞
            DBUtil.closeConection(conn);   //     杩炴帴
        }
        return id;
	}

	/**
	 * 鍒ゆ柇鐢ㄦ埛鍚嶆槸鍚﹀瓨鍦�
	 * @param username 鐢ㄦ埛鍚�
	 * @return true/false
	 */
	public boolean isExist(String username){
		
		boolean result = false;
		
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 鍒涘缓鏁版嵁搴撹繛鎺�
            conn = DBUtil.getConnection();

            // 瀹氫箟鍙婇澶勭悊sql璇彞
            String str = "SELECT * from userinfo WHERE username = ?";
            st = conn.prepareStatement(str);

            // 璁剧疆鍙傛暟
            st.setString(1, username);

            // 鎵цsql璇彞
            rs = st.executeQuery(str);
            if(rs.next())
            	result = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally
        {
            // 鍏抽棴杩炴帴
            DBUtil.closeResultSet(rs);     // 鍏抽棴鏁版嵁闆�
            DBUtil.closeStatement(st);     //     sql璇彞
            DBUtil.closeConection(conn);   //     杩炴帴
        }
		
		return result;
	}
	

	/**
	 * 获取token对应的用户名
	 * @return ""token过期
	 */
	public String getUsername(String token){
		
		String name = "";
		
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 定义及预处理sql语句
            System.out.println("token = " + token);
            String str = "SELECT * from user WHERE token = '" + token+"'";
            st = conn.prepareStatement(str);

            // 设置参数
            // st.setString(1, token);

            // 执行sql语句
            rs = st.executeQuery(str);
            
            // 处理结果
            if(rs.next()){
            	name = rs.getString("username");
            	System.out.print(name + "\t" + token);
            }
            	
        } catch (SQLException e) {
            e.printStackTrace();
        }finally
        {
            // 关闭数据库连接
            DBUtil.closeResultSet(rs);     
            DBUtil.closeStatement(st);    
            DBUtil.closeConection(conn);   
        }
        
        return name;		
	}

}
