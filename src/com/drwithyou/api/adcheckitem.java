package com.drwithyou.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drwithyou.util.DBUtil;
import com.drwithyou.util.StringUtil;
import com.drwithyou.util.Token;

/**
 * Servlet implementation class adcheckitem
 */
@WebServlet("/adcheckitem")
public class adcheckitem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adcheckitem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String token = request.getParameter("token");
		String usr = Token.checkToken(token);
		if(usr==null)
		{
			String json = "{\"result\":\"error\",\"errormsg\":\"Toeken error!\"}";
			json= StringUtil.Encode(json);
		    response.getWriter().append(json);
		    return;
		}
		String cid = request.getParameter("cid");
		String value = request.getParameter("value");
		if(cid ==null || value == null)
		{
			String json = "{\"result\":\"error\",\"errormsg\":\"Data error!\"}";
			json= StringUtil.Encode(json);
		    response.getWriter().append(json);
		    return;
		}
		//插入到checkeditem
		double dval = Double.parseDouble(value);
		long   timestamp = new Date().getTime();
		//SQL
		String sql = "insert into checkeditem(username,id,submittime,value) values('"+usr+"','"+cid+"','"+timestamp+"',"+dval+")";
		//String checkjson = "select submittime from checkeditem from checkeditem where username = '"+usr +"' and id = "+ cid;
		Connection conn = DBUtil.getConnection();
		try {
			//没问题 可以插入
			Statement st = conn.createStatement();
			int effect = st.executeUpdate(sql);
			if(effect>0)
			{
				String json = "{\"result\":\"true\"}";
				json= StringUtil.Encode(json);
			    response.getWriter().append(json);
			   
			}
			else//
			{
				String json = "{\"result\":\"error\",\"errormsg\":\"Unknow error!\"}";
				json= StringUtil.Encode(json);
			    response.getWriter().append(json);
			    
			}
			;			DBUtil.closeStatement(st);
			DBUtil.closeConection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
