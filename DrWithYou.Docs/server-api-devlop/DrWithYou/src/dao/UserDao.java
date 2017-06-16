package dao;

import util.AppException;

public interface UserDao {
	
	// 医生登陆
    public int login(String name, String password, String token) throws AppException;

    // 病人注册
    public String register(String username, String password) throws AppException;
    
    // 病人登录
    public int adLogin(String name, String password, String token) throws AppException;
    
    // 判断用户名是否已存在
    public boolean isExist(String username) throws AppException;

}
