package service;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import dbUtil.AppException;

/**
 * Created by jh on 2017/4/12.
 */
public class UserService {

    private UserDao userDao;

    public UserService()
    {
        userDao = new UserDaoImpl();
    }


    /**
     * 用户登录
     * @param name
     * @param password
     * @return -1 登录失败   1 登陆成功
     * @throws AppException
     */
    public int login(String name, String password) throws AppException
    {
        int id = -1;

        try{
            id = userDao.login(name, password);
        }catch(AppException e){
            e.printStackTrace();
            throw new AppException("service/UserService/login");
        }

        return id;
    }
}
