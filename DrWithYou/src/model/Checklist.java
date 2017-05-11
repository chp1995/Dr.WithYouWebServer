package model;

/**
 * �����
 * @author jh
 */
public class Checklist {
	
	private int cid;
	private String checklist_name;
	private String checktime;
	private String creator;   // username
	private String description;
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getChecklist_name() {
		return checklist_name;
	}
	
	public void setChecklist_name(String checklist_name) {
		this.checklist_name = checklist_name;
	}
	
	public String getChecktime() {
		return checktime;
	}
	
	public void setChecktime(String checktime) {
		this.checktime = checktime;
	}
	
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
