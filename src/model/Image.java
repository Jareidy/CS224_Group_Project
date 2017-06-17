package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;

public class Image {

    private String title;
    private String imageLink;
    private String location;
    private String description;
    ArrayList<Rating> ratings = new ArrayList<>();
    ObservableList<Comment> comments = FXCollections.observableArrayList();


    public Image(String title,String imageLink, String location, String description){
        this.title = title;
        this.imageLink = imageLink;
        this.location = location;
        this.description = description;
    }


    public String getImageLink() {
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

    public String titleProperty() {
        return title;
    }

    public void addRating(String user, String rating){
        Rating newRating = new Rating(user, rating);
        ratings.add(newRating);
    }

    public void addComment(String user, String comment){
        Comment newComment = new Comment(user, comment);
        comments.add(newComment);
    }

    public ArrayList returnRatings(){
        return ratings;
    }

    public ObservableList returnComments(){
        return comments;
    }

}
