package fxml;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainMenuController {
    @FXML
    public Menu addPlayerMenu;

    private  Main main;
    private Stage stage;
    private ArrayList<Player> playerList;
    Boolean bool;

    public void enterSearchPlayer(ActionEvent actionEvent) throws Exception {
       main.showSearchPlayerbyName();
    }
    public void searchByclubandcountry(ActionEvent actionEvent) throws Exception {
        main.showSearchClubandCountry();
    }

    public void searchByposition(ActionEvent actionEvent) throws Exception {
            main.showSearchPlayerbyPosition();
    }

    public void searchBysalaryrange(ActionEvent actionEvent)throws Exception {
        main.showSearchSalaryRange();
    }


    public void PressingSalary(ActionEvent actionEvent) throws Exception {
        main.clubMaxSalary();
    }

    public void PressingAge(ActionEvent actionEvent) throws Exception {
        main.clubMaxAge();
    }

    public void PressingHeight(ActionEvent actionEvent) throws Exception{
        main.clubMaxHeight();
    }


    public void addingPlayer(ActionEvent actionEvent)throws Exception {
        if(main.getBooladd()){
            main.addNewPlayer();
        }

    }

    public void exiting(ActionEvent actionEvent) {
    }
    public void setMain(Main main) {
        this.main=main;
        if (!main.getBooladd()) {
            addPlayerMenu.setVisible(false); // Hide the menu
        } else {
            addPlayerMenu.setVisible(true); // Show the menu
        }
    }

    public void searchCountrywise(ActionEvent actionEvent) throws Exception {
        main.showCountry();
    }

    public void PressingTotalSalary(ActionEvent actionEvent) throws Exception {
        main.showTotalClubSalary();
    }




    public void returnToHome(ActionEvent actionEvent) throws Exception {
        if(main.getBooladd()){
            main.showClubManager();
        }
        else{
            main.showHome();
        }
    }


}
