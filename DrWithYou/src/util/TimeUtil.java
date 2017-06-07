package util;



import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	 public static  String dateToStamp(String s) {
	        String res;
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date date = null;
			try {
				date = simpleDateFormat.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        long ts = date.getTime();
	        res = String.valueOf(ts);
	        return res;
	 }
	 
	 public static String stampToDate(String s){
	        String res;
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        long lt = new Long(s);
	        Date date = new Date(lt);
	        res = simpleDateFormat.format(date);
	        return res;
	 }
	 
	 
	 public static String getNowDateString()
	 {
		 
		 Date date = new Date();   
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String dateStr = sdf.format(date);
		  
		 return dateStr;
		 
	 }
	 
	 public static Date getDate(String dateStr)
	 {
		 Date date = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return date;
	 }
	 
	 public static void main(String[] args)
	 {
//		 Timestamp ts = new Timestamp(System.currentTimeMillis()); 
//		 String tsStr = "";
//		 DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		 try {
//				
//				tsStr = ts.toString();
//				System.out.println(tsStr);
//		 } catch (Exception e) {
//				e.printStackTrace();
//		 }
		 
		  Date date = new Date();   
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String dateStr = "";
		  dateStr = sdf.format(date);
		  System.out.println(dateStr);
		  
		  
		  String stampstr = dateToStamp(dateStr);
		  System.out.println(stampstr);

		  System.out.println(stampToDate(stampstr));
		  
		  String s1 = "2016-04-03 10:24:15";
		  String s2 = "2016-04-10 10:24:15";
		  System.out.println(dateToStamp(s1));
		  System.out.println(dateToStamp(s2));
	 }
	 
}
