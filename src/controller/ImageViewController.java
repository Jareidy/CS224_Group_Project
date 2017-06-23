package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import model.Main;
import model.Picture;

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
        ImportDetailsViewController.xmlHandler.XMLWriter();
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
        if(newComment.equals("")){
        }else {
            picture.addComment("Anonymous", newComment);
            inputComment.clear();
            setComments();
        }
    }
}