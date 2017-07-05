package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Main;

import static model.Main.mainViewController;

public class MapViewController {
    private Main main;
    @FXML TextField searchField;

    public void setMain(Main main){
        this.main = main;
    }

    @FXML
    public void handleBackButton(){
        main.showMainWindow();
    }

    @FXML
    public void handleSelectSouthAmerica(){
        handleSearchForLocationByContinent("South America");
    }

    @FXML
    public void handleSelectNorthAmerica(){
        handleSearchForLocationByContinent("North America");
    }

    @FXML
    public void handleSelectAustralia(){
        handleSearchForLocationByContinent("Australia");
    }

    @FXML
    public void handleSelectAfrica(){
        handleSearchForLocationByContinent("Africa");
    }

    @FXML
    public void handleSelectEurope(){
        handleSearchForLocationByContinent("Europe");
    }

    @FXML
    public void handleSelectAsia(){
        handleSearchForLocationByContinent("Asia");
    }

    public void handleSearchForLocationByContinent(String continent){
        main.showMainWindow();
        mainViewController.searchForContinent(continent);
    }

    @FXML
    public void handleSearchForLocation(){
        String location = searchField.getText();
        if(location.equals("")){
        }else{
            main.showMainWindow();
            mainViewController.searchForLocation(location);
        }
    }
}