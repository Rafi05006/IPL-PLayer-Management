package fxml;

import java.io.Serializable;

public class dataDTO implements Serializable {
    Player player;
    String s;
    Boolean transferStatus;

    public dataDTO(Player player, String s, Boolean transferStatus) {
        this.player = player;
        this.s = s;
        this.transferStatus=transferStatus;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Boolean getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(Boolean transferStatus) {
        this.transferStatus = transferStatus;
    }
}
