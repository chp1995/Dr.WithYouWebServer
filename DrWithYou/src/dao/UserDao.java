package dao;


public interface UserDao {
	
	// 医生登陆
    public int login(String name, String password, String token);

    // 病人注册
    public String register(String username, String password);
    
    // 病人登录
    public int adLogin(String name, String password, String token);
    
    // 判断用户名是否存在
    public boolean isExist(String username);
    
    // 根据token 获得用户名
    public String getUsername(String token);

}
