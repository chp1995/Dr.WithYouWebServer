package model;

public class User {
	private String username;
    private String password;
    private int isDoctor;  // 1 doctor 0 patient
    private String token;
  
    // ����ע�ᣨĬ�ϣ�
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int isDoctor() {
        return isDoctor;
    }

    public void setDoctor(int doctor) {
        isDoctor = doctor;
    }
    
    public void setToken(String token) {
		this.token = token;
	}
    
    public String getToken() {
		return token;
	}

	
}
