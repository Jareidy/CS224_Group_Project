package model;

class Comment {

    private final String user;
    private final String comment;

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
