package fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class searchbyNameController {
    private Main main;
    @FXML
    public TextField playerName;
    @FXML
    public Button searchButton;
    ArrayList<Player> playerList;


    public void startSearchingbyname(ActionEvent actionEvent) throws Exception {
        String name=playerName.getText();

        ArrayList<Player>searchList=SearchPlayersandclubs.searchName(playerList,name);
        main.showPlayer(searchList);
        
    }
    public void setMain(Main main) {
        this.main=main;
        playerList=main.getplayerList();
    }

    public void returntoMainMenu(ActionEvent actionEvent) throws Exception {
        main.showMainMenu();
    }
}
