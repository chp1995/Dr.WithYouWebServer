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
 * Servlet implementation class addoctor
 */
@WebServlet("/addoctor")
public class addoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addoctor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String token = request.getParameter("token");
		String usr = Token.checkToken(token);
		if(usr==null)
		{
			String json = "{\"result\":\"error\",\"errormsg\":\"Toeken error!\"}";
			json= StringUtil.Encode(json);
		    response.getWriter().append(json);
		    return;
		}
		//获取医生姓名
		String sql = "select userinfo.name from userinfo,doctorpatient where doctorpatient.patientusr = '"+usr +"' and doctorpatient.doctorusr = userinfo.username";
		System.out.println(sql);
		//String checkjson = "select submittime from checkeditem from checkeditem where username = '"+usr +"' and id = "+ cid;
		Connection conn = DBUtil.getConnection();
		try {
			//没问题 可以插入
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			//int effect = st.executeUpdate(sql);
			if(rs.next())
			{
				String name = rs.getString(1);
				String json = "{\"result\":\"true\",\"set\":{\"name:\":\""+name+"\"}}";
				json= StringUtil.Encode(json);
			    response.getWriter().append(json);
			   
			}
			else
			{
				String json = "{\"result\":\"error\",\"errormsg\":\"Unknow error!\"}";
				json= StringUtil.Encode(json);
			    response.getWriter().append(json);
			    
			}
			DBUtil.closeStatement(st);
			DBUtil.closeConection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
