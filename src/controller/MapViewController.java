package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import model.ImageManager;
import model.Main;
import model.Picture;

import javafx.scene.control.TableView;
import java.awt.*;
import java.util.ArrayList;

public class MapViewController {
    private Main main;
    @FXML
    TextField searchField;
    @FXML
    private TableView<Picture> imageTable;

    private final ObservableList<Picture>imageData = FXCollections.observableArrayList();
    private ImageManager images = new ImageManager();
    public void setMain(Main main){
        this.main = main;
    }

    public void searchForLocation(){
        String location = searchField.getText();
        if(location.equals("")){
            fillImageTable();
        }else{
            fillTableWithSearchItems(location);
        }
    }

    public void fillImageTable(){
        for(int i = 0; i < images.getImages().size();i++){
            imageData.add(images.getImages().get(i));
        }
        imageTable.setItems(imageData);
    }

    public void fillTableWithSearchItems(String location){
        ArrayList<Picture> searchedImage;
        searchedImage = images.searchImages(location);
        imageData.removeAll();
        for(Picture image: searchedImage){
            imageData.add(image);
        }
        imageTable.setItems(imageData);
    }
}