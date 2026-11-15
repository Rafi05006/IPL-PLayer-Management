package fxml;

import java.util.ArrayList;
import java.util.Scanner;

public class SearchPlayersandclubs {



    public static ArrayList<Player> searchName(ArrayList<Player> playerList, String name) {
        ArrayList<Player> searchResult = new ArrayList<>();

        if (!name.equalsIgnoreCase("ANY")) {
            for (int i = 0; i < playerList.size(); i++) {
                Player temp = playerList.get(i);
                if (temp.getName().equalsIgnoreCase(name)) {

                    searchResult.add(temp);
                }
            }
        } else if (name.equalsIgnoreCase("ANY")) {
            for (int i = 0; i < playerList.size(); i++) {

                Player temp = playerList.get(i);
                searchResult.add(temp);
            }

        }

        return searchResult;

    }

    public static ArrayList<Player> searchCountryandclub(ArrayList<Player> playerList, String country, String club) {
        ArrayList<Player> searchResult = new ArrayList<>();

        if (!country.trim().equalsIgnoreCase("ANY") && !club.trim().equalsIgnoreCase("ANY")) {
            for (int i = 0; i < playerList.size(); i++) {
                Player temp = playerList.get(i);
                if (temp.getCountry().equalsIgnoreCase(country.trim())
                        && temp.getClub().equals(club.trim())) {

                    searchResult.add(temp);
                }
            }
        } else if (country.trim().equalsIgnoreCase("ANY") && !club.trim().equalsIgnoreCase("ANY")) {
            for (int i = 0; i < playerList.size(); i++) {

                Player temp = playerList.get(i);
                if (temp.getClub().equals(club.trim())) {

                    searchResult.add(temp);
                }
            }
        } else if (!country.trim().equalsIgnoreCase("ANY") && club.trim().equalsIgnoreCase("ANY")) {
            for (int i = 0; i < playerList.size(); i++) {
                Player temp = playerList.get(i);
                if (temp.getCountry().equalsIgnoreCase(country.trim())) {

                    searchResult.add(temp);
                }
            }
        } else if (country.trim().equalsIgnoreCase("ANY") && club.trim().equalsIgnoreCase("ANY")) {

            for (int i = 0; i < playerList.size(); i++) {
                Player temp = playerList.get(i);
                searchResult.add(temp);
            }
        }

        return searchResult;
    }

    public static ArrayList<Player> searchPosition(ArrayList<Player> playerList, String position) {
        ArrayList<Player> searchResult = new ArrayList<>();

        for (int i = 0; i < playerList.size(); i++) {

            Player temp = playerList.get(i);
            if (temp.getPosition().equalsIgnoreCase(position)) {

                searchResult.add(temp);
            }
        }

        return searchResult;
    }

    public static ArrayList<Player> searchSalary(ArrayList<Player> playerList, int low, int high) {

        ArrayList<Player> searchResult = new ArrayList<>();

        for (int i = 0; i < playerList.size(); i++) {
            Player temp = playerList.get(i);
            if (temp.getSalary() >= low && temp.getSalary() <= high) {

                searchResult.add(temp);
            }
        }

        return searchResult;

    }

    public static ArrayList<Country> countCountry(ArrayList<Player> playerList) {

        ArrayList<Country> countryList = new ArrayList<>();
        for (int i = 0; i < playerList.size(); i++) {

            int j = 0;
            for (j = 0; j < countryList.size(); j++) {

                if (countryList.get(j).getCountryName().trim()
                        .equalsIgnoreCase((playerList.get(i).getCountry().trim()))) {
                    break;
                }
            }
            if (j == countryList.size()) {
                Country temp = new Country(playerList.get(i).getCountry(), 0);

                countryList.add(temp);
            }
        }

        for (int i = 0; i < countryList.size(); i++) {
            for (int j = 0; j < playerList.size(); j++) {
                if (countryList.get(i).getCountryName().equalsIgnoreCase(playerList.get(j).getCountry())) {
                    countryList.get(i).increaseNum();
                }
            }
        }
        return countryList;
    }

    public static ArrayList<Player> searchMaxsalary(ArrayList<Player> playerList) {
        ArrayList<Player> searchResult = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < playerList.size(); i++) {
            if (max < playerList.get(i).getSalary()) {
                max = playerList.get(i).getSalary();
            }
        }

        for (int i = 0; i < playerList.size(); i++) {
            if (max == playerList.get(i).getSalary()) {
                searchResult.add(playerList.get(i));
            }
        }
        return searchResult;
    }

    public static ArrayList<Player> searchMaxage(ArrayList<Player> playerList) {
        ArrayList<Player> searchResult = new ArrayList<>();

        int max = 0;
        for (int i = 0; i < playerList.size(); i++) {
            if (max < playerList.get(i).getAge()) {
                max = playerList.get(i).getAge();

            }
        }
        System.out.println("The maximum age player(s):");
        for (int i = 0; i < playerList.size(); i++) {
            if (max == playerList.get(i).getAge()) {
                searchResult.add(playerList.get(i));
            }
        }
        return searchResult;
    }

    public static ArrayList<Player> searchMaxheight(ArrayList<Player> playerList) {
        ArrayList<Player> searchResult = new ArrayList<>();
        double max = 0;

        for (int i = 0; i < playerList.size(); i++) {
            if (max < playerList.get(i).getHeight()) {
                max = playerList.get(i).getHeight();
            }
        }
        System.out.println("The maximum height player(s):");
        for (int i = 0; i < playerList.size(); i++) {
            if (max == playerList.get(i).getHeight()) {
                searchResult.add(playerList.get(i));
            }
        }
        return searchResult;
    }

    public static int totalSalary(ArrayList<Player> playerList, String clubName)

    {

        int total = 0;
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getClub().equalsIgnoreCase(clubName.trim())) {
                total += playerList.get(i).getSalary();
            }
        }

        return total;
    }

    public static void displayResult(ArrayList<Player> playerList) {
        if (playerList.isEmpty()) {
            System.out.println("No player Found");

        } else {
            for (int i = 0; i < playerList.size(); i++) {
                playerList.get(i).displayInfo();
            }
        }
    }

    public static void displayCountry(ArrayList<Country> countryList) {
        if (countryList.isEmpty()) {
            System.out.println("No Country Found");

        } else {
            for (int i = 0; i < countryList.size(); i++) {
                countryList.get(i).displayCountry();
            }
        }
    }

}
