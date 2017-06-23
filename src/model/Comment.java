package model;

public class Comment {

    private final String user;
    private final String comment;

    public Comment(String user, String comment){
        this.user = user;
        this.comment = comment;
    }

    String getComment(){
        return comment;
    }

    String getUser(){
        return user;
    }
}
