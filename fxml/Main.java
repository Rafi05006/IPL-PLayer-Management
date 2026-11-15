package fxml;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    private Stage stage;
    private SocketWrapper socketWrapper;
    private Boolean booladd=false;
    String user;
    private Boolean boolt=true;
    ArrayList<Player> playerList;
    {
        try {
            FileOperations fo=new FileOperations();
            fo.setInputFileName("playersoffline.txt");
            playerList = fo.loadPlayers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Player> transferPlayersList = new ArrayList<>();

    public void updateTransferList(){
        for(int i=0;i<playerList.size();i++){
            if(playerList.get(i).isTransfer()){
                transferPlayersList.add(playerList.get(i));
            }
        }

    }

    public void updateTransferPlayers(Player player) {
        if(player.isTransfer()){
            transferPlayersList.add(player);
            playerList.removeIf(Player->Player.getName().equals((player.getName())));
            playerList.add(player);
            System.out.println("Updated transfer players list: " + transferPlayersList.size() + " players.");
        }
        else{
            transferPlayersList.removeIf(Player -> Player.getName().equals(player.getName()));
            playerList.removeIf(Player->Player.getName().equals((player.getName())));
            playerList.add(player);
            System.out.println("Removed Player");
        }

    }

    public Stage getStage() {
        return stage;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        showHome();

    }
    public void connectToServer() throws Exception {

            String serverAddress = "127.0.0.1";
            int serverPort = 33333;
            socketWrapper = new SocketWrapper(serverAddress, serverPort);
            new ReadThread(this);

    }
    public SocketWrapper getSocketWrapper() {
        return socketWrapper;
    }
    public void showLoginPage() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 1008, 508));
        stage.show();
    }
    public void showClubManager() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ClubManagerSandT.fxml"));
        Parent root = loader.load();

        // Loading the controller
        setBooladd(Boolean.TRUE);
        ClubManagerSandTController controller = loader.getController();
        controller.setMain(this);
        controller.setClubImage();
        // Set the primary stage
        stage.setTitle("Club Manager");
        stage.setScene(new Scene(root, 732, 675));
        stage.show();

    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }
    public void updatePlayerTable(ArrayList<Player> updatedPlayers) {
        playerList.clear();

        playerList.addAll(updatedPlayers);
        if(boolt){
            boolt=false;

            updateTransferList();
        }
    }
    public void showHome() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Home.fxml"));
        Parent root = loader.load();

        // Loading the controller
        HomeController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 850, 575));
        stage.show();
    }
    public void showMainMenu() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();

        // Loading the controller
        MainMenuController controller = loader.getController();
        controller.setMain(this);



        // Set the primary stage
        stage.setTitle("Main Menu");
        stage.setScene(new Scene(root, 732, 575));
        stage.show();
    }
    public void showOwnPlayer() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ownclubplayer.fxml"));
        Parent root = loader.load();

        // Loading the controller
        ownclubplayerController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Own Club Player");
        stage.setScene(new Scene(root, 1037, 675));
        stage.show();
    }
    public void showSearchPlayerbyName() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchbyName.fxml"));
        Parent root = loader.load();

        // Loading the controller
        searchbyNameController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Searching By Name");
        stage.setScene(new Scene(root, 1200, 675));
        stage.show();
    }
    public void showSearchClubandCountry() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchbyClubandCountry.fxml"));
        Parent root = loader.load();

        // Loading the controller
        searchingbyClubandcountry controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Searching By Club And Country");
        stage.setScene(new Scene(root, 1200, 675));
        stage.show();
    }


    public void showSearchPlayerbyPosition() throws Exception{
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchbyPosition.fxml"));
        Parent root = loader.load();

        // Loading the controller
        searchbyPositionController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Searching By Position");
        stage.setScene(new Scene(root, 1200, 675));
        stage.show();

    }

    public void showSearchSalaryRange() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchSalaryRange.fxml"));
        Parent root = loader.load();

        // Loading the controller
        SearchSalaryRangeController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Searching By Salary Range");
        stage.setScene(new Scene(root, 1200, 675));
        stage.show();
    }

    public void clubMaxSalary() throws Exception {

        ArrayList<Player>searchList=SearchPlayersandclubs.searchMaxsalary(playerList);
        showPlayer(searchList);
    }


    public void clubMaxAge() throws Exception {

        ArrayList<Player>searchList=SearchPlayersandclubs.searchMaxage(playerList);
        showPlayer(searchList);
    }

    public void clubMaxHeight()throws  Exception {

        ArrayList<Player>searchList=SearchPlayersandclubs.searchMaxheight(playerList);
        showPlayer(searchList);
    }

    public void addNewPlayer() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddPlayer.fxml"));
        Parent root = loader.load();

        // Loading the controller
        addPlayerController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Searching By Position");
        stage.setScene(new Scene(root, 834, 675));
        stage.show();

    }
    public void showPlayer(ArrayList<Player> searchPlayer) throws Exception {
        if(searchPlayer.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Player");
            alert.setHeaderText("No Player Found");
            alert.setContentText("Please Try again with Different keyword");
            alert.showAndWait();
        }
        else{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("playerList.fxml"));
            Parent root = loader.load();

            // Loading the controller
            PlayerlistController controller = loader.getController();
            controller.setMain(this);

            controller.getPlayerListFromMethod(searchPlayer);

            // Set the primary stage
            stage.setTitle("Player List");
            stage.setScene(new Scene(root, 1037, 675));
            stage.show();
        }

    }

    public void showCountry() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CountryList.fxml"));
        Parent root = loader.load();

        // Loading the controller
        CountryListController controller = loader.getController();
        controller.setMain(this);
        ArrayList<Country> countries=SearchPlayersandclubs.countCountry(playerList);

        controller.setCountryData(countries);
        // Set the primary stage
        stage.setTitle("Country List");
        stage.setScene(new Scene(root, 1200, 675));
        stage.show();
    }

    public void showTotalClubSalary() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TotalSalary.fxml"));
        Parent root = loader.load();

        // Loading the controller
        TotalSalaryController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Club Salary");
        stage.setScene(new Scene(root, 1200, 675));
        stage.show();

    }
    public ArrayList<Player> getplayerList(){
        return playerList;
    }

    public Boolean getBooladd() {
        return booladd;
    }

    public void setBooladd(Boolean booladd) {
        this.booladd = booladd;
    }


    public void showTransferredList() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TransferredList.fxml"));
        Parent root = loader.load();

        // Loading the controller
        TransferListController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Transfer List");
        stage.setScene(new Scene(root, 1037, 675));
        stage.show();
    }
    public ArrayList<Player> getTransferPlayersList(){
        return transferPlayersList;
    }

    public void showSignUp() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("signup.fxml"));
        Parent root = loader.load();

        // Loading the controller
        SignUpController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Sign Up");
        stage.setScene(new Scene(root, 939, 441));
        stage.show();
    }

    public void showSignupError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Sign Up Error");
        alert.setHeaderText("Club Name already taken");
        alert.setContentText("Please Change your Club Name");
        alert.showAndWait();
    }

    public void showChangePassword() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("changepassword.fxml"));
        Parent root = loader.load();

        // Loading the controller
        changePasswordcontroller controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Sign Up");
        stage.setScene(new Scene(root, 600, 441));
        stage.show();
    }
}
