/**
 * 
 */
package service;

import java.util.ArrayList;

import model.Checklist;
import util.AppException;
import impl.ChecklistDaoImpl;
import dao.ChecklistDao;

/**
 * @author gwy
 *
 */
public class ChecklistService {

private ChecklistDao checklistDao;
	
	public ChecklistService(){
		checklistDao = new ChecklistDaoImpl();
	}
	
	/**
	 * 医生获得所有创建的检查项
	 * @param username
	 * @return
	 */
	public ArrayList<Checklist> getCheckList(String username){
		
		ArrayList<Checklist> list = new ArrayList<Checklist>();
		
		try {
			list = checklistDao.getChecklist(username);
		} catch (AppException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
