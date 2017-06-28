import model.*;
import model.picture.Picture;
import model.picture.PictureBuilder;
import model.picture.PictureDataParser;
import model.picture.PictureXMLHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class Tests {

    ImageManager manager = new ImageManager();


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

}