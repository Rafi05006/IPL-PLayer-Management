package fxml;

import java.io.Serializable;

public class addPlayerDTO implements Serializable {
    Player newplayer;

    public Player getNewplayer() {
        return newplayer;
    }

    public void setNewplayer(Player newplayer) {
        this.newplayer = newplayer;
    }


}
