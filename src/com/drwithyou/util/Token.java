package com.drwithyou.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;


public class Token {
	
	public static String getToken(){
		UUID uuid  =  UUID.randomUUID(); 
		String s = UUID.randomUUID().toString();
		return s.toUpperCase();
	}
	//true return username false return null;
	public static String checkToken(String token){
		if(token==null)
			return null;
		if(token.length()!=36)
			return null;
		token=token.toUpperCase();
		Connection con = DBUtil.getConnection();
		
			//创建statement
		Statement st;
		try {
			st = con.createStatement();
			String sqlQuery = "select username from user where token = '"+token+"'";
			ResultSet rs = st.executeQuery(sqlQuery);
			String usr = "";
			
			if(rs.next())//正确
				usr= rs.getString(1);	
			else
				usr = null;
			DBUtil.closeStatement(st);
		    DBUtil.closeConection(con);
		    return usr;
	
		}
		catch (SQLException e) 
		{
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return null;
		}
}


