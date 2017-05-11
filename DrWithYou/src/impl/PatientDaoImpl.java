package impl;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.PatientDao;
import model.Patient;
import util.DBUtil;

public class PatientDaoImpl implements PatientDao{
	
	/**
	 * 获取所有病人列表
	 */
	public ArrayList<Patient> getAllPatients(){
		
		ArrayList<Patient> list = new ArrayList<Patient>();

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {

			// 创建数据库连接
			conn = DBUtil.getConnection();

			// 定义及预处理sql语句
			String str = "SELECT * FROM userinfo";
			st = conn.prepareStatement(str);
			

			// 执行sql语句
			rs = st.executeQuery();

			// 处理结果
			while (rs.next()) {
				Patient patient = new Patient();
				patient.setUsername(rs.getString("username"));
				patient.setPatient_name(rs.getString("name"));
				patient.setPhone(rs.getString("phone"));
				patient.setSex(rs.getString("sex"));
				patient.setIdCard(rs.getString("idcard"));
				list.add(patient);
				System.out.println("all patient\t" + patient.getPatient_name() + "   " + patient.getPhone());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭数据库连接
            DBUtil.closeResultSet(rs);     
            DBUtil.closeStatement(st);    
            DBUtil.closeConection(conn);   
		}
		
		return list;
	}
	
	/**
	 * 添加医患关系
	 */
	public boolean addPatient(String doctorusr, String patientusr){
		
		boolean ans = false;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {

			// 创建数据库连接
			conn = DBUtil.getConnection();

			// 定义及预处理sql语句
			String str = "INSERT INTO doctorpatient VALUES(?, ?)";
			st = conn.prepareStatement(str);
			
			// 设置参数
			st.setString(1, doctorusr);
            st.setString(2, patientusr);

			// 执行sql语句
            int result = -1;
            result = st.executeUpdate();

			// 处理结果
            if(result > 0)
            	ans = true;   	

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭数据库连接
            DBUtil.closeResultSet(rs);     
            DBUtil.closeStatement(st);    
            DBUtil.closeConection(conn);   
		}
		
		return ans;
	}
	
	/**
	 * 获取该医生的所有病人
	 */
	public ArrayList<Patient> getDocPatients(String username){
		
		ArrayList<Patient> list = new ArrayList<Patient>();

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {

			// 创建数据库连接
			conn = DBUtil.getConnection();

			// 定义及预处理sql语句
			String str = "SELECT * FROM userinfo WHERE username IN (SELECT patientusr FROM doctorpatient WHERE doctorusr = ?)";
			st = conn.prepareStatement(str);
			
			// 设置参数
			st.setString(1, username);

			// 执行sql语句
			rs = st.executeQuery();

			// 处理结果
			while (rs.next()) {
				Patient patient = new Patient();
				patient.setUsername(rs.getString("username"));
				patient.setPatient_name(rs.getString("name"));
				patient.setPhone(rs.getString("phone"));
				patient.setSex(rs.getString("sex"));
				patient.setIllness(rs.getString("illness"));
				patient.setIdCard(rs.getString("idcard"));
				list.add(patient);
				System.out.println("doc patient\t" + patient.getPatient_name() + "   " + patient.getPhone());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 鍏抽棴杩炴帴
            DBUtil.closeResultSet(rs);     // 鍏抽棴鏁版嵁闆�
            DBUtil.closeStatement(st);     //     sql璇彞
            DBUtil.closeConection(conn);   //     杩炴帴
		}
		
		return list;
	}
	
	/**
	 * 删除医生和病人的关系对应
	 */
	public int deleteDocPatient(String doctorusr, String patientusr){
		
		int result = 0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "DELETE FROM doctorpatient WHERE doctorusr = ? AND patientusr = ?";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setString(1, doctorusr);
            st.setString(2, patientusr);

            // 执行sql语句
            // id = 0  update失败
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
