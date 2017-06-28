package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import model.Main;
import model.picture.Picture;

import static controller.LoginViewController.loginViewController;

public class ImageViewController {

    @FXML private ImageView imageView;
    @FXML private Label dislike;
    @FXML private Label like;
    @FXML private Label imageTitle;
    @FXML private TextArea inputComment;
    @FXML private ListView commentsList;
    @FXML private ListView userList;

    private Main main;
    private Picture picture;

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
        commentsList.setItems(picture.getCommentsText());
        userList.setItems(picture.getCommentsUser());
        updateXMLFile();
    }

    private void updateXMLFile(){
        ImportDetailsViewController.PICTURE_XML_HANDLER.formatXmlFile(System.getProperty("user.dir")+"/src/res/"+"PictureData.xml");
    }

    public void handleBackButton(){
       main.showMainWindow();
    }

    public void handleLikeClick(){
        picture.addLike(1);
        setLikesAndDislikes();
    }

    public void handleDislikeClick(){
        picture.addDislike(1);
        setLikesAndDislikes();
    }

    public void handleSubmitButton(){
        String newComment = inputComment.getText();
        String username;
        if(newComment.equals("")){
        }else {
            if(loginViewController.login) {
                username = loginViewController.getCurrentUser().getUsername();
            }else{
                username = "anonymous";
            }
            picture.addComment(username, newComment);
            inputComment.clear();
            setComments();
        }
    }
}