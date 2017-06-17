package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import model.Main;
import model.Image;

public class ImageViewController {

    @FXML private ImageView imageView;
    @FXML private Label dislike;
    @FXML private Label like;
    @FXML private Label imageTitle;
    @FXML private TextArea inputComment;
    @FXML private TextArea imageDescription;
    @FXML private ListView commentsList;

    public Main main;
    public Image image;

    public void setMain(Main main){
        this.main=main;
    }
    public void setImage(Image image){
        this.image=image;
    }

    public void setText(){
        this.image=image;
        imageTitle.setText(String.valueOf(image.getTitle()));
        like.setText(String.valueOf(image.returnRatings()));
        dislike.setText(String.valueOf(image.returnRatings()));
        imageDescription.setText(String.valueOf(image.getDescription()));
        commentsList.setItems(image.returnComments());
    }

    public void handleBackButton(){
        main.showMainWindow();
    }
    public void handleCloseButton(){
        main.close();
    }
    public void handleSubmitButton(){
        String newComment = inputComment.getText();
        image.addComment("Anonymous",newComment);
        inputComment.clear();
    }


}
