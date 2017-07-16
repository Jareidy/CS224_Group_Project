package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.*;
import javafx.scene.image.Image;
import model.picture.*;

import java.io.IOException;

public class ImportDetailsViewController {

    private Main main;
    static final PictureXMLHandler PICTURE_XML_HANDLER = new PictureXMLHandler();
    private final ImportFile importPhoto =  new ImportFile();
    private String title;
    ObservableList<String> choices = FXCollections.observableArrayList("Asia","Africa","Australia","Europe","North America","South America");
    @FXML private TextField imageTitleField;
    @FXML private TextField imageLocationField;
    @FXML private TextArea imageDescriptionField;
    @FXML private Label filePathLabel;
    @FXML private Label errorLabel;
    @FXML private ImageView imageView;
    @FXML private ChoiceBox choicebox;

    public void setMain(Main main) {
        this.main=main;
    }

    @FXML
    public void handleBackButton(){
        main.showMainWindow();
    }

    @FXML
    public void handleChooseFile(){
        try {
            importPhoto.chooseFile();
            setFilePathLabel();
            displayChosenImage();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            main.showImportDetailsWindow();
        }
    }

    private void setFilePathLabel(){
        filePathLabel.setText(String.valueOf(importPhoto.getFile()));
    }

    private void displayChosenImage(){
        Image importedPhoto = SwingFXUtils.toFXImage(importPhoto.getBufferedImage(),null);
        imageView.setImage(importedPhoto);
    }

    @FXML
    public void handleImportPhoto() {
        if(imageTitleField.getText().equals("")||filePathLabel.getText().equals("")){
            setErrorLabel();
        }else {
            collectTitleInput();
            importPhoto.saveFile(importPhoto.getFile(), title);
            collectUserInput();
        }
    }

    private void collectTitleInput(){
        title = imageTitleField.getText();
    }

    private void collectUserInput() {
        String location = imageLocationField.getText();
        String description = imageDescriptionField.getText();
        String path = "file:///"+System.getProperty("user.dir")+"/src/res/"+title+importPhoto.getFileExtension();
        Image image = new Image(path);
        PictureBuilder builder = new PictureBuilder();
        builder.setTitle(title);
        builder.setExtension(importPhoto.getFileExtension());
        builder.setDescription(description);
        builder.setLocation(location);
        builder.setContintent(choicebox.getValue().toString());
        builder.setImageLink(image);
        Picture newPicture = builder.build();
        if(imageLocationField.getText().equals("")||imageDescriptionField.getText().equals("")){
            setErrorLabel();
        }
        else {
            PictureManager.addImage(newPicture);
            PICTURE_XML_HANDLER.formatXmlFile(System.getProperty("user.dir")+"/src/res/"+"PictureData.xml");
            main.showMainWindow();
        }
    }

    private void setErrorLabel() {
        errorLabel.setText("You must enter all fields.");
    }

    public void displayErrorFileAlreadyExists() {
        errorLabel.setText("Title is already in use.");
    }

    @FXML
    public void initialize(){
        choicebox.setItems(choices);
    }
}