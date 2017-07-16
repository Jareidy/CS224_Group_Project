package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import model.Main;
import model.picture.Picture;

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
    @FXML private Button submitButton;

    private Main main;
    private Picture picture = MainViewController.selectedPicture;

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

    public void handleSubmitButton(){
        if(LoginViewController.login) {
            String newComment = inputComment.getText();
            picture.addComment(newComment);
            setComments();
        }else{
            main.showImageLoginPopupViewWindow();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImage(picture);
        setText();
    }
}
