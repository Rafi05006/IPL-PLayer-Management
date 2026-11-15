package fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;

public class addPlayerController {

    private  Main main;

    @FXML
    private TextField nameField;

    @FXML
    private TextField countryField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField clubField;

    @FXML
    private TextField positionField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField salaryField;

    private ArrayList<Player> playerList;

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    @FXML
    private void handleAddPlayer() {
        try {

            String name = nameField.getText();
            String country = countryField.getText();
            int age = Integer.parseInt(ageField.getText());
            double height = Double.parseDouble(heightField.getText());
            String club = clubField.getText();
            String position = positionField.getText();
            String number = numberField.getText();
            int salary = Integer.parseInt(salaryField.getText());

            for(int i=0;i<playerList.size();i++){
                if(playerList.get(i).getName().equals(name)){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Failed!");
                    alert.setHeaderText("Failed to add Player");
                    alert.setContentText("Player name already exists!");
                    alert.showAndWait();
                    return;
                }
            }

            Player newPlayer = new Player(name, country, age, height, club, position, number, salary);

            playerList.add(newPlayer);
            addPlayerDTO newpladto=new addPlayerDTO();
            newpladto.setNewplayer(newPlayer);

            main.getSocketWrapper().write(newpladto);
            //FileOperations.AddPlayer(playerList);


            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Player Added");
            alert.setContentText("Player added successfully!");
            alert.showAndWait();


            nameField.clear();
            countryField.clear();
            ageField.clear();
            heightField.clear();
            clubField.clear();
            positionField.clear();
            numberField.clear();
            salaryField.clear();


        } catch (Exception e) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid input. Please check your entries.");
            alert.showAndWait();
        }
    }

    public void setMain(Main main) {
        this.main=main;
        playerList=main.getplayerList();
        clubField.setText(main.user);
    }

    public void returnToMainMenu(ActionEvent actionEvent) throws Exception {
        main.showMainMenu();
    }
}