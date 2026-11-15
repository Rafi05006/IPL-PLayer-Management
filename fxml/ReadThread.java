package fxml;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.ArrayList;

public class ReadThread implements Runnable {
    private final Thread thr;
    private final Main main;
    private boolean bool=true;
    ArrayList<Player> updatedPlayers=new ArrayList<>();

    public ReadThread(Main main) {

        this.main = main;
        this.thr = new Thread(this);
        thr.start();

    }

    public void run() {
        try {
            while (true) {
                Object o = main.getSocketWrapper().read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        System.out.println(loginDTO.getUserName());
                        System.out.println(loginDTO.isStatus());
                        Platform.runLater(() -> {
                            if (loginDTO.isStatus()) {
                                try {
                                    main.showClubManager();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                main.showAlert();
                            }
                        });
                    } else if (o instanceof ArrayList<?>) {

                            ArrayList<?> list = (ArrayList<?>) o;
                            if (!list.isEmpty() && list.get(0) instanceof Player) {
                                updatedPlayers = (ArrayList<Player>) list;
                                if(bool)
                                {
                                    bool=false;
                                    Platform.runLater(() -> {

                                        main.updatePlayerTable(updatedPlayers);

                                    });
                                }
                                else{
                                    main.updatePlayerTable(updatedPlayers);
                                }
                            }
                    }
                    else if(o instanceof dataDTO){
                        dataDTO data=(dataDTO)o;
                        Player p = data.getPlayer();
                        if(data.getTransferStatus()){

                            updatedPlayers.removeIf(player -> player.getName().equals(p.getName()));

                            updatedPlayers.add(p);
                            main.updateTransferPlayers(p);
                        }
                        else{
                            updatedPlayers.removeIf(player -> player.getName().equals(p.getName()));

                            updatedPlayers.add(p);
                            main.updateTransferPlayers(p);
                        }

                    }
                    else if(o instanceof SignupDTO){
                        Platform.runLater(() -> {
                                try {
                                    if(!((SignupDTO) o).isAllow()){
                                        main.showSignupError();
                                    }
                                    else{
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Sign Up Successful");
                                        alert.setHeaderText("Account created");
                                        alert.setContentText("Please use your credentials to log in");
                                        alert.showAndWait();
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                        });
                    } else if (o instanceof newpasswordDTO) {
                        Platform.runLater(() -> {
                            if(((newpasswordDTO) o).isPasschange()){
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Success!");
                                alert.setHeaderText("Password Changed successfully");
                                alert.setContentText("Use The New Password for log in next time");
                                alert.showAndWait();
                            }
                            else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Failed");
                                alert.setHeaderText("Failed to Authorize");
                                alert.setContentText("Old Password does not match");
                                alert.showAndWait();
                            }
                        });

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                main.getSocketWrapper().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



