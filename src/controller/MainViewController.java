package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.net.URL;
import java.util.*;

import static controller.LoginViewController.loginViewController;

public class MainViewController implements Initializable{

    private Main main;
    private final ObservableList<Picture> imageData = FXCollections.observableArrayList();
    private final ImageManager imageManager = PictureDataParser.imageManager;
    private final PictureDataParser pictureDataParser = new PictureDataParser();
    @FXML
    private TableView<Picture> imageTable;
    @FXML
    private TableColumn<Picture, String> imageDescriptionColumn;
    @FXML
    private TableColumn<Picture, String> locationColumn;
    @FXML
    private TextField searchField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button logoutButton;


    public void setMain(Main main) {
        this.main= main;
    }

    public void setLoginButtons(){
        if(loginViewController.login){
            logoutButton.defaultButtonProperty();
            registerButton.isDisabled();
            registerButton.setVisible(false);
            loginButton.isDisabled();
            loginButton.setVisible(false);
        }else{
            logoutButton.isDisabled();
            logoutButton.setVisible(false);
            registerButton.defaultButtonProperty();
            loginButton.defaultButtonProperty();
        }
    }

    private void fillImageTable(){
        for(int i = 0; i < pictureDataParser.getImages().size(); i++) {
            ArrayList<Picture> images=pictureDataParser.getImages();
            imageData.add(images.get(i));
        }
       imageTable.setItems(imageData);
    }

    @FXML
    public void handleLogin(){
        main.showLoginViewWindow();
    }

    @FXML
    public void handleRegister(){
        main.showRegisterViewWindow();
    }

    @FXML
    public void handleLogout(){
        loginViewController.login=false;
        loginViewController.currentUser=null;
        main.showMainWindow();
    }

    @FXML
    public void handleGetTextFieldText(){
        String location = searchField.getText();
        searchForLocation(location);
    }

    public void searchForLocation(String search){
        imageTable.getItems().clear();
        if(search.equals("")){
            fillImageTable();
        }else{
            fillTableWithSearchedLocations(search);
        }
    }

    public void searchForContinent(String search){
        imageTable.getItems().clear();
        if(search.equals("")){
            fillImageTable();
        }else{
            fillTableWithSearchedContinents(search);
        }
    }

    protected void fillTableWithSearchedContinents(String continent){
        ArrayList<Picture> searchedImages;
        searchedImages = imageManager.searchImagesByContinent(continent);
        imageData.removeAll();
        imageData.addAll(searchedImages);
        imageTable.setItems(imageData);
    }

    protected void fillTableWithSearchedLocations(String location){
        ArrayList<Picture> searchedImages;
        searchedImages = imageManager.searchImagesByLocation(location);
        imageData.removeAll();
        imageData.addAll(searchedImages);
        imageTable.setItems(imageData);
    }

    @FXML
    public void handleMapView(){
        main.showMapViewWindow();
    }

    @FXML
    public void handleImportPhoto(){
       main.showImportDetailsWindow();
    }

    @FXML
    public void handleClickedImage(){
        try {
            Picture selectedImage = imageTable.getSelectionModel().getSelectedItem();
            main.showImageWindow(selectedImage);
        }
        catch(NullPointerException ignored){
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        fillImageTable();
    }
}