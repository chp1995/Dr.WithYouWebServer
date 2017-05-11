package com.drwithyou.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
 * Servlet implementation class aduser
 */
@WebServlet("/aduser")
public class aduser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public aduser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String token = request.getParameter("token");
		//转换大写
		String usr = Token.checkToken(token);
		if(usr==null)
		{
			String json = "{\"result\":\"error\",\"errormsg\":\"Toeken error!\"}";
			json= StringUtil.Encode(json);
		    response.getWriter().append(json);
		    return;
		}
		//获取数据库连接
		Connection con = DBUtil.getConnection();
		try {
			//创建statement
			Statement st = con.createStatement();
			
			//拼接sql
			
			String sqlInfo = "select * from userinfo where username = '"+usr+"'";
			String sqlDoctor = "select name from userinfo,doctorpatient where username=doctorusr and patientusr='"+usr+"'";
			PreparedStatement  st2 = con.prepareStatement(sqlDoctor);
			ResultSet rsinfo = st.executeQuery(sqlInfo);
			ResultSet rsdoctor = st2.executeQuery();
			if(rsinfo.next())
			{
				    rsdoctor.next();
					//String username = rsinfo.getString(1);
					//String name = rsinfo.getString(2);
					//String phone = rsinfo.getString(3);
					//String 
					String json = "{\"result\":\"true\",\"set\":{"
							+ "\"username\":\""+rsinfo.getString(1)+"\","
							+ "\"name\":\""+rsinfo.getString(2)+"\","
							+ "\"phone\":\""+rsinfo.getString(3)+"\","
							+ "\"illness\":\""+rsinfo.getString(4)+"\","
							+ "\"begintime\":\""+rsinfo.getString(5)+"\","
							+ "\"endtime\":\""+rsinfo.getString(6)+"\","
							+ "\"sex\":\""+rsinfo.getString(7)+"\","
							+ "\"iconpath\":\""+rsinfo.getString(8)+"\","
							+ "\"idcard\":\""+rsinfo.getString(9)+"\","
						    + "\"doctor\":\""+rsdoctor.getString(1)+"\""
							+"}}";
					System.out.println(rsinfo.getString(2));
					System.out.println(json);
					json = StringUtil.Encode(json);
					response.getWriter().append(json);
			}
			else
			{
				String json =  "{\"result\":\"error\",\"errormsg\":\"Toeken error!\"}";
				json= StringUtil.Encode(json);
				response.getWriter().append(json);
			}
			DBUtil.closeStatement(st);
		    DBUtil.closeConection(con);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//获取token
		String token = request.getParameter("token");
		token =token.toUpperCase();
		//转换大写
		
		String usr = Token.checkToken(token);
		if(usr==null)
		{
			String json = "{\"result\":\"error\",\"errormsg\":\"Toeken error!\"}";
			json= StringUtil.Encode(json);
		    response.getWriter().append(json);
		    return;
		}
		
		Connection con = DBUtil.getConnection();
		try {
			//创建statement
			Statement st = con.createStatement();
			//拼接sql
			String sqlInfo = "select * from userinfo where username = '"+usr+"'";
			ResultSet rsinfo = st.executeQuery(sqlInfo);
			if(rsinfo.next())
			{
					//获取并解码
					String name = request.getParameter("name");
					name = StringUtil.Decode(name);
					String phone = request.getParameter("phone");
					phone = StringUtil.Decode(phone);
					String sex   = request.getParameter("sex");
					sex = StringUtil.Decode(sex);
					String idcard = request.getParameter("idcard");
					idcard = StringUtil.Decode(idcard);
					
					//sql
					String upname = "update userinfo set name = '"+name+"' where username = '"+usr+"'";
					String upphone = "update userinfo set phone = '"+phone+"' where username = '"+usr+"'";
					String upsex = "update userinfo set sex = '"+sex+"' where username = '"+usr+"'";
					String upidcard = "update userinfo set idcard = '"+idcard+"' where username = '"+usr+"'";
					
					//update
					st.executeUpdate(upname);
					st.executeUpdate(upphone);
					st.executeUpdate(upsex);
					st.executeUpdate(upidcard);
					String json = "{\"result\":\"true\"}";
					json = StringUtil.Encode(json);
					response.getWriter().append(json);
					
			}
			else
			{
				String json =  "{\"result\":\"error\",\"errormsg\":\"Toeken error!\"}";
				json= StringUtil.Encode(json);
				response.getWriter().append(json);
			}
				
			DBUtil.closeStatement(st);
		    DBUtil.closeConection(con);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
