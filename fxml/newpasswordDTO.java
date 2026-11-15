package fxml;

import java.io.Serializable;

public class newpasswordDTO implements Serializable {
    private String clubname;

    private String oldpass;
    private String newpass;
    private String conpass;
    private boolean passchange;
    public boolean isPasschange() {
        return passchange;
    }

    public void setPasschange(boolean passchange) {
        this.passchange = passchange;
    }



    public String getClubname() {
        return clubname;
    }

    public void setClubname(String clubname) {
        this.clubname = clubname;
    }

    public String getOldpass() {
        return oldpass;
    }

    public void setOldpass(String oldpass) {
        this.oldpass = oldpass;
    }

    public String getNewpass() {
        return newpass;
    }

    public void setNewpass(String newpass) {
        this.newpass = newpass;
    }

    public String getConpass() {
        return conpass;
    }

    public void setConpass(String conpass) {
        this.conpass = conpass;
    }
}
