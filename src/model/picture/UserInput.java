package model.picture;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

public class UserInput {

    String user;
    ArrayList<Comment> comments = new ArrayList<>();
    String likeDislike;

    public UserInput(String user){
        this.user = user;
    }

    public void addUserComment(String comment, Date date){
        Comment addComment = new Comment(date, comment);
        comments.add(addComment);
    }

    public void addLikeDislike(String input){
        if (likeDislike.isEmpty()){
            likeDislike = input;
        }
        if (likeDislike.equals("like")){
            if
        }
    }
}
