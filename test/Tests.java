import javafx.scene.image.Image;
import model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class Tests {

    private final ImportFile importFile = new ImportFile();
    private ImageManager images = new ImageManager();
    private File file = new File(System.getProperty("user.dir")+"/Test assets/"+"test.xml");
    ArrayList<Picture> pictures = new ArrayList<>(images.getImages());

    @Before
    public void createTestImageManager(){
        PictureBuilder builder = new PictureBuilder();
        builder.setTitle("Arizona Clouds");
        builder.setImageLink(null);
        builder.setExtension(".jpg");
        builder.setDescription("Pretty clouds");
        builder.setLocation("Arizona");
        Picture newPicture = builder.build();
        pictures.add(newPicture);
    }

    @Test
    public void findFileExtensionTest(){
        File file = new File("test.jpg");
        Assert.assertEquals(".jpg",importFile.findFileExtension(file));
    }

    @Test
    public void locationSearchTest(){
        ImageManager imageManager = new ImageManager();
        Assert.assertNotNull(imageManager.searchImages("testLocation"));
    }

    @Test
    public void imageManagerPictureTest(){
        for (Picture picture : pictures){
            Assert.assertEquals("Arizona Clouds", picture.getTitle());
            Assert.assertSame(null, picture.getImageLink());
            Assert.assertEquals("Arizona", picture.getLocation());
            Assert.assertEquals("Pretty clouds", picture.getDescription());
            Assert.assertEquals(".jpg", picture.getFileExtension());
        }
    }

    @Test
    public void xmlReadWriteTest(){
        PictureDataParser parser = new PictureDataParser();
        ImageManager manager = new ImageManager();
        manager.addImage(pictures.get(0));
        XMLHandler.formatXmlFile(System.getProperty("user.dir")+"/Test assets/"+"Test.xml");
        parser.parsePictureData(file);
        Assert.assertNotNull(parser.getImages());
    }

}