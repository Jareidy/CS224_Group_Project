package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Image;
import model.ImageWarehouse;
import model.Main;

import java.net.URL;
import java.util.*;

public class MainViewController implements Initializable{

    private Main main;

    @FXML
    private TableView<Image> imageTable;
    @FXML
    private TableColumn<Image, String> imageDescriptionColumn;
    @FXML
    private TableColumn<Image, String> locationColumn;
    @FXML
    private TextField searchField;

    private final ObservableList<Image> imageData = FXCollections.observableArrayList();
    private ImageWarehouse images = new ImageWarehouse();
    public static Image selectedImage;

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
        ArrayList<Image> searchedImages = new ArrayList<>();
        searchedImages = ImageWarehouse.searchImages(location);
        imageData.removeAll();
        for(Image image: searchedImages){
            imageData.add(image);
        }
        imageTable.setItems(imageData);
    }

    @FXML
    public void handleClickedImage(MouseEvent event){
        selectedImage = imageTable.getSelectionModel().getSelectedItem();
        main.showImageWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageDescriptionColumn.setCellValueFactory(new PropertyValueFactory("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory("location"));
        images.addImage("title","waffles", "Arizona", "GREAT SKIES");
        images.addImage("title2","pancakes", "California", "Dinosaurs");
        fillImageTable();
    }
}