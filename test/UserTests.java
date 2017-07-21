import model.user.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class UserTests {

    User user;


    public void createTestUsers(){
        UserManager.clearUserData();
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.setUsername("jon");
        userBuilder.setPassword("jon");
        userBuilder.setAnswer("jon@jon.com");
        userBuilder.setSecurityQuestion("jon");
        userBuilder.setEmail("jon@jon.com");
        user = userBuilder.build();
        for (int i = 0; i < 12;i++) {
            UserManager.addUser(user);
        }
    }

    public void createUserXMLFile(){
        createTestUsers();
        UsersXMLHandler userXML = new UsersXMLHandler();
        userXML.formatXmlFile(System.getProperty("user.dir")+"/Test assets/"+"TestUserData.xml");
    }

    @Test
    public void userPassword(){
        createTestUsers();
        Assert.assertEquals("jon", user.getPassword());
    }

    @Test
    public void testUsername(){
        createTestUsers();
        Assert.assertEquals("jon", user.getUsername());
    }

    @Test
    public void testEmail(){
        createTestUsers();
        Assert.assertEquals("jon@jon.com", user.getEmailAddress());
    }

    @Test
    public void userManagerTest(){
        createTestUsers();
        ArrayList<User> arrayUsers = UserManager.getUsers();
        for (User user: arrayUsers){
            Assert.assertEquals("jon", user.getUsername());
        }
    }

    @Test
    public void testUsersXMLHandler(){
        createUserXMLFile();
        File file = new File(System.getProperty("user.dir")+"/Test assets/"+"TestUserData.xml");
        Assert.assertTrue(file.exists());
        file.delete();
    }

    @Test
    public void testUserDataParser(){
        UserDataParser userParser = new UserDataParser();
        createUserXMLFile();
        File file = new File(System.getProperty("user.dir")+"/Test assets/"+"TestUserData.xml");
        UserDataParser.parseUserData(System.getProperty("user.dir")+"/Test assets/"+"TestUserData.xml");
        Assert.assertEquals(24, UserManager.getUsers().size());
        file.delete();
    }
}
