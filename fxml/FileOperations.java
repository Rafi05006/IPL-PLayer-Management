package fxml;

import java.io.*;
import java.util.ArrayList;

public class FileOperations {

    public void setInputFileName(String inputFileName) {
        INPUT_FILE_NAME = inputFileName;
    }

    private static String INPUT_FILE_NAME = "players.txt";
    private static String OUTPUT_FILE_NAME = "players.txt";

    public static ArrayList<Player> loadPlayers() throws IOException {

        ArrayList<Player> playerList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null)
                break;

            String parts[] = line.split(",");

            String name = parts[0].trim();
            String country = parts[1].trim();
            int age = Integer.parseInt(parts[2].trim());
            double height = Double.parseDouble(parts[3].trim());
            String club = parts[4].trim();
            String position = parts[5].trim();
            String number = parts[6].trim();
            int salary = Integer.parseInt(parts[7].trim());



            Player player = new Player(name, country, age, height, club, position, number, salary);
            playerList.add(player);
        }
        br.close();
        return playerList;

    }

    public static void AddPlayer(ArrayList<Player> playerList) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for (int i = 0; i < playerList.size(); i++) {
            Player newPlayer = playerList.get(i);
            String newData = newPlayer.getName() + "," + newPlayer.getCountry() + "," + newPlayer.getAge() + ","
                    + newPlayer.getHeight() + "," + newPlayer.getClub() + "," + newPlayer.getPosition() + ","
                    + newPlayer.getNumber() + "," + newPlayer.getSalary();

            bw.write(newData);
            bw.write(System.lineSeparator());
        }

        bw.close();

    }

}