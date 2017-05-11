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
import com.drwithyou.util.*;
/**
 * Servlet implementation class adregister
 */
@WebServlet("/adregister")
public class adregister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adregister() {
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
			if(usr==null || pwd==null)
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
			String sqlUpdate = "insert into user(username,password,bedoctor,token) values('"+usr+"','"+pwd+"',false,'"+token+"')";
		    String sqlQuery  = "select * from user where username = '"+usr+"'";
		    ResultSet rs =  st.executeQuery(sqlQuery);
		    if(rs.next())//存在
		    {
		    	DBUtil.closeResultSet(rs);
		    	DBUtil.closeStatement(st);
		    	DBUtil.closeConection(con);
		    	
		    	String json = "{\"result\":\"error\",\"errormsg\":\"Username already exist.\"}";
		    	json= StringUtil.Encode(json);
		    	response.getWriter().append(json);
		    	return;
		    }
		    else
		    {
		       int n = st.executeUpdate(sqlUpdate);
		       
		       DBUtil.closeStatement(st);
		       DBUtil.closeConection(con);
		       if(n>0){
		    	   //插入信息
		    	 String insertinfo = "insert into userinfo(username) values('"+usr+"')";
		    	 con.prepareStatement(insertinfo).executeUpdate();
		         String json = "{\"result\":\"true\"}";
		         json= StringUtil.Encode(json);
		    	 response.getWriter().append(json);
		       }
		       else
		       {
		    	  String json = "{\"result\":\"error\",\"errormsg\":\"Unknow ERROR\"}";
		    	  json= StringUtil.Encode(json);
		    	  DBUtil.closeStatement(st);
			      DBUtil.closeConection(con);
		    	  response.getWriter().append(json);
		       }
		    	return;
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
