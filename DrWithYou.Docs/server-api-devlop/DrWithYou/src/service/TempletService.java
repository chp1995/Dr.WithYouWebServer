/**
 * 
 */
package service;

import java.util.ArrayList;

import util.AppException;

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
	 * 获取医生创建的所有检查项
	 * @param username
	 * @return
	 */
	public ArrayList<Templet> getTempletList(String username){
		
		ArrayList<Templet> list = new ArrayList<Templet>();
		
		try {
			list = templetDao.getTempletList(username);
		} catch (AppException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
