package model;

public class Templet {
	
	private int tid;
	
	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	private String templet_name;
	private String creator;         // username
	private String suitable;        // 适用症֢
	private String description;     // 描述
	
	public String getTemplet_name() {
		return templet_name;
	}
	
	public void setTemplet_name(String templet_name) {
		this.templet_name = templet_name;
	}
	
	public String getCreator() {
		return creator;
	}
	
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public String getSuitable() {
		return suitable;
	}
	
	public void setSuitable(String suitable) {
		this.suitable = suitable;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
