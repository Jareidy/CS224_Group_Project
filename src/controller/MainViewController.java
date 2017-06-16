package controller;

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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainViewController implements Initializable{

    private Main main;

    @FXML
    private TableView<Image> imageTable;
    @FXML
    private TableColumn imageDescriptionColumn;
    @FXML
    private TableColumn locationColumn;
    @FXML
    private TextField searchField;

    private final ObservableList<Image>imageData = FXCollections.observableArrayList();
    private ArrayList<Image> imagesCollection = new ArrayList<>();
    ImageWarehouse images = new ImageWarehouse();

    public void setMain(Main main) {
        this.main= main;
    }

    public void fillImageTable(){
        imagesCollection = images.getImages();
        for (Image image: imagesCollection){
            imageData.add(image);
        }
    }

    public void searchForLocation(){
        fillImageTable();
    }

    @FXML
    public void handleClickedImage(MouseEvent event){

        System.out.println(imageTable.getSelectionModel().getSelectedItem().getImageLink());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        locationColumn.setCellFactory(new PropertyValueFactory("location"));
        imageDescriptionColumn.setCellFactory(new PropertyValueFactory("description"));
        images.addImage("waffles", "Arizona", "GREAT SKIES");
        images.addImage("pancakes", "California", "Dinosaurs");
        fillImageTable();
        imageTable.setItems(imageData);
    }
}