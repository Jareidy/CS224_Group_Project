package model;

public class Rating {

    String user;
    String rating;

    public Rating(String user, String rating){
        this.user = user;
        this.rating = rating;
    }

    public String getRating(){
        return rating;
    }

    public String getUser(){
        return user;
    }
}
