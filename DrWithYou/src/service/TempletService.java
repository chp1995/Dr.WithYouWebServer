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
	 * ��ȡҽ�����������м����
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
