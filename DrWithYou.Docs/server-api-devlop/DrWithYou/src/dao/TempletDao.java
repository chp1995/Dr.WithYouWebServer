package dao;

import java.util.ArrayList;

import util.AppException;

import model.Templet;

public interface TempletDao {
	
	// 获取医生创建的所有模板
	public ArrayList<Templet> getTempletList(String username) throws AppException;

}
