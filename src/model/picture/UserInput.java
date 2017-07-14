package model.picture;

import controller.LoginViewController;
import model.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

public class UserInput {

    String user;
    ArrayList<Comment> comments = new ArrayList<>();
    String likeDislike="";

    public UserInput(User user){
        this.user = user.getUsername();
    }

    public void addUserComment(String comment, int date){
        Comment addComment = new Comment(date, comment);
        comments.add(addComment);


    }

    public ArrayList getComments(){
        return comments;
    }

    public String getUser(){
        return user.toString();
    }

    public String getLikeDislike(){
        return likeDislike;
    }

    public void addLikeDislike(String input){
        if (likeDislike.equals("dislike")){
            if (input.equals("dislike")){
                likeDislike = "";
            }
            else{
                likeDislike = "dislike";
            }
        }
        else if (likeDislike.equals("like")){
            if (input.equals("like")){
                likeDislike = "";
            }
            else{
                likeDislike = "like";
            }
        }
        else{
            likeDislike = input;
        }
    }
}
