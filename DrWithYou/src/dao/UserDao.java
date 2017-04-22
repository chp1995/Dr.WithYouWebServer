package dao;

import util.AppException;

public interface UserDao {
	
	// ҽ����½
    public int login(String name, String password, String token) throws AppException;

    // ����ע��
    public String register(String username, String password) throws AppException;
    
    // ���˵�¼
    public int adLogin(String name, String password, String token) throws AppException;
    
    // �ж��û����Ƿ��Ѵ���
    public boolean isExist(String username) throws AppException;
}
