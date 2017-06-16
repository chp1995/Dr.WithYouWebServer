package service;

import util.AppException;
import util.MD5Util;
import impl.UserDaoImpl;
import dao.UserDao;

public class UserService {
	
	private UserDao userDao;

    public UserService()
    {
        userDao = new UserDaoImpl();
    }

    /**
     * 医生登录
     * @param name
     * @param password(未加密)
     * @return  0  登录失败   
     *          1  医生登录成功
     * @throws AppException
     */
    public int login(String name, String password, String token) 
    {
        int id = 0;
        
        try {
        	String encode = MD5Util.MD5(password);
			id = userDao.login(name, encode, token);
		} catch (AppException e) {
			e.printStackTrace();
		}

        return id;
    }
    

   /**
    * 
    * @Title: register 
    * @Description: 患者注册
    * @param username
    * @param password(未加密)
    * @return String
    */
    public String register(String username, String password)
    {
    	String message = "";
    	
    	try {
    		String encode = MD5Util.MD5(password);
			message = userDao.register(username, encode);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return message;
    }
    
    /**
     * 
     * @Title: adLogin 
     * @Description: 患者登录
     * @param username
     * @param password(未加密)
     * @return String
     */
    public int adLogin(String username, String password, String token)
    {
    	int id = 0;
    	
    	try {
        	String encode = MD5Util.MD5(password);
			id = userDao.adLogin(username, encode, token);
		} catch (AppException e) {
			e.printStackTrace();
		}
		
		return id;
    }
    
    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    public boolean isExist(String username){
    	boolean result = true;
    	
    	try {
			result = userDao.isExist(username);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
    }

}
