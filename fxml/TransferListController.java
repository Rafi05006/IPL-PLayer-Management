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

public class TransferListController {

    public ImageView playerImage;
    public ImageView clubImage;
    public ImageView countryImage;
    public Label playerName;
    public Label playerPosition;
    public Label playerClub;



    @FXML
    private TableView<Player> transferTable;

    @FXML
    private TableColumn<Player, String> nameColumn;

    @FXML
    private TableColumn<Player, String> clubColumn;

    @FXML
    private TableColumn<Player, String> transferClubColumn;

    @FXML
    private TableColumn<Player, String> statusColumn;

    @FXML
    private Button acceptButton;

    private Main main;

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        clubColumn.setCellValueFactory(new PropertyValueFactory<>("club"));
        transferClubColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("transfer"));

        acceptButton.disableProperty().bind(transferTable.getSelectionModel().selectedItemProperty().isNull());

        transferTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                setPlayerDetails(newSelection);
            }
        });
    }

    public void setMain(Main main) {
        this.main = main;
        refreshTable();
    }

    @FXML
    private void handleAcceptRequest() {

        refreshTable();
        try {
            Player selectedPlayer = transferTable.getSelectionModel().getSelectedItem();
            selectedPlayer.setClub(main.user);
            selectedPlayer.setTransfer(false);
            dataDTO data=new dataDTO(selectedPlayer,"buy",selectedPlayer.isTransfer());
            main.getSocketWrapper().write(data);
            showAlert("Player Bought", "The player " + selectedPlayer.getName() + " is now available in your own club.");
        } catch (IOException e) {
            showAlert("Error", "Failed to send the buy request.");
        }catch (NullPointerException e){

            showAlert("Error", "Player is already bought by another club.");
        }

        refreshTable();
    }

    private void setPlayerDetails(Player player) {
        playerName.setText(player.getName());
        playerPosition.setText(player.getPosition());
        playerClub.setText(player.getClub());


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
    private void handleRefresh() {

        refreshTable();
    }

    private void refreshTable() {
        ArrayList<Player> transferRequests = main.getTransferPlayersList();
        transferTable.setItems(FXCollections.observableArrayList(transferRequests));
        transferTable.refresh();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void returnToClub() throws IOException {
        main.showClubManager();
    }
}
