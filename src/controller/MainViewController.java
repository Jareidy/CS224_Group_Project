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
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

public class MainViewController implements Initializable{

    private Main main;

    @FXML
    private TableView<Image> imageTable;
    @FXML
    private TableColumn<Image, String> imageLinkColumn;
    @FXML
    private TableColumn<Image, String> locationColumn;
    @FXML
    private TextField searchField;

    private HashMap<String, String> imageHashMap = new HashMap<>();
    private final ObservableList<Image>imageData = FXCollections.observableArrayList();



    public void setMain(Main main) {
        this.main= main;
    }

    public void fillImageTable(){
        ImageWarehouse imageWarehouse = new ImageWarehouse();
        imageHashMap = imageWarehouse.imageData();
        Set<String> imageKeys = imageHashMap.keySet();
        Iterator<String> imageIterator = imageKeys.iterator();

        String key;
        String value;
        while(imageIterator.hasNext()){
            key = imageIterator.next();
            value = imageHashMap.get(key);
            Image entry = new Image(key,value);
            imageData.add(entry);
        }
        imageTable.setItems(imageData);

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
        imageLinkColumn.setCellValueFactory(new PropertyValueFactory<>("model.Image"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("Location"));
        imageTable.setItems(imageData);
    }
}