import controller.ImportDetailsViewController;
import model.*;
import model.picture.Picture;
import model.picture.PictureBuilder;
import model.picture.PictureDataParser;
import model.picture.PictureXMLHandler;
import model.user.User;
import model.user.UserManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class Tests {

    ImageManager manager = new ImageManager();
    User user = new User("jon", "jon", "jon@jon.com");
    UserManager users = new UserManager();

    @Before
    public void createTestImageManager(){
        for (int i = 0; i > 12; i++){
            PictureBuilder builder = new PictureBuilder();
            builder.setTitle("Arizona Clouds");
            builder.setImageLink(null);
            builder.setExtension(".jpg");
            builder.setDescription("Pretty clouds");
            builder.setLocation("Arizona");
            Picture newPicture = builder.build();
            manager.addImage(newPicture);
        }
    }

    @Test
    public void findFileExtensionTest(){
        ImportFile importFile = new ImportFile();
        File file = new File("test.jpg");
        Assert.assertEquals(".jpg",importFile.findFileExtension(file));
    }

    @Test
    public void locationSearchTest(){
        ImageManager imageManager = new ImageManager();
        Assert.assertNotNull(imageManager.searchImagesByLocation("testLocation"));
    }

    @Test
    public void imageManagerPictureTest(){
        for (Picture picture : manager.getImages()){
            Assert.assertEquals("Arizona Clouds", picture.getTitle());
            Assert.assertSame(null, picture.getImageLink());
            Assert.assertEquals("Arizona", picture.getLocation());
            Assert.assertEquals("Pretty clouds", picture.getDescription());
            Assert.assertEquals(".jpg", picture.getFileExtension());
        }
    }

    @Test
    public void xmlReadWriteTest(){
        File file = new File(System.getProperty("user.dir")+"/Test assets/"+"test.xml");
        PictureDataParser parser = new PictureDataParser();
        PictureXMLHandler pictureXmlHandler = new PictureXMLHandler();
        pictureXmlHandler.formatXmlFile(System.getProperty("user.dir")+"/Test assets/"+"Test.xml");
        parser.parsePictureData(file);
        Assert.assertNotNull(parser.getImages());
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
            users.addUser(user);
        }
        ArrayList<User> arrayUsers = users.returnUsers();
        for (User user: arrayUsers){
            Assert.assertEquals("jon", user.getUsername());
        }
    }
}