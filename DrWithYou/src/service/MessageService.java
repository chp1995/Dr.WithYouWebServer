package service;

import java.util.ArrayList;
import java.util.Date;

import dao.MessageDao;
import impl.MessageDaoImpl;
import model.Message;
import util.TimeUtil;

public class MessageService {
	
	private MessageDao messageDao;
	
	public MessageService()
	{
		messageDao = new MessageDaoImpl();
	}
	
	
	/**
	 * 发送消息
	 * @param sender
	 * @param receiver
	 * @param message
	 * @return
	 */
	public int sendMessage(String sender, String receiver, String message)
	{
		String time = TimeUtil.dateToStamp(TimeUtil.getNowDateString());
		int result = messageDao.sendMessage(sender, receiver, time, message);
		return result;
	}
	
	public ArrayList<Message> getMessages(String docusr, String pausr)
	{
		
		ArrayList<Message> list1 = messageDao.getMessage(docusr, pausr);
		ArrayList<Message> list2 = messageDao.getMessage(pausr, docusr);
		
		ArrayList<Message> result = new ArrayList<Message> ();
		
		// 按时间顺序汇总数组
		int flag1 = 0, flag2 = 0;
		while(flag1 < list1.size() && flag2 < list2.size())
		{
			Message temp1 = list1.get(flag1);
			Message temp2 = list2.get(flag2);
			
			Date date1 = TimeUtil.getDate(temp1.getTime());
			Date date2 = TimeUtil.getDate(temp2.getTime());
			
			if(date1.before(date2))
			{
				result.add(temp1);
				flag1 ++;
			}
			else
			{
				result.add(temp2);
				flag2 ++;
			}
		}
		
		if(flag1 == list1.size())        // 拷贝list2剩下的至result
		{
			for(; flag2 < list2.size(); flag2 ++)
				result.add(list2.get(flag2));
		}
		else if(flag2 == list2.size())   // 拷贝list1剩下的至result
		{
			for(; flag1 < list1.size(); flag1 ++)
				result.add(list1.get(flag1));
		}
		
		return result;
	}

}
