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
	     * ҽ����¼
	     * @param name
	     * @param password(δ����)
	     * @return  0  ��¼ʧ��   
	     *          1  ҽ����¼�ɹ�
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
	    * @Description: ����ע��
	    * @param username
	    * @param password(δ����)
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
	     * @Description: ���ߵ�¼
	     * @param username
	     * @param password(δ����)
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

}
