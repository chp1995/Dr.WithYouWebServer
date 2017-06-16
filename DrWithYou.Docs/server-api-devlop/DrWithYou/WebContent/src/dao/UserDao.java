package dao;

import dbUtil.AppException;

/**
 * Created by jh on 2017/4/12.
 */
public interface UserDao {

    // 登录
    public int login(String name, String password) throws AppException;
}
