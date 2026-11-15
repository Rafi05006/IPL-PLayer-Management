package fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class ClubManagerSandTController {
    @FXML
    public ImageView clubImage;

    private Main main;

    public void showMainMenu(ActionEvent actionEvent) throws Exception {
        main.showMainMenu();
    }

    public void showPlayersofOwnClub(ActionEvent actionEvent) throws Exception {
       main.showOwnPlayer();
    }

    public void ShowPlayersAvailableForTransfer(ActionEvent actionEvent) throws Exception {
        main.showTransferredList();

    }


    public void logOut(ActionEvent actionEvent) throws Exception {

        main.setBooladd(Boolean.FALSE);
        main.showLoginPage();

    }
    public void setClubImage(){
        String clubString="/fxml/playerImages[1]/clubLogos/"+main.user+".png";
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

    }
    public void setMain(Main main) {
        this.main=main;

    }

    public void handleChangePassword(ActionEvent actionEvent) throws Exception{
        main.showChangePassword();
    }
}
