package dao;

import java.util.ArrayList;

import model.CheckedItem;

public interface CheckedItemDao {
	
	// 获取用户提交数据
	public ArrayList<CheckedItem> getData(String username, int id);

}
