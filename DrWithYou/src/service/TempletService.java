/**
 * 
 */
package service;

import java.util.ArrayList;
import java.util.HashMap;

import model.Patient;
import model.Templet;

import impl.TempletDaoImpl;
import dao.TempletDao;

/**
 * @author gwy
 *
 */
public class TempletService {
	
	private TempletDao templetDao;
	
	public TempletService(){
		templetDao = new TempletDaoImpl();
	}
	
	/**
	 * 获取该医生创建的所有模板
	 * @param username
	 * @return
	 */
	public ArrayList<Templet> getTempletList(String username){
		
		ArrayList<Templet> list = templetDao.getTempletList(username);
			
		return list;
	}
	
	/**
	 * 删除模板
	 * @param tid
	 * @return
	 */
	public String deleteTemplet(int tid){
		
		String message = "";
		
		int result = templetDao.deleteTemplet(tid);
		if(result != 0)
			message = "删除成功！";
		else
			message = "删除失败！";
		
		return message;
	}
	
	/**
	 * 新建模板
	 * @param username
	 * @param templetname
	 * @param suitable
	 * @param description
	 * @return
	 */
	public String addTemplet(String username, String templetname, String suitable, String description){
		
		String message = "";
		
		int result = templetDao.addTemplet(username, templetname, suitable, description);
		if(result != 0)
			message = "增加成功！";
		else
			message = "增加失败！";
		
		return message;
	}
	
	/**
	 * 获取模板详细信息
	 * @param tid
	 * @return
	 */
	public Templet getTemplet(int tid){
		Templet templet = templetDao.getTemplet(tid);
		return templet;
	}
	
	/**
	 * 修改模板
	 * @param tid
	 * @param templetname
	 * @param suitable
	 * @param description
	 * @return
	 */
	public String saveTemplet(int tid, String templetname, String suitable, String description){
		String message = "";
		
		int result = templetDao.saveTemplet(tid, templetname, suitable, description);
		if(result != 0)
			message = "修改成功！";
		else
			message = "修改失败！";
		
		return message;
	}
	
	/***
	 * 删除所有模板和检查项关系
	 * @param tid
	 * @return
	 */
	public int deleteTempletCheck(int tid){
		
		int id = templetDao.deleteTempletCheck(tid);
		
		return id;
	}
	
	/**
	 * 添加模板和检查项关系
	 * @param tid
	 * @param cid
	 * @return
	 */
	public int addTempletCheck(int tid, int cid){
		int id = templetDao.addTempletCheck(tid, cid);
		return id;
	}
	
	/**
	 * 获取模板的所有检查项id
	 * @param tid
	 * @return
	 */
	public ArrayList<Integer> getTempletCheck(int tid){
		ArrayList<Integer> list = templetDao.getTempletCheck(tid);
		return list;
	}
	
	/**
	 * 获取用户对应的模板id
	 * @param list
	 * @return
	 */
	public HashMap<String, String> getUserTemplet(ArrayList<Patient> list){
		
		HashMap<String , String> map = new HashMap<String , String>(); 
		
		for(Patient patient: list){
			String username = patient.getUsername();
			int tid = templetDao.getPatientTemplet(username);
			Templet templet = templetDao.getTemplet(tid);
			
			if(tid == -1)
				map.put(username, "null");
			else
				map.put(username, templet.getTemplet_name());
		}
		
		return map;
	}
	
	/**
	 * 删除所有该模板和病人关系
	 * @param tid
	 * @return
	 */
	public int deletePatientTemplet(int tid){
		
		int id = templetDao.deletePatientTemplet(tid);
		
		return id;
	}
	
	/**
	 * 修改病人和模板的对应关系
	 * @param patientusr
	 * @param tid
	 * @return
	 */
	public String editPatientTemplet(String patientusr, int tid){
		
		String message = "";
		
		// 删除病人和之前的模板对应关系
		templetDao.deletePatientTemplet(patientusr);
		int result = templetDao.addPatientTemplet(patientusr, tid);
		System.out.println("add patient templet\t" + patientusr + "\t" + tid + "\t" + result);
		if(result == 1)
			message = "修改成功！";
		else
			message = "修改失败！";
		
		return message;
	}

}
