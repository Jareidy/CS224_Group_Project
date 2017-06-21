package model;

public class Comment {

    private final String user;
    private final String comment;

    public Comment(String user, String comment){
        this.user = user;
        this.comment = comment;
    }

    private String getComment(){
        return comment;
    }

    private String getUser(){
        return user;
    }
}
