package fxml;

public class Country {
    String countryName;
    int totalNum;

    public Country(String countryName, int totalNum) {
        this.countryName = countryName;
        this.totalNum = totalNum;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public void increaseNum() {
        totalNum++;
    }

    public void displayCountry() {
        System.out.println("Country: " + countryName + "\ntotal: " + totalNum);
    }
}
