package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.picture.Picture;
import model.picture.PictureManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class MainViewController implements Initializable{

    @FXML private TableView<Picture> imageTable;
    @FXML private TableColumn<Picture, String> imageTitleColumn;
    @FXML private TableColumn<Picture, String> locationColumn;
    @FXML private TextField searchField;
    @FXML private Button loginButton;
    @FXML private Button registerButton;
    @FXML private Button logoutButton;
    @FXML private Button importButton;
    @FXML private Button viewReportsButton;

    private Main main;
    private final ObservableList<Picture> imageData = FXCollections.observableArrayList();
    public static Picture selectedPicture;


    public void setMain(Main main) {
        this.main= main;
    }

    public void setLoginButtons(){
        if(LoginViewController.login){
            viewReportsButton.setVisible(false);
            if(LoginViewController.currentUser.getUsername().equals("admin")){
                viewReportsButton.setVisible(true);
            }
            logoutButton.defaultButtonProperty();
            registerButton.isDisabled();
            registerButton.setVisible(false);
            loginButton.isDisabled();
            loginButton.setVisible(false);
            importButton.setVisible(true);
            importButton.setDisable(false);
        }else{
            viewReportsButton.setVisible(false);
            logoutButton.isDisabled();
            logoutButton.setVisible(false);
            registerButton.defaultButtonProperty();
            loginButton.defaultButtonProperty();
        }
    }

    private void fillImageTable(){
        for(int i = 0; i < PictureManager.getImages().size(); i++) {
            ArrayList<Picture> images=PictureManager.getImages();
            imageData.add(images.get(i));
        }
       imageTable.setItems(imageData);
    }

    @FXML
    public void handleLogin(){
        main.showLoginViewWindow("mainView");
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
    public void handleSearchButton(){
        String search = searchField.getText();
        searchForImage(search);
    }

    public void searchForImage(String search){
        imageTable.getItems().clear();
        if(search.equals("")){
            fillImageTable();
        }else{
            fillTableWithSearchedImages(search);
        }
    }

    protected void fillTableWithSearchedImages(String search){
        ArrayList<Picture> searchedImages;
        searchedImages = PictureManager.searchImages(search);
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
            selectedPicture = imageTable.getSelectionModel().getSelectedItem();
            main.showImageWindow();
        }
        catch(Exception ignored){
            return;
        }
    }

    @FXML
    public void handleClearButton(){
        searchField.clear();
        imageTable.getItems().clear();
        fillImageTable();
    }

    @FXML
    public void handleRandomButton(){
        if(PictureManager.images.size()>0) {
            Random random = new Random();
            int randomNumber = random.nextInt(PictureManager.images.size());
            selectedPicture = PictureManager.images.get(randomNumber);
            main.showImageWindow();
        }else{
            showNoImagesAlert();
        }
    }

    private void showNoImagesAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("There are no images.");
        alert.setContentText("To see a random image, you must first add some images.");
        alert.showAndWait();
    }

    @FXML
    public void handleViewReportsButton(){
        main.showReportsWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        fillImageTable();
    }
}