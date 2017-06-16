package model;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Image {

    private SimpleStringProperty imageLink;
    private SimpleStringProperty location;
    private SimpleStringProperty description;
    ArrayList<Rating> ratings = new ArrayList<>();
    ArrayList<Comment> comments = new ArrayList<>();

    public Image(String imageLink, String location, String description){
        this.imageLink = new SimpleStringProperty(imageLink);
        this.location = new SimpleStringProperty(location);
        this.description = new SimpleStringProperty(description);
    }

    public SimpleStringProperty getImageLink() {
        return imageLink;
    }

    public SimpleStringProperty getLocation() {
        return location;
    }

    public SimpleStringProperty getDescription(){
        return description;
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

    public ArrayList returnComments(){
        return comments;
    }
}
