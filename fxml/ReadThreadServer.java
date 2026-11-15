package fxml;

import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadThreadServer implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    private ArrayList<Player> playerList;
    private ArrayList<Player> transferList=new ArrayList<>();
    private ArrayList<SocketWrapper> socketWrapperArrayList=new ArrayList<>();
    public HashMap<String, String> userMap;

    public ReadThreadServer(HashMap<String, String> map, SocketWrapper socketWrapper, ArrayList<Player> playerList,ArrayList<SocketWrapper>socketWrapperArrayList) {
        this.userMap = map;
        this.socketWrapper = socketWrapper;
        this.playerList = playerList;
        this.socketWrapperArrayList=socketWrapperArrayList;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = socketWrapper.read();



                if (o instanceof ArrayList<?>)
                {
                    playerList = (ArrayList<Player>) o;
                }
                else if(o instanceof SignupDTO){
                    SignupDTO signupDTO=(SignupDTO) o;
                    String name=signupDTO.getUserName();
                    String pass=signupDTO.getPassword();


                        try{

                            if (userMap.containsKey(name)) {
                                signupDTO.setAllow(false);
                                socketWrapper.write(signupDTO);

                            }
                            else {
                                signupDTO.setAllow(true);

                                socketWrapper.write(signupDTO);
                                BufferedWriter bw = new BufferedWriter(new FileWriter("credentials.txt", true));
                                bw.write(name + "=" + pass);
                                bw.newLine();
                                bw.close();
                                BufferedReader br = new BufferedReader(new FileReader("credentials.txt"));
                                String line;
                                while ((line = br.readLine()) != null) {
                                    String[] parts = line.split("=");
                                    if (parts.length == 2) {
                                        String clubName = parts[0].trim();
                                        String clubCode = parts[1].trim();
                                        userMap.put(clubName, clubCode);
                                    }
                                }
                                br.close();
                            }

                        } catch (Exception e) {
                            System.out.println("Error in Sign Up");
                        }

                }
                else if (o instanceof LoginDTO) {
                    LoginDTO loginDTO = (LoginDTO) o;
                    String password = userMap.get(loginDTO.getUserName());
                    loginDTO.setStatus(password != null && loginDTO.getPassword().equals(password));

                    socketWrapper.write(loginDTO);
                    if (loginDTO.isStatus()) {
                        socketWrapper.write(playerList);
                    }
                }
                else if(o instanceof dataDTO){ dataDTO data=(dataDTO)o;
                    Player transferPlayer = data.getPlayer();

                    if(data.s.equals("sell")){
                        playerList.removeIf(player -> player.getName().equals(transferPlayer.getName()));

                        playerList.add(transferPlayer);
                        transferList.add(transferPlayer);
                        FileOperations.AddPlayer(playerList);
                        for (SocketWrapper client : socketWrapperArrayList) {
                            try {
                                //client.write(playerList);
                                client.write(data);
                            } catch (IOException e) {
                                System.out.println("Error sending to client: " + e.getMessage());
                            }
                        }
                    }

                    else if(data.s.equals("buy")){
                        playerList.removeIf(player -> player.getName().equals(transferPlayer.getName()));

                        playerList.add(transferPlayer);
                        FileOperations.AddPlayer(playerList);
                        transferList.removeIf(player -> player.getName().equals(transferPlayer.getName()));
                        for (SocketWrapper client : socketWrapperArrayList) {
                            try {

                               // client.write(playerList);
                                client.write(data);
                            } catch (IOException e) {
                                System.out.println("Error sending to client: " + e.getMessage());
                            }
                        }

                    }

                }
                else if (o instanceof addPlayerDTO) {
                    Player newPlayer=((addPlayerDTO) o).getNewplayer();
                    playerList.add(newPlayer);
                    FileOperations.AddPlayer(playerList);
                    for (SocketWrapper client : socketWrapperArrayList) {
                        try {

                            client.write(newPlayer);

                        } catch (IOException e) {
                            System.out.println("Error sending to client: " + e.getMessage());
                        }
                    }

                } else if (o instanceof newpasswordDTO) {
                    newpasswordDTO newp=(newpasswordDTO) o;

                    synchronized (userMap){
                        if(userMap.containsKey(newp.getClubname()) && userMap.get(newp.getClubname()).equals(newp.getOldpass())){
                            userMap.put(newp.getClubname(),newp.getNewpass());

                            BufferedWriter bw = new BufferedWriter(new FileWriter("credentials.txt"));

                            for(String userName: userMap.keySet()){
                                String password=userMap.get(userName);
                                bw.write(userName + "=" + password);
                                bw.newLine();

                            }
                            bw.close();
                            newp.setPasschange(true);
                            socketWrapper.write(newp);
                        }
                        else{
                            socketWrapper.write(newp);
                        }
                    }


                }


            }
        } catch (Exception e) {
            System.out.println("Error in ReadThreadServer: " + e.getMessage());
        } finally {
            try {
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
