package fxml;

import java.io.Serializable;

public class SignupDTO implements Serializable {
    private String userName;
    private String password;
    private String cpass;
    private boolean allow=false;

    public SignupDTO(String userName, String password, String cpass) {
        this.userName = userName;
        this.password = password;
        this.cpass = cpass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpass() {
        return cpass;
    }

    public void setCpass(String cpass) {
        this.cpass = cpass;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }
}
