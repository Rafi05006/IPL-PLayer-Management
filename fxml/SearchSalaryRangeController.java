package fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.util.ArrayList;

public class SearchSalaryRangeController {
    private Main main;
    @FXML
    public TextField highestSalary;
    @FXML
    public TextField lowestSalary;
    private ArrayList<Player> playerList;
    public void setMain(Main main) {
        this.main=main;
        playerList=main.getplayerList();
    }

    public void pressingButton(ActionEvent actionEvent) throws Exception {
        String h= highestSalary.getText();
        int highSalary=Integer.parseInt(h);
        String l= lowestSalary.getText();
        int lowSalary=Integer.parseInt(l);

        ArrayList<Player> searchList=SearchPlayersandclubs.searchSalary(playerList,lowSalary,highSalary);
        main.showPlayer(searchList);

    }

    public void returntoMainMenu(ActionEvent actionEvent) throws Exception {
        main.showMainMenu();
    }
}
