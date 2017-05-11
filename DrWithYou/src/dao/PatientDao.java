package dao;

import java.util.ArrayList;

import model.Patient;

public interface PatientDao {
	
	// 获取所有病人信息列表
	public ArrayList<Patient> getAllPatients();
	
	// 添加病人
	public boolean addPatient(String doctorusr, String patientusr);

	// 获取该医生的所有病人
	public ArrayList<Patient> getDocPatients(String username);
	
	// 删除医生和病人的关系对应
	public int deleteDocPatient(String doctorusr, String patientusr);
}
