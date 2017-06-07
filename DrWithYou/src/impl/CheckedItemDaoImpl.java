package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CheckedItemDao;
import model.CheckedItem;
import model.Templet;
import util.DBUtil;
import util.TimeUtil;

public class CheckedItemDaoImpl implements CheckedItemDao{
	
	/**
	 * 获取用户提交的该检查项数据
	 */
	public ArrayList<CheckedItem> getData(String username, int id)
	{
		
		ArrayList<CheckedItem> list = new ArrayList<CheckedItem> ();
		
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 设置sql语句
            String str = "SELECT * FROM checkeditem WHERE username = '" + username + "' AND id = '" + id + "'";
            st = conn.prepareStatement(str);

            // 设置参数
//            st.setString(1, username);

            // 执行sql
            rs = st.executeQuery(str);
            while(rs.next()){
            	CheckedItem item = new CheckedItem();
            	item.setUsername(username);
            	item.setId(id);
            	item.setValue(rs.getFloat("value"));  
            	//item.setSubmittime(TimeUtil.stampToDate(rs.getString("submittime")));
            	item.setSubmittime(rs.getString("submittime"));
            	list.add(item);
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

}
