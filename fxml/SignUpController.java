package fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {
    @FXML
    public TextField clubName;
    @FXML
    public PasswordField Pass;
    @FXML
    public PasswordField confirmPass;
    private Main main;


    public void setMain(Main main) {
        this.main=main;
    }

    public void performSignUp(ActionEvent actionEvent) {
        String userName = clubName.getText();

        String password = Pass.getText();
        String cpass = confirmPass.getText();
        if(password.equals(cpass)){
            SignupDTO signupDTO = new SignupDTO(userName,password,cpass);
            try{
                main.getSocketWrapper().write(signupDTO);

            }
            catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Network Issue");
                alert.setContentText("Failed to connect to Server");
                alert.showAndWait();
            }


        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incorrect");
            alert.setContentText("Password does not match with Confirm Password");
            alert.showAndWait();
        }

    }

    public void returntologin(ActionEvent actionEvent) throws Exception {
        main.showLoginPage();
    }
}
