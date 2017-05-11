package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtil {
	
	private static String url = "jdbc:mysql://127.0.0.1:3306/doctor?userUnicode=true&amp;"
        + "characterEncoding=utf8";
	private static String username = "root";
	private static String password = "xiannvzier";
	
	static{
	    // ������
	    try {
	        Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC�����
	        //Class.forName("org.gjt.mm.mysql.Driver");
	        System.out.println("Success loading Mysql Driver!");
	    }
	    catch (Exception e) {
	        System.err.print("Error loading Mysql Driver!");
	        e.printStackTrace();
	    }
	}
	
	public static Connection getConnection()
	{
	    Connection conn = null;
	
	    // ��������
	    try {
	        conn = DriverManager.getConnection(url, username, password);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } // ������ݿ�����
	
	    return conn;
	}
	
	// �ر�����
	public static void closeConection(Connection conn)
	{
	    try {
	        if(conn != null && !conn.isClosed())
	        {
	            conn.close();
	            conn = null;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void closeStatement(Statement st)
	{
	    try {
	        if(st != null && !st.isClosed())
	        {
	            st.close();
	            st = null;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void closeResultSet(ResultSet set)
	{
	    try {
	        if(set != null && !set.isClosed())
	        {
	            set.close();
	            set = null;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void main(String[] args)
	{
	    Connection conn = getConnection();
	    if(conn != null)
	        System.out.println("Is not null");
	
	}

}
