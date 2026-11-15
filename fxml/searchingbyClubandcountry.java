package fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.ArrayList;

public class searchingbyClubandcountry {


    private Main main;
    @FXML
    public TextField countryname;
    @FXML
    public TextField clubname;
    @FXML
    public Button searchButton;
    private ArrayList<Player> playerList;

    public void startsearchingbyClubandCountry(ActionEvent actionEvent) throws Exception {
        String country=countryname.getText();
        String club=clubname.getText();


        ArrayList<Player> searchList=SearchPlayersandclubs.searchCountryandclub(playerList,country,club);
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
