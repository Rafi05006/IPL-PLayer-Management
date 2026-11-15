package fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class searchbyPositionController {


    private Main main;
    @FXML
    public TextField positionName;
    private ArrayList<Player> playerList;

    public void startSearchingbyposition(ActionEvent actionEvent) throws Exception {
        String position=positionName.getText();
        ArrayList<Player>searchList=SearchPlayersandclubs.searchPosition(playerList,position);
        main.showPlayer(searchList);
    }

    public void setMain(Main main) {
        this.main = main;
        playerList=main.getplayerList();
    }

    public void returntoMainMenu(ActionEvent actionEvent) throws Exception {
        main.showMainMenu();
    }


}
