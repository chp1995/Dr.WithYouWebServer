package com.drwithyou.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import com.drwithyou.util.TimeUtil;
import com.drwithyou.util.Token;

/**
 * Servlet implementation class adchecklist
 */
@WebServlet("/adchecklist")
public class adchecklist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adchecklist() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    //是否比今天00.00晚
    private boolean timstampLeagle(String submitStamp)
    {
    	 Date today = new Date();
 	    int year = today.getYear()+1900;
 	    
 	    int mon = today.getMonth()+1;
 	    int day = today.getDate();
 	   
 	    String d = year+"-"+mon+"-"+day+"-00:00:00";
 	    String stamp = TimeUtil.dateToStamp(d);
 	    Long  td = new Long(stamp);
 	    Long  sub = new Long(submitStamp);
 	   // System.out.println(""+td+"d"+sub+"#"+d );
 	    return (sub < td);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		//获取数据库连接
		Connection con = DBUtil.getConnection();
		//
	   
		try {
			//创建statement
			//拼接sql
			String querySql = "select CheckItem.cid,CheckItem.Itemname,CheckItem.Checktime,CheckItem.description from CheckItem,UserCheckItem where CheckItem.cid = UserCheckItem.id and UserCheckItem.Username = '"+usr+"'";
			PreparedStatement p1st = con.prepareStatement(querySql);
			ResultSet checklist = p1st.executeQuery();
			String json = "{\"result\":\"true\",\"set\":[";
			int n=0;
			while(checklist.next())
			{
				boolean flag = true;
				String cid = checklist.getString(1);
			    String query = "select submittime from checkeditem where username = '"+usr+"' and id = '"+cid+"'";
			   // ResultSet checkedlist = st.executeQuery(query);
			    PreparedStatement p2st = con.prepareStatement(query);
			    ResultSet checkedlist = p2st.executeQuery();
			    while(checkedlist.next())
			    {
			    	String submittime = checkedlist.getString(1);
			    	if(!timstampLeagle(submittime))
			    		flag = false;
			    }
			    DBUtil.closeResultSet(checkedlist);
			    if(flag == true)
			    {
			    	String subjson = "{\"cid\":\""+cid+"\",\"itemname\":\""+checklist.getString(2)+"\",\"checktime\":\""+checklist.getString(3)+"\",\"Description\":\""+checklist.getString(4)+"\"},";
			        json+=subjson;
			        n++;
			    }
			    DBUtil.closeStatement(p2st);
			}
			if(n>0)
			 json = json.substring(0, json.length()-1);//去掉逗号
			
			json +="]}";
			//System.out.println(json);
			json = StringUtil.Encode(json);
			response.getWriter().append(json);
			DBUtil.closeStatement(p1st);
			DBUtil.closeResultSet(checklist);
		    DBUtil.closeConection(con);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
