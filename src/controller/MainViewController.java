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
import model.*;

import java.lang.reflect.InvocationTargetException;
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
    ImageManager imageManager = PictureDataParser.imageManager;
    PictureDataParser pictureDataParser = new PictureDataParser();
    public static Picture selectedImage;

    public void setMain(Main main) {
        this.main= main;
    }

    public void fillImageTable(){
        for(int i = 0; i < pictureDataParser.getImages().size(); i++) {
            ArrayList<Picture> images=pictureDataParser.getImages();
            imageData.add(images.get(i));
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
        searchedImages = imageManager.searchImages(location);
        imageData.removeAll();
        for(Picture image: searchedImages){
            imageData.add(image);
        }
        imageTable.setItems(imageData);
    }

    @FXML
    public void handleImportPhoto(){
       main.showImportDetailsWindow();
    }

    @FXML
    public void handleClickedImage(MouseEvent event){
        try {
            selectedImage = imageTable.getSelectionModel().getSelectedItem();
            main.showImageWindow(selectedImage);
        }
        catch(NullPointerException e){
            return;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageDescriptionColumn.setCellValueFactory(new PropertyValueFactory("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory("location"));
        fillImageTable();
    }


}