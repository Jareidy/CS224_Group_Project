package model.picture;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {

    private final String comment;
    Date date;

    public Comment(Date date, String comment){
        this.comment = comment;
        this.date = date;
    }

    public String getComment(){
        return comment;
    }

    public String getDate(){
        return date.toString();
    }

    public Date getDateData(){
        return date;
    }

}
