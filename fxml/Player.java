package fxml;
import java.io.Serial;
import java.io.Serializable;

public class Player implements Serializable {


    private String Name;
    private String Country;
    private int Age;
    private double Height;
    private String Club;
    private String Position;
    private String Number;
    private int Salary;
    private boolean transfer;



    public Player(String name, String country, int age, double height, String club, String position, String number,
                  int salary) {
        Name = name;
        Country = country;
        Age = age;
        Height = height;
        Club = club;
        Position = position;
        Number = number;
        Salary = salary;
        this.transfer=false;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public String getClub() {
        return Club;
    }

    public void setClub(String club) {
        Club = club;
    }
    public boolean isTransfer() {
        return transfer;
    }

    public void setTransfer(boolean transfer) {
        this.transfer = transfer;
    }
    public void displayInfo() {
        System.out.println("Player Info:");
        System.out.println("Name: " + Name);
        System.out.println("Country: " + Country);
        System.out.println("Age: " + Age);
        System.out.println("Height: " + Height);
        System.out.println("Club: " + Club);
        System.out.println("Position: " + Position);
        System.out.println("Number: " + Number);
        System.out.println("Salary: " + Salary);
        System.out.println();

    }

}
