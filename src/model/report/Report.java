package model.report;

import model.picture.Picture;
import model.user.User;

public class Report {

    String picture;
    String user;

    public Report(User user, Picture picture){
        this.user=user.getUsername();
        this.picture=picture.getTitle();
    }

    public String getUser(){
        return user;
    }

    public String getPicture(){
        return picture;
    }

}
