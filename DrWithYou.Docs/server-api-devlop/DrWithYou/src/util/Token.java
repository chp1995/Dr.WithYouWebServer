package util;

import java.util.UUID;

/**   
 * @Title: Token.java
 * @Description:  
 * @author guwenyu
 * @date 2017-4-19 下午04:53:14
 * @version V1.0   
 */

public class Token {
	
	/**
	 * 
	 * @Title: getToken 
	 * @Description: 生成大写的GUID作为Token
	 * @return String
	 */
	public static String getToken(){
		UUID uuid  =  UUID.randomUUID(); 
		String s = UUID.randomUUID().toString();
		return s.toUpperCase();
	}

}
