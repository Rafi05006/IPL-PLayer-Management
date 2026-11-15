package fxml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {

    private ServerSocket serverSocket;
    public HashMap<String, String> userMap=new HashMap<>();

    {

        try {
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

        } catch (IOException e) {
            throw new RuntimeException("Error loading clubs or players: " + e.getMessage());
        }
    }

    private static ArrayList<SocketWrapper> clientSockets = new ArrayList<>();
    public ArrayList<Player> playerArrayList;
    {
        try {
            playerArrayList = FileOperations.loadPlayers();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Server() {

        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
        clientSockets.add(socketWrapper);
        new ReadThreadServer(userMap, socketWrapper, playerArrayList,clientSockets);
    }

    public static void main(String[] args) {
        new Server();

    }

}
