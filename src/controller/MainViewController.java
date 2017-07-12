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
import model.Main;
import model.picture.Picture;
import model.picture.PictureDataParser;
import model.picture.PictureManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class MainViewController implements Initializable{

    private Main main;
    private final ObservableList<Picture> imageData = FXCollections.observableArrayList();
    private final PictureDataParser pictureDataParser = new PictureDataParser();
    @FXML private TableView<Picture> imageTable;
    @FXML private TableColumn<Picture, String> imageDescriptionColumn;
    @FXML private TableColumn<Picture, String> locationColumn;
    @FXML private TextField searchField;
    @FXML private Button loginButton;
    @FXML private Button registerButton;
    @FXML private Button logoutButton;
    @FXML private Button importButton;

    public void setMain(Main main) {
        this.main= main;
    }

    public void setLoginButtons(){
        if(LoginViewController.login){
            logoutButton.defaultButtonProperty();
            registerButton.isDisabled();
            registerButton.setVisible(false);
            loginButton.isDisabled();
            loginButton.setVisible(false);
            importButton.setVisible(true);
            importButton.setDisable(false);
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
        LoginViewController.login=false;
        LoginViewController.currentUser=null;
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

    protected void fillTableWithSearchedLocations(String location){
        ArrayList<Picture> searchedImages;
        searchedImages = PictureManager.searchImagesByLocation(location);
        imageData.removeAll();
        imageData.addAll(searchedImages);
        imageTable.setItems(imageData);
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
        searchedImages = PictureManager.searchImagesByContinent(continent);
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

    @FXML
    public void handleRandomButton(){
        Random random = new Random();
        int randomNumber = random.nextInt(imageData.size());
        Picture randomPicture = imageData.get(randomNumber);
        main.showImageWindow(randomPicture);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        fillImageTable();
    }
}