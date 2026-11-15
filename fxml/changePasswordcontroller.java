package fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

import java.io.IOException;

public class changePasswordcontroller {
    private Main main;
    @FXML
    public PasswordField oldPasswordField;
    @FXML
    public PasswordField newPasswordField;
    @FXML
    public PasswordField confirmPasswordField;

    public void handleChangePassword(ActionEvent actionEvent)  {
        String oldpass=oldPasswordField.getText();
        String newpass=newPasswordField.getText();
        String conpass=confirmPasswordField.getText();

        newpasswordDTO newpassdto=new newpasswordDTO();
        newpassdto.setClubname(main.user);
        newpassdto.setOldpass(oldpass);
        newpassdto.setNewpass(newpass);
        newpassdto.setConpass(conpass);
        if(newpass.equals(conpass)) {
            try {
                main.getSocketWrapper().write(newpassdto);

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Network Error");
                alert.setHeaderText("Failed to connect to server");
                alert.setContentText("Please try again later");
                alert.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            //show alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to Change Password");
            alert.setContentText("New Password and Confirm Password does not match");
            alert.showAndWait();
        }

    }

    public void handleCancel(ActionEvent actionEvent) throws IOException {
        main.showClubManager();
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
