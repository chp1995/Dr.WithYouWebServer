package dao;

import java.util.ArrayList;

import util.AppException;

import model.Templet;

public interface TempletDao {
	
	// ��ȡҽ������������ģ��
	public ArrayList<Templet> getTempletList(String username) throws AppException;

}
