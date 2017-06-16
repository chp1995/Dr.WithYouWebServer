package com.drwithyou.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drwithyou.util.DBUtil;
import com.drwithyou.util.StringUtil;
import com.drwithyou.util.Token;

/**
 * Servlet implementation class adlogin
 */
@WebServlet("/adlogin")
public class adlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adlogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con  =DBUtil.getConnection();
		try {
			Statement st = con.createStatement();
			String token = Token.getToken();
			String usr  = request.getParameter("username");
			String pwd  = request.getParameter("password");
			if(usr==null || pwd == null )
			{
				DBUtil.closeStatement(st);
	    	    DBUtil.closeConection(con);
				String json = "{\"result\":\"error\",\"errormsg\":\"Passwrod or username error.\"}";
				json= StringUtil.Encode(json);
				response.getWriter().append(json);
		    	return;
			}
			if(pwd.length()!=32)
			{
				DBUtil.closeStatement(st);
	    	    DBUtil.closeConection(con);
				String json = "{\"result\":\"error\",\"errormsg\":\"Passwrod or username error.\"}";
				json= StringUtil.Encode(json);
				response.getWriter().append(json);
		    	return;
			}
			pwd = pwd.toUpperCase();
			
			String sqlQuery  = "select * from user where username = '"+usr+"' and password = '"+pwd+"'";
			ResultSet rs = st.executeQuery(sqlQuery);
			if(rs.next())//OK
			{
				
				String sqlUpdate = "update user set token ='"+token+"' where username = '"+usr+"'";
				int n= st.executeUpdate(sqlUpdate);
				if(n>0)
				{
					DBUtil.closeResultSet(rs);
			    	DBUtil.closeStatement(st);
			    	DBUtil.closeConection(con);
					String json = "{\"result\":\"true\",\"token\":\""+token+"\"}";
					json= StringUtil.Encode(json);
					response.getWriter().append(json);
			    	return;
				}
			}
			DBUtil.closeResultSet(rs);
	    	DBUtil.closeStatement(st);
	    	DBUtil.closeConection(con);
	    	String json = "{\"result\":\"error\",\"errormsg\":\"Passwrod or username error.\"}";
			json= StringUtil.Encode(json);
	    	response.getWriter().append(json);
	    	return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}

}
