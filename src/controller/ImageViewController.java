package controller;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.picture.Comment;
import model.report.Report;
import model.report.ReportDataParser;
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
    @FXML private Button removeImageButton;
    @FXML private Button reportImageButton;
    @FXML private Button removeReportButton;
    @FXML private TableView<Comment> commentsTable;
    @FXML private TableColumn<Comment, String> userColumn;
    @FXML private TableColumn<Comment, String> commentColumn;
    @FXML private TableColumn<Comment, String> dateColumn;

    private Main main;
    private Picture picture = MainViewController.selectedPicture;
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
        updateXMLFile();
        dateColumn.setSortType(TableColumn.SortType.DESCENDING);
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
        ReportDataParser.reports.removeReport(picture);
        main.showMainWindow();
    }

    @FXML
    public void handleReportImageButton(){
        Report newReport = new Report(LoginViewController.currentUser,picture);
        ReportDataParser.reports.addReport(newReport);
        reportXMLHandler.formatXmlFile(System.getProperty("user.dir")+"/src/res/"+"Reports.xml");
    }

    @FXML
    public void handleRemoveReportButton(){
        ReportDataParser.reports.removeReport(picture);
        reportXMLHandler.formatXmlFile(System.getProperty("user.dir")+"/src/res/"+"Reports.xml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImage(picture);
        setText();
        userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
        commentsTable.setItems(Picture.comments);
        dateColumn.setSortType(TableColumn.SortType.DESCENDING);
        commentColumn.setSortable(false);
        userColumn.setSortable(false);
    }
}