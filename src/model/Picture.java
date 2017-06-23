package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Picture {

    private final String title;
    private final Image imageLink;
    private final String location;
    private final String description;
    private final ObservableList<Comment> comments = FXCollections.observableArrayList();
    private Integer likes=0;
    private Integer dislikes=0;
    private final String fileExtension;

    public Picture(String title, Image imageLink, String location, String description,String fileExtension){
        this.title = title;
        this.imageLink = imageLink;
        this.location = location;
        this.description = description;
        this.fileExtension=fileExtension;
    }

    public Image getImageLink() {
        return imageLink;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription(){
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void addComment(String user, String comment){
        Comment newComment = new Comment(user, comment);
        comments.add(newComment);
    }

    public ObservableList returnComments(){
        return comments;
    }

    public ObservableList getCommentsText(){
        ObservableList<String> commentText = FXCollections.observableArrayList();
        for(int i = 0; i<comments.size(); i++) {
            Comment currentComment = (Comment) returnComments().get(i);
            commentText.add(currentComment.getComment());
        }
        return commentText;
    }

    public ObservableList getCommentsUser(){
        ObservableList<String> commentUser = FXCollections.observableArrayList();
        for(int i = 0; i<comments.size(); i++) {
            Comment currentComment = (Comment) returnComments().get(i);
            commentUser.add(currentComment.getUser());
        }
        return commentUser;
    }

    public void addLike(int num){
        likes=likes+num;
    }

    public void addDislike(int num){
        dislikes=dislikes+num;
    }

    public Integer getLikes() {
        return likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public String getFileExtension(){
        return fileExtension;
    }

}
