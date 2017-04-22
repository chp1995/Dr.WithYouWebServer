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
            // �������ݿ�����
            conn = DBUtil.getConnection();

            // ���弰Ԥ����sql���
            String str = "SELECT * FROM templet WHERE creator = ?";
            st = conn.prepareStatement(str);

            // ���ò���
            st.setString(1, username);

            // ִ��sql���
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
            // �ر�����
            DBUtil.closeResultSet(rs);     // �ر����ݼ�
            DBUtil.closeStatement(st);     //     sql���
            DBUtil.closeConection(conn);   //     ����
        }
		
		return list;
		
	}

}
