package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.ImageWarehouse;
import model.Main;
import model.Picture;
import java.awt.*;
import java.util.ArrayList;

public class MapViewController {

    private Main main;

    @FXML TextField searchField;
    @FXML private TableView<Picture> imageTable;

    private final ObservableList<Picture> imageData = FXCollections.observableArrayList();
    private ImageWarehouse images = new ImageWarehouse();

    public void setMain(Main main){
        this.main = main;
    }

    public void handleCloseButton(){
        main.close();
    }

    public void searchForLocation(){
        String location = searchField.getText();
        if(location.equals("")){
            fillImageTable();
        }else{
            fillTableWithSearchedItems(location);
        }
    }

    public void fillImageTable(){
        for(int i = 0; i < images.getImages().size(); i++) {
            imageData.add(images.getImages().get(i));
        }
        imageTable.setItems(imageData);
    }

    public void fillTableWithSearchedItems(String location){
        ArrayList<Picture> searchedImages = images.searchImages(location);
        imageData.removeAll();
        for(Picture image: searchedImages){
            imageData.add(image);
        }
        imageTable.setItems(imageData);
    }
}
