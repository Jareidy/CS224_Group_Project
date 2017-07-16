package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import model.Main;
import model.report.Report;
import model.report.ReportXMLHandler;
import model.report.ReportsManager;
import model.picture.Picture;
import model.picture.PictureManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ImageViewController implements Initializable {

    @FXML private ImageView imageView;
    @FXML private Label dislike;
    @FXML private Label like;
    @FXML private Label imageTitle;
    @FXML private TextArea inputComment;
    @FXML private ListView commentsList;
    @FXML private ListView userList;
    @FXML private Button removeImageButton;
    @FXML private Button reportImageButton;
    @FXML private Button removeReportButton;

    private Main main;
    private Picture picture = MainViewController.selectedPicture;
    private ReportsManager reportsManager = new ReportsManager();
    ReportXMLHandler reportXMLHandler = new ReportXMLHandler();

    public void setMain(Main main){
        this.main=main;
    }

    public void setImage(Picture picture){
        this.picture=picture;
    }

    public void setText(){
        imageView.setImage(picture.getImageLink());
        imageTitle.setText(String.valueOf(picture.getTitle()));
        setLikesAndDislikes();
        setComments();
    }

    @FXML
    public void setButtons(){
        if(LoginViewController.login) {
            if (LoginViewController.currentUser.getUsername().equals("admin")) {
                removeImageButton.setVisible(true);
                removeReportButton.setVisible(true);
                reportImageButton.setVisible(false);
            } else {
                reportImageButton.setVisible(true);
            }
        }else{
            reportImageButton.setVisible(false);
        }

    }

    private void setLikesAndDislikes(){
        like.setText(String.valueOf(picture.getLikes()));
        dislike.setText(String.valueOf(picture.getDislikes()));
        updateXMLFile();
    }

    private void setComments(){
        picture.getCommentsText();
        commentsList.setItems(picture.commentText);
        userList.setItems(picture.commentUser);
        updateXMLFile();
    }

    private void updateXMLFile(){
        ImportDetailsViewController.PICTURE_XML_HANDLER.formatXmlFile(System.getProperty("user.dir")+"/src/res/"+"PictureData.xml");
    }

    public void handleBackButton(){
       main.showMainWindow();
    }

    public void handleLikeClick(){
        if(LoginViewController.login){
            picture.addLike();
            setLikesAndDislikes();
        }else{
            main.showImageLoginPopupViewWindow();
        }
    }

    public void handleDislikeClick(){
        if(LoginViewController.login) {
            picture.addDislike();
            setLikesAndDislikes();
        }else{
            main.showImageLoginPopupViewWindow();
        }
    }

    @FXML
    public void handleSubmitButton(){
        if(LoginViewController.login) {
            String newComment = inputComment.getText();
            picture.addComment(newComment);
            setComments();
        }else{
            main.showImageLoginPopupViewWindow();
        }
    }

    @FXML
    public void handleRemoveImageButton(){
        PictureManager.removePicture(picture);
        reportsManager.removeReport(picture);
        main.showMainWindow();
    }

    @FXML
    public void handleReportImageButton(){
        Report newReport = new Report(LoginViewController.currentUser,picture);
        reportsManager.addReport(newReport);
        reportXMLHandler.formatXmlFile(System.getProperty("user.dir")+"/src/res/"+"Reports.xml");
    }

    @FXML
    public void handleRemoveReportButton(){
        reportsManager.removeReport(picture);
        reportXMLHandler.formatXmlFile(System.getProperty("user.dir")+"/src/res/"+"Reports.xml");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImage(picture);
        setText();
    }
}
