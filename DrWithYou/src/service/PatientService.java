package service;

import java.util.ArrayList;

import dao.ChecklistDao;
import dao.PatientDao;
import dao.TempletDao;
import dao.UserDao;
import impl.ChecklistDaoImpl;
import impl.PatientDaoImpl;
import impl.TempletDaoImpl;
import impl.UserDaoImpl;
import model.Patient;

public class PatientService {
	
	private PatientDao patientDao;
	private UserDao userDao;
	private ChecklistDao checklistDao;
	private TempletDao templetDao;
	
	public PatientService(){
		patientDao = new PatientDaoImpl();
		userDao = new UserDaoImpl();
		checklistDao = new ChecklistDaoImpl();
		templetDao = new TempletDaoImpl();
	}
	
	/**
	 * 获取所有病人信息列表
	 * @return
	 */
	public ArrayList<Patient> getNonAddPatient(String username){
		ArrayList<Patient> list = patientDao.getAllPatients(); // 所有病人
		ArrayList<Patient> mylist = patientDao.getDocPatients(username);
		
		ArrayList<Patient> result = new ArrayList<Patient>();
		for(Patient temp: list){
			boolean flag = false;
			for(Patient compare: mylist){
				if(temp.getPatient_name().equals(compare.getPatient_name())){
					flag = true;
					break;
				}		
			}	
			
			if(!flag)
				result.add(temp);
		}
	           
		return result;
	}
	
	/**
	 * 添加医患关系
	 * @param token
	 * @param patientname
	 * @return
	 */
	public boolean addPatient(String token, String patientname){
		
		boolean result = false;
		
		// 根据token 获取医生用户名
		String doctorusr = userDao.getUsername(token);
		if(doctorusr != "")
			result = patientDao.addPatient(doctorusr, patientname);
		return result;
		
	}
	
	/**
	 * 获取医生的所有患者
	 * @param username 医生用户名
	 * @return
	 */
	public ArrayList<Patient> getDoctorPatients(String username){
		
		ArrayList<Patient> list = patientDao.getDocPatients(username);		
		return list;
	}
	
	/**
	 * 删除医生和患者的对应
	 * @param doctorusr
	 * @param patientusr
	 * @return
	 */
	public String deleteDoctorPatient(String doctorusr, String patientusr){
		String message = "";
		

		// 1. 删除病人对应检查项
		// 2. 删除病人对应模板
		// 3. 删除病人和患者对应
		
		
		checklistDao.deletePatientChecklist(patientusr);
		templetDao.deletePatientTemplet(patientusr);
		
		int result = patientDao.deleteDocPatient(doctorusr, patientusr);
		if(result == 1)
			message = "删除成功！";
		else
			message = "删除失败！";
		
		return message;
	}
	
	

}
