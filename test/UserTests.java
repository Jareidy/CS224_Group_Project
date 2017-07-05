import model.user.User;
import model.user.UserManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class UserTests {

    User user = new User("jon", "jon", "jon@jon.com");
    UserManager users = new UserManager();

    @Test
    public void userTest(){
        Assert.assertEquals("jon", user.getUsername());
        Assert.assertEquals("jon", user.getPassword());
        Assert.assertEquals("jon@jon.com", user.getEmailAddress());
    }

    @Test
    public void userManagerTest(){
        for (int i = 0; i < 12;i++) {
            users.addUser(user);
        }
        ArrayList<User> arrayUsers = users.returnUsers();
        for (User user: arrayUsers){
            Assert.assertEquals("jon", user.getUsername());
        }
    }
}
