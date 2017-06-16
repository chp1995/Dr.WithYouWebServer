package model;

/**
 * Created by jh on 2017/4/12.
 */
public class User {

    private String username;
    private String password;
    private boolean isDoctor;  // true doctor false patient

    // 患者注册（默认）
    public User(String username, String password){
        this.username = username;
        this.password = password;
        isDoctor = false;
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

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }
}
