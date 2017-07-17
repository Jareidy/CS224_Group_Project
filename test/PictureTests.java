import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ImportFile;
import model.picture.*;
import model.user.User;
import model.user.UserDataParser;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PictureTests {

    public String imagePath = "file:///" + System.getProperty("user.dir")+"/Test assets/"+"test.png";

    @Rule
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

    public void createPictureData() {
        Image image = new Image(imagePath);
        for (int i = 0; i < 12; i++) {
            PictureBuilder builder = new PictureBuilder();
            builder.setTitle("Arizona Clouds");
            builder.setImageLink(image);
            builder.setExtension(".png");
            builder.setDescription("Pretty clouds");
            builder.setLocation("Arizona");
            builder.setContintent("America");
            Picture newPicture = builder.build();
            PictureManager.addImage(newPicture);
        }
    }

    public void createPictureXMLFile(){
        PictureXMLHandler xmlHandler = new PictureXMLHandler();
        createPictureData();
        xmlHandler.formatXmlFile(System.getProperty("user.dir")+"/Test assets/"+"TestPictureData.xml");
    }

    @Test
    public void findFileExtensionTest() {
        ImportFile importFile = new ImportFile();
        File file = new File("test.png");
        Assert.assertEquals(".png", importFile.findFileExtension(file));
    }

    @Test
    public void locationSearchTest() {
        Assert.assertNotNull(PictureManager.searchImages("testLocation"));
    }

    @Test
    public void testPictureExtension() {
        createPictureData();
        for (Picture picture : PictureManager.getImages()) {
            Assert.assertEquals(".png", picture.getFileExtension());
        }
    }

    @Test
    public void testPictureTitle(){
        createPictureData();
        for (Picture picture : PictureManager.getImages()) {
            Assert.assertEquals("Arizona Clouds", picture.getTitle());
        }
    }

    @Test
    public void testPictureLink(){
        createPictureData();
        for (Picture picture : PictureManager.getImages()) {
            Assert.assertNotNull(picture.getImageLink());
        }
    }

    @Test
    public void testPictureLocation(){
        createPictureData();
        for (Picture picture : PictureManager.getImages()) {
            Assert.assertEquals("Arizona", picture.getLocation());
        }
    }

    @Test
    public void testPictureDescription(){
        createPictureData();
        for (Picture picture : PictureManager.getImages()) {
            Assert.assertEquals("Pretty clouds", picture.getDescription());
        }
    }

    @Test
    public void getContinentTest() {
        Assert.assertEquals("America", PictureManager.images.get(0).getContinent());
    }

    @Test
    public void testPictureXMLCreator() {
        createPictureXMLFile();
        File file = new File(System.getProperty("user.dir")+"/Test assets/"+"TestPictureData.xml");
        Assert.assertTrue(file.exists());
        file.delete();
    }

    @Test
    public void testPictureXMLParser(){
        createPictureXMLFile();
        PictureDataParser dataParser = new PictureDataParser();
        File file = new File(System.getProperty("user.dir")+"/Test assets/"+"TestPictureData.xml");
        dataParser.parsePictureData(file);
        file.delete();
    }
}