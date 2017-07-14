package model.picture;

import java.time.LocalDateTime;

public class Comment {

    private final String comment;
    LocalDateTime date = LocalDateTime.now();

    public Comment(LocalDateTime date, String comment){
        this.comment = comment;
        this.date = date;
    }

    public String getComment(){
        return comment;
    }

    public LocalDateTime getDate(){
        return date;
    }

}
