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
	
	public ArrayList<Templet> getTempletList(String username) throws AppException{
		
		ArrayList<Templet> list = new ArrayList<Templet> ();
		
		Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            // 创建数据库连接
            conn = DBUtil.getConnection();

            // 定义及预处理sql语句
            String str = "SELECT * FROM templet WHERE creator = ?";
            st = conn.prepareStatement(str);

            // 设置参数
            st.setString(1, username);

            // 执行sql语句
            rs = st.executeQuery(str);
            while(rs.next()){
            	Templet templet = new Templet();
            	templet.setTemplet_name(rs.getString("templetname"));
            	templet.setCreator(username);
            	templet.setSuitable(rs.getString("suitable"));
            	templet.setDescription(rs.getString("description"));            	
            	
            	list.add(templet);            	
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
