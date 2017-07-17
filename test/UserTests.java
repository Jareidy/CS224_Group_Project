import model.user.User;
import model.user.UserBuilder;
import model.user.UserManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class UserTests {

    UserManager users = new UserManager();
    User user;

    public void createTestUser(){
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.setUsername("jon");
        userBuilder.setPassword("jon");
        userBuilder.setAnswer("jon@jon.com");
        userBuilder.setSecurityQuestion("jon");
        userBuilder.setEmail("jon@jon.com");
        user = userBuilder.build();

    }

    @Test
    public void userTest(){
        Assert.assertEquals("jon", user.getUsername());
        Assert.assertEquals("jon", user.getPassword());
        Assert.assertEquals("jon@jon.com", user.getEmailAddress());
    }

    @Test
    public void userManagerTest(){
        for (int i = 0; i < 12;i++) {
            UserManager.addUser(user);
        }
        ArrayList<User> arrayUsers = UserManager.getUsers();
        for (User user: arrayUsers){
            Assert.assertEquals("jon", user.getUsername());
        }
    }
}
