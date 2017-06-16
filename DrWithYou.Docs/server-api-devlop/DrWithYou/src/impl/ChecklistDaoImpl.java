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
	public ArrayList<Checklist> getChecklist(String username) throws AppException{
		
		ArrayList<Checklist> list = new ArrayList<Checklist>();
		
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 定义及预处理sql语句
            String str = "SELECT * FROM checklist WHERE creator = ?";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setString(1, username);

            // 执行sql语句
            rs = st.executeQuery(str);
            while(rs.next()){
            	Checklist check = new Checklist();
            	check.setChecklist_name(rs.getString("itemname"));
            	check.setChecktime(rs.getString("checktime"));
            	check.setCreator(username);
            	check.setDescription(rs.getString("descrption"));
            	
            	list.add(check);            	
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new AppException("/impl/ChecklistDaoImpl/getChecklists");
        }finally
        {
            // 关闭连接
            DBUtil.closeResultSet(rs);     // 关闭数据集
            DBUtil.closeStatement(st);     //     sql语句
            DBUtil.closeConection(conn);   //     连接
        }

		return list;
	}

}
