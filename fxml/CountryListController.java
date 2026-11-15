package fxml;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

public class CountryListController {

    private Main main;

    @FXML
    private TableView<Country> countryTable;

    @FXML
    private TableColumn<Country, String> countryNameColumn;

    @FXML
    private TableColumn<Country, Integer> totalNumColumn;

    private ObservableList<Country> countryList;

    public void initialize() {

        countryNameColumn.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        totalNumColumn.setCellValueFactory(new PropertyValueFactory<>("totalNum"));
    }

    public void setCountryData(ArrayList<Country> countries) {
        countryList = FXCollections.observableArrayList(countries);
        countryTable.setItems(countryList);
    }

    public void setMain(Main main) {
        this.main=main;
    }

    public void returntoMainmenu(ActionEvent actionEvent) throws Exception {
        main.showMainMenu();
    }
}
