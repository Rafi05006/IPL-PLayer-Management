package fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    private Main main;

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    public void returnAction() throws Exception {
        main.getSocketWrapper().closeConnection();
        main.showHome();
    }

    public void loginAction() {
        String userName = userText.getText();
        main.user=userName;
        String password = passwordText.getText();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName(userName);
        loginDTO.setPassword(password);
        try {
            main.getSocketWrapper().write(loginDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void signupAction(ActionEvent actionEvent) throws IOException {
        main.showSignUp();
    }
    void setMain(Main main) {

        this.main = main;

    }


}
