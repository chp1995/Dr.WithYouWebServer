package com.drwithyou.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drwithyou.util.*;


/**
 * Servlet implementation class admsg
 */
@WebServlet("/admsg")
public class admsg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admsg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取信息列表
		String  token = request.getParameter("token");
		String username = Token.checkToken(token);
		if(username==null)
		{
			String json = "{\"result\":\"error\",\"errormsg\":\"Toeken error!\"}";
			json= StringUtil.Encode(json);
			response.getWriter().append(json);
			return;
		}
		//获取信息列表
		String sql = "select * from message where sender='"+username+"' or receiver='"+username+"'";
		Connection con = DBUtil.getConnection();
		try {
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			String json ="{\"result\":\"true\",\"set\":[";
			int n = 0;
			while(rs.next())
			{
				String sender = rs.getString(1);
				String recevier = rs.getString(2);
				String msg = rs.getString(3);
				String time = rs.getString(4);
				String subjson = "{\"sender\":\""+sender+"\",\"revevier\":\""+recevier+"\",\"msg\":\""+msg+"\",\"time\":\""+time+"\"},";
			    json+=subjson;
			    n++;
			}
		    if(n>0)
		    {
		    	json = json.substring(0, json.length()-1);//去掉逗号
		    }
		    json += "]}";
		    json = StringUtil.Encode(json);
			response.getWriter().append(json);
			DBUtil.closeStatement(st);
			DBUtil.closeResultSet(rs);
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
		String token = request.getParameter("token");
		String username = Token.checkToken(token);
		String msg= request.getParameter("msg");
		//解码
		msg = StringUtil.Decode(msg);
		if(username==null)
		{
			String json = "{\"result\":\"error\",\"errormsg\":\"Toeken error!\"}";
			json= StringUtil.Encode(json);
			response.getWriter().append(json);
			return;
		}
		//获取医生username
		String docusrSql = "select doctorusr from DoctorPatient where patientusr = '"+username+"'";
		String docusr = "";
		Connection con = DBUtil.getConnection();
		try {
			PreparedStatement st = con.prepareStatement(docusrSql);
			ResultSet rs = st.executeQuery();
			if(rs.next())
			{
				docusr = rs.getString(1);
				DBUtil.closeStatement(st);
				DBUtil.closeResultSet(rs);
			    DBUtil.closeConection(con);
			}
			else
			{
				response.setStatus(500);
				DBUtil.closeStatement(st);
				DBUtil.closeResultSet(rs);
			    DBUtil.closeConection(con);
				return;
			}
			//插入信息
			String insertSql = "insert into message(Sender,receiver,Msg,Time) values('"+username+"','"+docusr+"','"+msg+"','"+new Date().getTime()+"')";
			con = DBUtil.getConnection();
			st = con.prepareStatement(insertSql);
			int rt = st.executeUpdate();
			DBUtil.closeStatement(st);
			DBUtil.closeResultSet(rs);
		    DBUtil.closeConection(con);
			if(rt==1)
			{
				String json = "{\"result\":\"true\"}";
				json= StringUtil.Encode(json);
				response.getWriter().append(json);
			}
			else
			{
				String json = "{\"result\":\"error\",\"errormsg\":\"Save error!\"}";
				json= StringUtil.Encode(json);
				response.getWriter().append(json);
				return;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
