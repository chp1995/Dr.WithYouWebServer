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
 * Servlet implementation class adcheckeditems
 */
@WebServlet("/adcheckeditems")
public class adcheckeditems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adcheckeditems() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String token = request.getParameter("token");
		String filterID = request.getParameter("cid");
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
			Statement st = con.createStatement();
			String querySql = "select id,checkitem.itemname,checkitem.checktime,checkeditem.submittime,checkeditem.value from checkitem,checkeditem where checkeditem.username = '"+usr+"' and checkeditem.id = checkitem.cid";
						ResultSet rs = st.executeQuery(querySql);
			String json = "{\"result\":\"true\",\"set\":[";
			int n=0;
			while(rs.next())
			{
				String subjson = "{"
						+ "\"id\":"+rs.getString(1)+"\","
						+"\"itemname\":\""+rs.getString(2)+"\","
						+"\"checktime\":\""+rs.getString(3)+"\","
						+"\"submittime\":\""+rs.getString(4)+"\","
						+"\"value\":\""+rs.getString(5)+"\"},";
				if(filterID!= null)
				{
					if(rs.getString(1).equals(filterID))
					{
						json += subjson;
						n++;
					}
				}
				else
				{
					json += subjson;
					n++;
				}
				
				

			}
			if(n>0)
				 json = json.substring(0, json.length()-1);//去掉逗号
			json+="]}";
			System.out.println(json);

			json = StringUtil.Encode(json);
			response.getWriter().append(json);
			return;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String json = "{\"result\":\"error\",\"errormsg\":\"Toeken error!\"}";
		json= StringUtil.Encode(json);
	    response.getWriter().append(json);
	}

}
