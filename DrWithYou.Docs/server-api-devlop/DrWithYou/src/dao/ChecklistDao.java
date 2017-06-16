package dao;

import java.util.ArrayList;

import util.AppException;

import model.Checklist;

public interface ChecklistDao {
	
	// 获取医生创建的检查项
	public ArrayList<Checklist> getChecklist(String username) throws AppException;

}
