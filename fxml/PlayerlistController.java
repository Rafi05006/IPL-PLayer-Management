package fxml;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.util.ArrayList;

public class PlayerlistController {
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
    public Label playerHeight;
    @FXML
    public Label playerAge;
    @FXML
    public Label playerSalary;
    @FXML
    public Label playerNumber;
    @FXML
    public Label playerClub;


    private  Main main;

    @FXML
    private TableView<Player> playerTable;

    @FXML
    private TableColumn<Player, String> nameColumn;

    @FXML
    private TableColumn<Player, String> countryColumn;

    @FXML
    private TableColumn<Player, Integer> ageColumn;

    @FXML
    private TableColumn<Player, Double> heightColumn;

    @FXML
    private TableColumn<Player, String> clubColumn;

    @FXML
    private TableColumn<Player, String> positionColumn;

    @FXML
    private TableColumn<Player, String> numberColumn;

    @FXML
    private TableColumn<Player, Integer> salaryColumn;

    @FXML
    private TableColumn<Player, String> transferColumn;
    @FXML
    private ObservableList<Player> playerList;

    public void initialize() {
        playerName.setText(null);
        playerPosition.setText(null);
        playerHeight.setText(null);
        playerAge.setText(null);
        playerSalary.setText(null);
        playerNumber.setText(null);
        playerClub.setText(null);


        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));
        clubColumn.setCellValueFactory(new PropertyValueFactory<>("club"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        transferColumn.setCellValueFactory(new PropertyValueFactory<>("transfer"));

        playerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                setPlayerDetails(newSelection);
            }
        });

    }

    public void getPlayerListFromMethod(ArrayList<Player> searchPlayer) {
        playerList = FXCollections.observableArrayList(searchPlayer);
        playerTable.setItems(playerList);
    }

    public void setMain(Main main) {
        this.main=main;
        //playerArrayList=main.getplayerList();

    }


//    public void setPlayerList(ArrayList<Player> playerList) {
//        this.playerArrayList = playerList;
//    }

    public void returntoMain(ActionEvent actionEvent) throws Exception {
        main.showMainMenu();
    }
    public void setPlayerDetails(Player player)
    {
        playerName.setText("Name : "+player.getName());
        playerPosition.setText("Position : "+player.getPosition());
        playerAge.setText("Age : "+String.valueOf(player.getAge()));
        playerHeight.setText("Height : "+String.valueOf(player.getHeight()));
        playerClub.setText("Club : "+player.getClub());
        playerSalary.setText("Salary : "+String.valueOf(player.getSalary()));
        if(player.getNumber()!=null)
            playerNumber.setText(String.valueOf("Jersey No. : "+player.getNumber()));
        else playerNumber.setText("Jersey No. : +");


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
}
