package model.picture;

import controller.LoginViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import model.user.User;
import model.user.UserManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class Picture {

    private final String title;
    private final Image imageLink;
    private final String location;
    private final String continent;
    private final String description;
    private final String fileExtension;
    public LinkedList<UserInput> userInputs= new LinkedList<>();
    LocalDateTime date = LocalDateTime.now();

    public static ObservableList<Comment> comments = FXCollections.observableArrayList();


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

    public void addCommentFromXML(String comment, String username, LocalDateTime commentDate){
        UserInput newUser = null;
        if(userInputs.isEmpty()){
            newUser = new UserInput(findUserFromUsername(username));
            userInputs.add(newUser);
            newUser.addUserComment(comment, date);
        }else{
            for (int i = 0; i < userInputs.size(); i++) {
                UserInput user = userInputs.get(i);
                if (user.getUser().equals(username)) {
                    user.addUserComment(comment, commentDate);
                    return;
                }
            }
            newUser = new UserInput(findUserFromUsername(username));
            newUser.addUserComment(comment, commentDate);
            userInputs.add(newUser);
        }
    }

    public User findUserFromUsername(String username) {
        User tempUser=null;
        for(User user : UserManager.users){
            if(user.getUsername().equals(username)){
                tempUser = user;
            }
        }
        return tempUser;
    }

    public void addComment(String comment){
        if(userInputs.isEmpty()){
            UserInput newUser = new UserInput(LoginViewController.currentUser);
            userInputs.add(newUser);
            newUser.addUserComment(comment, date);
        }else {
            for (int i = 0; i < userInputs.size(); i++) {
                UserInput user = userInputs.get(i);
                if (LoginViewController.currentUser.getUsername().equals(user.getUser())) {
                    user.addUserComment(comment, date);
                    return;
                }
            }
            UserInput newUser = new UserInput(LoginViewController.currentUser);
            userInputs.add(newUser);
            newUser.addUserComment(comment, date);
        }
    }

    public void getCommentsText(){
        for(UserInput userInput: userInputs){
            ArrayList<Comment> userComments = userInput.getComments();
            for(Comment comment: userComments){
                comments.add(comment);
            }
        }
    }

    public void addLike(){
        if(userInputs.isEmpty()){
            UserInput newUser = new UserInput(LoginViewController.currentUser);
            userInputs.add(newUser);
            newUser.addLikeDislike("like");
        }
        else {
            for (UserInput user : userInputs) {
                if (LoginViewController.currentUser.getUsername().equals(user.getUser())) {
                    user.addLikeDislike("like");
                    return;
                }
            }
            UserInput newUser = new UserInput(LoginViewController.currentUser);
            userInputs.add(newUser);
            newUser.addLikeDislike("like");
        }
    }


    public void addDislike(){
        if(userInputs.isEmpty()){
            UserInput newUser = new UserInput(LoginViewController.currentUser);
            userInputs.add(newUser);
            newUser.addLikeDislike("dislike");
        }
        else {
            for (UserInput user : userInputs) {
                if (LoginViewController.currentUser.getUsername().equals(user.getUser())) {
                    user.addLikeDislike("dislike");
                    return;
                }
            }
            UserInput newUser = new UserInput(LoginViewController.currentUser);
            userInputs.add(newUser);
            newUser.addLikeDislike("dislike");
        }
    }


    public void addLikeDislikeFromXML(String username, String likeDislike){
        UserInput newUser = null;
        if (userInputs.isEmpty()){
            newUser = new UserInput(findUserFromUsername(username));
            userInputs.add(newUser);
            newUser.addLikeDislike(likeDislike);
        }
        else {
            for (UserInput user : userInputs) {
                if (user.getUser().equals(username)) {
                    user.addLikeDislike(likeDislike);
                }
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