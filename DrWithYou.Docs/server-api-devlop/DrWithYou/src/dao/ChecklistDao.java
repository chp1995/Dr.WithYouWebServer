package dao;

import java.util.ArrayList;

import util.AppException;

import model.Checklist;

public interface ChecklistDao {
	
	// ��ȡҽ�������ļ����
	public ArrayList<Checklist> getChecklist(String username) throws AppException;

}
