package dao;

import util.AppException;

public interface UserDao {
	
	/**
	 * ҽ����¼
	 * @param name
	 * @param password(�Ѽ���)
	 * @return
	 */
    public int login(String name, String password, String token) throws AppException;

    /**
     * 
     * @Title: register 
     * @Description: TODO
     * @param username
     * @param password(�Ѽ���)
     * @param @return
     * @return String
     */
    public String register(String username, String password) throws AppException;
    
    public int adLogin(String name, String password, String token) throws AppException;
}
