package service;

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
     * ҽ���¼
     * @param name
     * @param password(δ����)
     * @return  0  ��¼ʧ��   
     *          1  ҽ���¼�ɹ�
     * @throws AppException
     */
    public int login(String name, String password, String token) 
    {
       	String encode = MD5Util.MD5(password);
		int id = userDao.login(name, encode, token);
	
        return id;
    }
    

   /**
    * 
    * @Title: register 
    * @Description: 病人注册
    * @param username
    * @param password(δ����)
    * @return String
    */
    public String register(String username, String password)
    {
    	String encode = MD5Util.MD5(password);
    	String message = userDao.register(username, encode);
	
    	return message;
    }
    
    /**
     * 
     * @Title: adLogin 
     * @Description: 病人登录
     * @param username
     * @param password(δ����)
     * @return String
     */
    public int adLogin(String username, String password, String token)
    {
      	String encode = MD5Util.MD5(password);
		int id = userDao.adLogin(username, encode, token);
		
		return id;
    }
    
    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    public boolean isExist(String username){
    	  	
        boolean result = userDao.isExist(username);
		
		return result;
    }
    
    /**
     * 根据token 获取用户名
     * @param token
     * @return
     */
    public String getUsername(String token){
    	String username = userDao.getUsername(token);
    	return username;
    }

}
