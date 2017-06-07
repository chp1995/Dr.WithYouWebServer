package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.MessageDao;
import model.Message;
import util.DBUtil;
import util.TimeUtil;


public class MessageDaoImpl implements MessageDao{
	
	
	/**
	 * 获取用户一与用户二的对话（用户一方）
	 */
	public ArrayList<Message> getMessage(String docusr, String pausr)
	{
		
		ArrayList<Message> list = new ArrayList<Message>();
		
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 定义及预处理sql语句
            String str = "SELECT * FROM message WHERE sender = '" + docusr + "' AND receiver = '" + pausr + "'";
            st = conn.prepareStatement(str);

            // 设置参数
//            st.setString(1, username);
            //st.setString(1, docusr);
            //st.setString(2, pausr);
            

            // 执行sql语句
            rs = st.executeQuery(str);
            while(rs.next()){
            	Message message = new Message();
            	message.setSender(docusr);
            	message.setReceiver(pausr);
            	message.setTime(TimeUtil.stampToDate(rs.getString("time")));
            	message.setMessage(rs.getString("msg"));
            	list.add(message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally
        {
            // 关闭连接
            DBUtil.closeResultSet(rs);     // 关闭数据集
            DBUtil.closeStatement(st);     //     sql语句
            DBUtil.closeConection(conn);   //     连接
        }

		return list;	
	}
	
	/**
	 * 发送消息
	 */
	public int sendMessage(String sender, String receiver, String time, String message)
	{
		int result = -1;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "INSERT INTO message VALUES(?, ?, ?, ?)";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setString(1, sender);
            st.setString(2, receiver);
            st.setString(3, message);
            st.setString(4, time);

            // 执行sql
            result = st.executeUpdate();
            System.out.println("send message " + sender + "\t" + receiver + "\t" + message + "\t" + time + "\t" + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally
        {
            // 关闭数据库连接
            DBUtil.closeResultSet(rs);   
            DBUtil.closeStatement(st);     
            DBUtil.closeConection(conn);   
        }
        
        return result;		
	}


}
