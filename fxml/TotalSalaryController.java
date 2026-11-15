package fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.ArrayList;

public class TotalSalaryController {

    private Main main;

    @FXML
    private TextField clubNameField;

    @FXML
    private Label totalSalaryLabel;

    private ArrayList<Player> playerList;

    @FXML
    private void handleCalculateSalary() {
        try {
            String clubName = clubNameField.getText();

            if (clubName.isEmpty()) {

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a club name.");
                alert.showAndWait();
                return;
            }

            int totalSalary = SearchPlayersandclubs.totalSalary(playerList, clubName);

            totalSalaryLabel.setText(String.valueOf(totalSalary));

        } catch (Exception e) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while calculating the total salary.");
            alert.showAndWait();
        }
    }

    public void setMain(Main main) {
        this.main=main;
        playerList=main.getplayerList();
    }

    public void returnToMainMenu(ActionEvent actionEvent) throws Exception {
        main.showMainMenu();
    }
}
