package model.picture;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {

    private final String comment;
    int date;

    public Comment(int date, String comment){
        this.comment = comment;
        this.date = date;
    }

    public String getComment(){
        return comment;
    }

    public int getDate(){
        return date;
    }

}
