package fxml;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.io.IOException;

import static java.lang.System.exit;

public class HomeController {


    private Main main;

    public void showMainMenu(ActionEvent actionEvent) throws Exception {
        main.showMainMenu();
    }

    public void showLoginPage(ActionEvent actionEvent) throws Exception {
        boolean b=true;
        try{
            main.connectToServer();
        }

        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Server is Down");
            alert.setContentText("Please Wait and try again after Sometimes");
            alert.showAndWait();
            main.showHome();
            b=false;
        }
        if(b){
            main.showLoginPage();
        }

    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void handleExit(ActionEvent actionEvent) throws IOException {

        exit(1);
    }
}
