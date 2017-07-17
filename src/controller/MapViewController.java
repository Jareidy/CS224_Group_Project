package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static controller.Main.mainViewController;

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
    public void handleSearchForImage(){
        String search = searchField.getText();
        if(search.equals("")){
        }else{
            main.showMainWindow();
            mainViewController.searchForImage(search);
        }
    }
}