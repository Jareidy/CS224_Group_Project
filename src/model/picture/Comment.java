package model.picture;

import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comment {

    private final String comment;
    LocalDateTime date;
    private final String user;

    public Comment(LocalDateTime date, String comment, String user){
        this.comment = comment;
        this.date = date;
        this.user = user;
    }

    public String getComment(){
        return comment;
    }

    public LocalDateTime getDateData(){
        return date;
    }

    public String getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = date.format(formatter);
        return formatDateTime;
    }

    public String getUser(){
        return user;
    }

}
