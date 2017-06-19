package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import model.Picture;
import model.ImageWarehouse;
import model.Main;

import java.net.URL;
import java.util.*;

public class MainViewController implements Initializable{

    private Main main;

    @FXML
    private TableView<Picture> imageTable;
    @FXML
    private TableColumn<Picture, String> imageDescriptionColumn;
    @FXML
    private TableColumn<Picture, String> locationColumn;
    @FXML
    private TextField searchField;

    private final ObservableList<Picture> imageData = FXCollections.observableArrayList();
    private ImageWarehouse images = new ImageWarehouse();
    public static Picture selectedImage;

    public void setMain(Main main) {
        this.main= main;
    }

    public void fillImageTable(){
        for(int i = 0; i < images.getImages().size(); i++) {
           imageData.add(images.getImages().get(i));
        }
       imageTable.setItems(imageData);
    }

    public void searchForLocation(){
        imageTable.getItems().clear();
        String location = searchField.getText();
        if(location.equals("")){
            fillImageTable();
        }else{
            fillTableWithSearchedItems(location);
        }
    }

    public void fillTableWithSearchedItems(String location){
        ArrayList<Picture> searchedImages;
        searchedImages = images.searchImages(location);
        imageData.removeAll();
        for(Picture image: searchedImages){
            imageData.add(image);
        }
        imageTable.setItems(imageData);
    }

    @FXML
    public void handleClickedImage(MouseEvent event){
        selectedImage = imageTable.getSelectionModel().getSelectedItem();
        main.showImageWindow(selectedImage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageDescriptionColumn.setCellValueFactory(new PropertyValueFactory("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory("location"));
        setDefaultPictures();
        fillImageTable();
    }

    public void setDefaultPictures() {
        Image image1 = new Image("/res/blue-clouds-day-fluffy-53594.jpeg");
        images.addImage("Fluffy Clouds",image1, "Arizona", "Today the skies were blue and filled with giant fluffy clouds.");
        images.addImage("Blue Skies Today",image1, "California", "Today the skies were blue and filled with giant fluffy clouds.");
    }

    public void setDefaultComments(Picture newImage) {
        newImage.addComment("Anonymous","Wow! What a beautiful sky!");
        newImage.addComment("Anonymous","Love it!");
        newImage.addComment("Anonymous","Take more pictures");
        newImage.addComment("Anonymous","Wow! What a beautiful sky!");
        newImage.addComment("Anonymous","Wow! What a beautiful sky!");
        newImage.addComment("Anonymous","Wow! What a beautiful sky!");
    }
}