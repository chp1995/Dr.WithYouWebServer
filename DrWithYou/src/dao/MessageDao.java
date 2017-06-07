package dao;

import java.util.ArrayList;

import model.Message;

public interface MessageDao {
	
	// 获取用户一与用户二的对话（用户一方）
	public ArrayList<Message> getMessage(String docusr, String pausr);
	
	// 发送消息（用户一给用户二）
	public int sendMessage(String sender, String receiver, String time, String message);
	
}
