package fxml;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ownclubplayerController {

    @FXML
    public ImageView playerImage;
    @FXML
    public ImageView clubImage;
    @FXML
    public ImageView countryImage;
    @FXML
    public Label playerName;
    @FXML
    public Label playerPosition;
    @FXML
    public Label playerClub;

    ArrayList<Player> playerList=new ArrayList<>();

    public TableView<Player> getPlayerTable() {
        return playerTable;
    }

    public void setPlayerTable(TableView<Player> playerTable) {
        this.playerTable = playerTable;
    }

    @FXML
    private TableView<Player> playerTable;

    @FXML
    private TableColumn<Player, String> nameColumn;

    @FXML
    private TableColumn<Player, String> clubColumn;

    @FXML
    private TableColumn<Player, String> positionColumn;

    @FXML
    private TableColumn<Player, Integer> salaryColumn;

    @FXML
    private TableColumn<Player, Boolean> transferStatusColumn;

    @FXML
    private Button sellPlayerButton;

    private Main main;

    @FXML
    public void initialize() {
        // Initialize table columns with Player class properties
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        clubColumn.setCellValueFactory(new PropertyValueFactory<>("club"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        transferStatusColumn.setCellValueFactory(new PropertyValueFactory<>("transfer"));

        sellPlayerButton.disableProperty().bind(playerTable.getSelectionModel().selectedItemProperty().isNull());

        playerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                setPlayerDetails(newSelection);
            }
        });
    }

    public void setMain(Main main) {
        this.main = main;
        loadPlayerData();
    }

    private void loadPlayerData() {

        playerList = main.getplayerList();
        ArrayList<Player> clubPlayers = SearchPlayersandclubs.searchCountryandclub(playerList, "any", main.user);

        playerTable.setItems(FXCollections.observableArrayList(clubPlayers));
        playerTable.refresh();
    }

    public void setPlayerDetails(Player player) {

        playerName.setText(player.getName());
        playerPosition.setText(player.getPosition());
        playerClub.setText(String.valueOf(player.getClub()));

        String playerString="/fxml/playerImages[1]/playerImages/"+player.getName()+".png";
        InputStream pStream= getClass().getResourceAsStream(playerString);
        if(pStream!=null) {
            Image pImage = new Image(pStream);
            playerImage.setImage(pImage);
        }
        else {
            playerString="/fxml/playerImages[1]/playerImages/generic-player.png";
            pStream=getClass().getResourceAsStream(playerString);
            Image pImage=new Image(pStream);
            playerImage.setImage(pImage);
        }

        String clubString="/fxml/playerImages[1]/clubLogos/"+player.getClub().trim()+".png";
        InputStream cStream= getClass().getResourceAsStream(clubString);
        if(cStream!=null) {
            Image cImage = new Image(cStream);
            clubImage.setImage(cImage);
        }
        else {
            clubString="/fxml/playerImages[1]/clubLogos/generic-logo.png";
            cStream=getClass().getResourceAsStream(clubString);
            Image cImage=new Image(cStream);
            clubImage.setImage(cImage);
        }

        String countryString="/fxml/playerImages[1]/flags/"+player.getCountry().trim()+".png";
        InputStream counStream = getClass().getResourceAsStream(countryString);
        if(counStream!=null) {
            Image counImage = new Image(counStream);
            countryImage.setImage(counImage);
        }
        else {
            countryString="/fxml/playerImages[1]/flags/generic-country.png";
            counStream=getClass().getResourceAsStream(countryString);
            Image counImage=new Image(counStream);
            countryImage.setImage(counImage);
        }


    }

    @FXML
    private void handleSellPlayer() {

        Player selectedPlayer = playerTable.getSelectionModel().getSelectedItem();

            try {
                if (!selectedPlayer.isTransfer()) {
                    selectedPlayer.setTransfer(true);
                    dataDTO data=new dataDTO(selectedPlayer,"sell",selectedPlayer.isTransfer());
                    main.getSocketWrapper().write(data);
                    showAlert("Request Sent", "The player " + selectedPlayer.getName() + " is now available for transfer.");

                }
                else{
                    showAlert("Error", "Cannot send sell request multiple times.");
                }
            }catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "Failed to send the sell request.");
            }
        loadPlayerData();
    }
    public void returnToClubManager(ActionEvent actionEvent) throws IOException {
        main.showClubManager();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void refreshTable(ActionEvent actionEvent) {
        playerList=main.getplayerList();
        ArrayList<Player> clubPlayers = SearchPlayersandclubs.searchCountryandclub(playerList, "any", main.user);
        playerTable.setItems(FXCollections.observableArrayList(clubPlayers));
        playerTable.refresh();
    }
}
