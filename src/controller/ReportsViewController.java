package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.picture.Picture;
import model.picture.PictureManager;
import model.report.Report;
import model.report.ReportsManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportsViewController implements Initializable{

    Main main;
    MainViewController mainViewController = new MainViewController();
    @FXML private TableView<Report> reportsTableView;
    @FXML private TableColumn<Report,String> userColumn;
    @FXML private TableColumn<Report,String> reportColumn;

    public void setMain(Main main) {
        this.main = main;
    }

    private void fillImageTable() {
        ObservableList<Report> reportsList = FXCollections.observableArrayList();
        reportsList.setAll(ReportsManager.reports);
        reportsTableView.setItems(reportsList);
        System.out.println(reportsList);
    }

    @FXML
    public void handleSelectedImage(){
        try {
            for(Picture picture : PictureManager.images){
                if(picture.getTitle().equals(reportsTableView.getSelectionModel().getSelectedItem().getPicture())){
                    mainViewController.selectedPicture = picture;
                    System.out.println(mainViewController.selectedPicture.getTitle());
                }
            }
            main.showImageWindow();
        }
        catch(NullPointerException ignored){
        }
    }

    @FXML
    public void handleBackButton(){
        main.showMainWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        reportColumn.setCellValueFactory(new PropertyValueFactory<>("picture"));
        fillImageTable();
    }
}
