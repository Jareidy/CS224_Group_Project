package model.picture;

import controller.LoginViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Picture {

    private final String title;
    private final Image imageLink;
    private final String location;
    private final String continent;
    private final String description;
    private final String fileExtension;
    public ArrayList<UserInput> userInputs= new ArrayList<>();
    LocalDateTime date = LocalDateTime.now();

    public static ObservableList<String> commentUser = FXCollections.observableArrayList();
    public static ObservableList<String> commentText = FXCollections.observableArrayList();

    public Picture(PictureBuilder builder){
        this.title = builder.title;
        this.imageLink = builder.imageLink;
        this.location = builder.location;
        this.continent = builder.contintent;
        this.description = builder.description;
        this.fileExtension=builder.extension;
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

    public void addComment(String comment){
        if(userInputs.isEmpty()){
            UserInput newUser = new UserInput(LoginViewController.currentUser);
            userInputs.add(newUser);
            newUser.addUserComment(comment, date);
        }else {
            for (UserInput user : userInputs) {
                if (LoginViewController.currentUser.getUsername().equals(user.getUser())) {
                    user.addUserComment(comment, date);
                } else {
                    UserInput newUser = new UserInput(LoginViewController.currentUser);
                    userInputs.add(newUser);
                    newUser.addUserComment(comment, date);
                }
            }
        }
    }


    public void getCommentsText(){
        commentUser.clear();
        commentText.clear();
        for(UserInput user: userInputs) {
            ArrayList<Comment> comments = user.getComments();
            for(Comment comment: comments) {
                commentUser.add(user.getUser());
                commentText.add(comment.getComment());
            }
        }
    }


    public void addLike(){
        for (UserInput user: userInputs){
            if (LoginViewController.currentUser.equals(user)){
                user.addLikeDislike("like");
            }
            else{
                UserInput newUser = new UserInput(LoginViewController.currentUser);
                userInputs.add(newUser);
                newUser.addLikeDislike("like");
            }
        }
    }

    public void addDislike(){
        for (UserInput user: userInputs){
            if (LoginViewController.currentUser.equals(user)){
                user.addLikeDislike("dislike");
            }
            else{
                UserInput newUser = new UserInput(LoginViewController.currentUser);
                userInputs.add(newUser);
                newUser.addLikeDislike("dislike");
            }
        }
    }

    public Integer getLikes() {
        int likes = 0;
        for (UserInput user: userInputs){
            if (user.getLikeDislike().equals("like")){
                likes++;
            }
        }
        return likes;
    }

    public Integer getDislikes() {
        int dislikes = 0;
        for (UserInput user: userInputs){
            if (user.getLikeDislike().equals("dislike")){
                dislikes++;
            }
        }
        return dislikes;
    }

    public String getFileExtension(){
        return fileExtension;
    }

    public String getContinent() {
        return continent;
    }
}
