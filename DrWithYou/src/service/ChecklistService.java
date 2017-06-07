/**
 * 
 */
package service;

import java.util.ArrayList;

import model.Checklist;
import util.AppException;
import impl.ChecklistDaoImpl;
import impl.UserDaoImpl;
import dao.ChecklistDao;
import dao.UserDao;

/**
 * @author gwy
 *
 */
public class ChecklistService {

	private ChecklistDao checklistDao;
	private UserDao userDao;
	
	public ChecklistService(){
		checklistDao = new ChecklistDaoImpl();
		userDao = new UserDaoImpl();
	}
	
	/**
	 * 获取该医生创建的所有checklist
	 * @param username
	 * @return
	 */
	public ArrayList<Checklist> getCheckList(String username){
		 
		ArrayList<Checklist> list = checklistDao.getChecklist(username);
			
		return list;
	}
	
	/**
	 * 添加检查项
	 * @param username
	 * @param itemname
	 * @param checktime
	 * @param description
	 * @return
	 */
	public String addChecklist(String username, String itemname, String checktime, String description){
		String message = "";
		
		int result = checklistDao.addChecklist(username, itemname, checktime, description);
		if(result > 0)
			message = "添加成功";
		else
			message = "添加失败";
		
		return message;
		
	}
	
	/**
	 * 获取检查项细则
	 * @param cid
	 * @return
	 */
	public Checklist getChecklist(int cid){
		Checklist check = checklistDao.getCheckitem(cid);

		return check;
	}
	
	/**
	 * 修改检查项
	 * @param cid
	 * @param itemname
	 * @param checktime
	 * @param description
	 * @return
	 */
	public String saveChecklist(int cid, String itemname, String checktime, String description){
		
		String message = "";
		
		int result = checklistDao.saveCheckitem(cid, itemname, checktime, description);
		if(result != 0)
			message = "修改成功！";
		else
			message = "修改失败！";
		
		return message;
	}
	
	/**
	 * 删除检查项
	 * @param cid
	 * @return
	 */
	public String deleteCheckitem(int cid){
		
		// 删除检查项
		// 删除模板和检查项
		// 删除病人和检查项
		// 删除检查项
		
		String message = "";
		
		checklistDao.deletePatientChecklist(cid);
		checklistDao.deleteTempletChecklist(cid);	
		
		int result = checklistDao.deleteCheckitem(cid);
		if(result != 0)
			message = "删除成功！";
		else
			message = "删除失败！";
		
		return message;
		
	}
	
	/**
	 * 获取病人所有检查项id
	 * @param username
	 * @return
	 */
	public ArrayList<Integer> getPatientChecklist(String username){
		
		ArrayList<Integer> list = checklistDao.getPatientChecklist(username);
		return list;
	}
	
	/**
	 * 删除病人对应所有检查项id
	 * @param username
	 * @return
	 */
	public int deletePatientChecklist(String username){
		int id = checklistDao.deletePatientChecklist(username);
		return id;
	}
	
	
	/**
	 * 添加病人和检查项的联系
	 * @param username
	 * @param cid
	 * @return
	 */
	public int addPatientChecklist(String username, int cid){
		int result = checklistDao.addPatientChecklist(username, cid);
		return result;
	}
}
