package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Checklist;
import model.Templet;
import util.AppException;
import util.DBUtil;
import dao.TempletDao;

public class TempletDaoImpl implements TempletDao{
	
	/**
	 * 获取医生所创建的所有模板
	 */
	public ArrayList<Templet> getTempletList(String username){
		
		ArrayList<Templet> list = new ArrayList<Templet> ();
		
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "SELECT * FROM templet WHERE username = '" + username + "'";
            st = conn.prepareStatement(str);

            // 设置参数
//            st.setString(1, username);

            // 执行sql
            rs = st.executeQuery(str);
            while(rs.next()){
            	Templet templet = new Templet();
            	templet.setTid(rs.getInt("tid"));
            	templet.setTemplet_name(rs.getString("templetname"));
            	templet.setCreator(username);
            	templet.setSuitable(rs.getString("suitable"));
            	templet.setDescription(rs.getString("description"));            	
            	
            	list.add(templet);            	
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
		
		return list;	
	}
	
	/**
	 * 删除模板
	 */
	public int deleteTemplet(int tid){
		
		int id = 0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "DELETE FROM templet WHERE tid = ?";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setInt(1, tid);

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
	 * 添加模板
	 */
	public int addTemplet(String username, String templetname, String suitable, String description){
		
		int result = -1;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "INSERT INTO templet(templetname, username, suitable, description) VALUES(?, ?, ?, ?)";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setString(1, templetname);
            st.setString(2, username);
            st.setString(3, suitable);
            st.setString(4, description);

            // 执行sql
            result = st.executeUpdate();

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
	
	/**
	 * 获取模板细则
	 */
	public Templet getTemplet(int tid){
		
		Templet templet = null;
		
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 定义及预处理sql语句
            String str = "SELECT * FROM templet WHERE tid = '" + tid + "'";
            st = conn.prepareStatement(str);

            // 设置参数
//            st.setString(1, username);

            // 执行sql语句
            rs = st.executeQuery(str);
            while(rs.next()){
            	templet = new Templet();
            	templet.setTid(tid);
            	templet.setTemplet_name(rs.getString("templetname"));
            	templet.setSuitable(rs.getString("suitable"));
            	templet.setDescription(rs.getString("description"));
            	System.out.println("get templet\t" + templet.getTemplet_name()+ "\t" + templet.getSuitable() + "\t" + templet.getDescription());
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

		return templet;
	}
	
	/**
	 * 修改模板
	 */
	public int saveTemplet(int tid, String templetname, String suitable, String description){
		
		int id = 0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "UPDATE templet SET templetname = ?, suitable = ?, description = ? WHERE tid = ?";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setString(1, templetname);
            st.setString(2, suitable);
            st.setString(3, description);
            st.setInt(4, tid);

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
	 * 删除模板和检查项
	 */
	public int deleteTempletCheck(int tid){
		
		int id = 0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "DELETE FROM templetcheckitem WHERE tid = ?";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setInt(1, tid);

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
	 * 添加模板和检查项关系
	 */
	public int addTempletCheck(int tid, int cid){
		
		int result = -1;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "INSERT INTO templetcheckitem VALUES(?, ?)";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setInt(1, cid);
            st.setInt(2, tid);

            // 执行sql
            result = st.executeUpdate();

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

	// 获取所有模板对应的检查项
	public ArrayList<Integer> getTempletCheck(int tid){
		
		ArrayList<Integer> list = new ArrayList<Integer> ();
		
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "SELECT * FROM templetcheckitem WHERE tid = '" + tid + "'";
            st = conn.prepareStatement(str);

            // 设置参数
//            st.setString(1, username);

            // 执行sql
            rs = st.executeQuery(str);
            while(rs.next()){
            	int temp = rs.getInt("cid");
            	list.add(temp);            	
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
		
		return list;	
	}
	
	/**
	 * 获取病人对应模板id
	 */
	public int getPatientTemplet(String patientName){
				
		int tid = -1;
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 定义及预处理sql语句
            String str = "SELECT * FROM usertemplet WHERE username = '" + patientName + "'";
            st = conn.prepareStatement(str);

            // 设置参数
//            st.setString(1, patientName);

            // 执行sql语句
            rs = st.executeQuery(str);
            while(rs.next()){
            	tid = rs.getInt("tid");
            	System.out.println("get patient templet\t" + patientName + "\t" + tid);
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

		return tid;
	}
	
	/**
	 * 删除该模板和所有病人的对应关系
	 */
	public int deletePatientTemplet(int tid){
		
		int id = 0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "DELETE FROM usertemplet WHERE tid = ?";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setInt(1, tid);

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
	 * 删除该病人和该模板的对应关系
	 */
	public int deletePatientTemplet(String username){
		
		int id = 0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "DELETE FROM usertemplet WHERE username = ?";
            st = conn.prepareStatement(str);
            System.out.println("detele usertemplet username = " + username);

            // 设置参数
            st.setString(1, username);

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
	 * 添加该病人和模板的对应关系
	 */
	public int addPatientTemplet(String username, int tid){
		
		int result = -1;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "INSERT INTO usertemplet VALUES(?, ?)";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setString(1, username);
            st.setInt(2, tid);

            // 执行sql
            result = st.executeUpdate();

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
