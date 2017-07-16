package model.picture;

import java.time.LocalDateTime;

public class Comment {

    private final String comment;
    LocalDateTime date = LocalDateTime.now();
    private final String userName;

    public Comment(LocalDateTime date, String comment, String userName){
        this.comment = comment;
        this.date = date;
        this.userName = userName;
    }

    public String getComment(){
        return comment;
    }

    public LocalDateTime getDate(){
        return date;
    }

}
