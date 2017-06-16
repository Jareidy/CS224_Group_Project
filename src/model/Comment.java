package model;

public class Comment {

    String user;
    String comment;

    public Comment(String user, String comment){
        this.user = user;
        this.comment = comment;
    }

    public String getComment(){
        return comment;
    }

    public String getUser(){
        return user;
    }
}
