package com.drwithyou.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.drwithyou.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Servlet implementation class adicon
 */
@WebServlet("/adicon")
public class adicon extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final String UPLOAD_PATH = "C:\\DiskUpload\\";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adicon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//
		String token = request.getParameter("token");
		String username = Token.checkToken(token);
		
		if(token==null)
		{
			response.setStatus(404);
			return;
		}
		
		//获取path
		String pathSql = "select Iconpath from UserInfo where username ='"+username+"'";
		Connection con = DBUtil.getConnection();
		PreparedStatement st;
		try {
			st = con.prepareStatement(pathSql);
			ResultSet rs = st.executeQuery();
			if(!rs.next())
			{
				response.setStatus(404);
				DBUtil.closeResultSet(rs);
				DBUtil.closeStatement(st);
				DBUtil.closeConection(con);
				return;
			}
			String path = UPLOAD_PATH +rs.getString(1);
			File iconFile = new File(path);
			if(!iconFile.exists())
			{
				response.setStatus(404);
				DBUtil.closeResultSet(rs);
				DBUtil.closeStatement(st);
				DBUtil.closeConection(con);
				return;
			}
			
			//开始读取写入
			response.setContentType("image/*");
			InputStream is =new FileInputStream(iconFile);
			OutputStream os = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len =0;
			while((len=is.read(buffer))!=-1)
			{
				os.write(buffer, 0, len);
			}
			is.close();
			os.close();
			
			DBUtil.closeResultSet(rs);
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
		
		/**
		 * 仅支持单文件上传
		 */
		request.setCharacterEncoding("utf-8");////这句至关重要，不然中文的文件名称显示乱码 
		DiskFileItemFactory factory = new DiskFileItemFactory();  
		String uploadPath= adicon.UPLOAD_PATH;
		File pathFile = new File(uploadPath);
		if(!pathFile.exists())
			pathFile.mkdirs();
		
		factory.setSizeThreshold(1024 * 1024 * 500); 
		factory.setRepository(new File(uploadPath));  
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		
	    try {
		Map<String,List<FileItem>> map = servletFileUpload.parseParameterMap(request);
	
		List<FileItem> fileList= map.get("file");
		List<FileItem> tokenList= map.get("token");
		if(fileList == null || tokenList == null)
		{
			
			response.setStatus(500);
			return;
		}
		//获取token
		String token = tokenList.get(0).getString();
		if(token == null)
		{
			response.setStatus(500);
			
			return;
		}
		//check token .get username
		String username = Token.checkToken(token);
		if(username==null)
		{
			response.setStatus(500);
			
			return;
		}
		String orgPathSql = "select Iconpath from UserInfo where username ='"+username+"'";
		Connection con = DBUtil.getConnection();
		PreparedStatement st =  con.prepareStatement(orgPathSql);
		ResultSet rs = st.executeQuery();
		if(!rs.next())
		{
			response.setStatus(500);
			
			return;
		}
		//获取
		String orgPath = rs.getString(1);
		//完成 关闭之
		DBUtil.closeResultSet(rs);
		DBUtil.closeStatement(st);
		DBUtil.closeConection(con);
		
		File orgIconFile = new File(UPLOAD_PATH+orgPath);
		
		//若存在 删除之
		if(orgIconFile.exists())
			orgIconFile.delete();
		
        FileItem fileItem =  fileList.get(0);
        InputStream is = fileItem.getInputStream();  
        
        //生成一个GUID名字作为该文件的文件名
        UUID uuid = UUID.randomUUID();
        String saveName = uploadPath+uuid.toString();
        FileOutputStream output = new FileOutputStream(  new File(saveName)); 
        byte[] buf = new byte[102400]; 
        int length = 0;  
        while ((length = is.read(buf)) != -1) {  
                output.write(buf, 0, length);  
        }  
        is.close();
        output.close();
        
        //保存新路径到数据库
        con= DBUtil.getConnection();
        String saveSql = "update UserInfo set Iconpath = '"+uuid.toString()+"' where username='"+username+"'";
        st = con.prepareStatement(saveSql);
        if(st.executeUpdate()!=1)
        {
        	response.setStatus(500);
        	
			return;
        }
       //完成 关闭之
      	DBUtil.closeResultSet(rs);
      	DBUtil.closeStatement(st);
      	DBUtil.closeConection(con);
        
	} catch (FileUploadException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  

		
	}

}
