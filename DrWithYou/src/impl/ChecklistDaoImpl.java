package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.AppException;
import util.DBUtil;

import dao.ChecklistDao;
import model.Checklist;

public class ChecklistDaoImpl implements ChecklistDao{
	
	/**
	 * 获取医生创建的所有检查项
	 * @param username 医生用户名
	 */
	public ArrayList<Checklist> getChecklist(String username){
		
		ArrayList<Checklist> list = new ArrayList<Checklist>();
		
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 定义及预处理sql语句
            String str = "SELECT * FROM checkitem WHERE creator = '" + username + "'";
            st = conn.prepareStatement(str);

            // 设置参数
//            st.setString(1, username);

            // 执行sql语句
            rs = st.executeQuery(str);
            while(rs.next()){
            	Checklist check = new Checklist();
            	check.setCid(rs.getInt("cid"));
            	check.setChecklist_name(rs.getString("itemname"));
            	check.setChecktime(rs.getString("checktime"));
            	check.setCreator(username);
            	check.setDescription(rs.getString("description"));
            	list.add(check);    
            	System.out.println(check.getChecklist_name() + "\t" + check.getChecktime());
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
	 * 新建检查项
	 */
	public int addChecklist(String username, String itemname, String checktime, String description){
		
		int result = -1;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "INSERT INTO checkitem(itemname, checktime, creator, description) VALUES(?, ?, ?, ?)";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setString(1, itemname);
            st.setString(2, checktime);
            st.setString(3, username);
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
	
	
	public Checklist getCheckitem(int cid){
		
		Checklist check = null;
		
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 定义及预处理sql语句
            String str = "SELECT * FROM checkitem WHERE cid = '" + cid + "'";
            st = conn.prepareStatement(str);

            // 设置参数
//            st.setString(1, username);

            // 执行sql语句
            rs = st.executeQuery(str);
            while(rs.next()){
            	check = new Checklist();
            	check.setCid(cid);
            	check.setChecklist_name(rs.getString("itemname"));
            	check.setChecktime(rs.getString("checktime"));
            	check.setCreator(rs.getString("creator"));
            	check.setDescription(rs.getString("description"));  
            	System.out.println(check.getChecklist_name() + "\t" + check.getChecktime() + "\t" + check.getDescription());
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

		return check;
		
	}
	
	/**
	 * 修改检查项
	 */
	public int saveCheckitem(int cid, String itemname, String checktime, String description){
		
		int id = 0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "UPDATE checkitem SET itemname = ?, checktime = ?, description = ? WHERE cid = ?";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setString(1, itemname);
            st.setString(2, checktime);
            st.setString(3, description);
            st.setInt(4, cid);

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
	 * 删除检查项
	 */
	public int deleteCheckitem(int cid){
		
		int id = 0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "DELETE FROM checkitem WHERE cid = ?";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setInt(1, cid);

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
	 * 获取病人对应的检查项id
	 */
	public ArrayList<Integer> getPatientChecklist(String username){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 定义及预处理sql语句
            String str = "SELECT * FROM usercheckitem WHERE username = '" + username + "'";
            st = conn.prepareStatement(str);

            // 设置参数
//            st.setString(1, username);

            // 执行sql语句
            rs = st.executeQuery(str);
            while(rs.next()){
            	list.add(rs.getInt("id"));
            	System.out.println("patient checkitem\t" + username + "\t" + rs.getInt("id"));
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
	 * 删除病人对应的所有检查项
	 */
	public int deletePatientChecklist(String username){
		
		int id = 0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "DELETE FROM usercheckitem WHERE username = '" + username + "'";
    		System.out.println("delete patient checkpoint\t" + username);
            st = conn.prepareStatement(str);

            // 设置参数
//            st.setInt(1, cid);

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
	 * 添加病人和检查项的联系
	 */
	public int addPatientChecklist(String username, int cid){
		
		int result = -1;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "INSERT INTO usercheckitem VALUES(?, ?)";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setString(1, username);
            st.setInt(2, cid);

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
	 * 删除病人和检查项的对应
	 */
	public int deletePatientChecklist(int cid){
		
		int id = 0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "DELETE FROM usercheckitem WHERE id = '" + cid + "'";
    		System.out.println("delete patient checkpoint\t" + cid);
            st = conn.prepareStatement(str);

            // 设置参数
//            st.setInt(1, cid);

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
	 * 删除模板和检查项的对应
	 */
	public int deleteTempletChecklist(int cid){
		
		int id = 0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "DELETE FROM templetcheckitem WHERE cid = '" + cid + "'";
    		System.out.println("delete templet checkpoint\t" + cid);
            st = conn.prepareStatement(str);

            // 设置参数
//            st.setInt(1, cid);

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
}
