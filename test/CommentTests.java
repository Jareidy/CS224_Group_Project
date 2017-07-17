import model.picture.Comment;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentTests {

    LocalDateTime date = LocalDateTime.now();
    Comment comment = new Comment(date,"Test Comment","Test User");

    @Test
    public void getCommentTest(){
        Assert.assertEquals("Test Comment",comment.getComment());
    }

    @Test
    public void getDateDataTest(){
        Assert.assertEquals(date,comment.getDateData());
    }

    @Test
    public void getDateTest(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Assert.assertEquals(date.format(formatter),comment.getDate());
    }

    @Test
    public void getUserTest(){
        Assert.assertEquals("Test User",comment.getUser());
    }
}
