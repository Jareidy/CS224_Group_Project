package controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.ImageWarehouse;
import model.ImportFile;
import model.Main;
import javafx.scene.image.Image;
import model.Picture;



public class ImportDetailsViewController {

    private Main main;
    ImportFile importPhoto =  new ImportFile();
    String title;
    @FXML private TextField imageTitleField;
    @FXML private TextField imageLocationField;
    @FXML private TextArea imageDescriptionField;
    @FXML private Label filePathLabel;
    @FXML private ImageView imageView;


    public void setMain(Main main) {
        this.main=main;
    }

    @FXML
    public void handleBackButton(){
        main.showMainWindow();
    }
    @FXML
    public void handleChooseFile(){
        importPhoto.chooseFile();
        setFilePathLabel();
        displayChosenImage();
    }
    @FXML
    public void handleImportPhoto(){
        collectTitleInput();
        importPhoto.saveFile(importPhoto.getBufferedImage(),importPhoto.getFile(),title);
        collectUserInput();
    }

    private void setFilePathLabel(){
        filePathLabel.setText(String.valueOf(importPhoto.getFile()));
    }

    public void displayChosenImage(){
        Image importedPhoto = SwingFXUtils.toFXImage(importPhoto.getBufferedImage(),null);
        imageView.setImage(importedPhoto);
    }

    private void collectTitleInput(){
        title = imageTitleField.getText();
    }

    private void collectUserInput() {
        Image image = new Image("/teamB/"+title+importPhoto.getFileExtension());
        String location = imageLocationField.getText();
        String description = imageDescriptionField.getText();
        Picture newPicture = new Picture(title,image,location,description);
        ImageWarehouse imageWarehouse = new ImageWarehouse();
        imageWarehouse.addImage(title,image,location,description);
    }

}
