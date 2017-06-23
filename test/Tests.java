import javafx.scene.image.Image;
import model.ImageManager;
import model.ImportFile;
import model.Picture;
import model.PictureDataParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class Tests {

    private final ImportFile importFile = new ImportFile();
    private ImageManager images = new ImageManager();
    private File file = new File(System.getProperty("user.dir")+"Test assets/"+"test.xml");

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
        ArrayList<Picture> pictures = new ArrayList<>(images.getImages());
        images.addImage("Arizona Clouds", null, "Arizona","Pretty clouds", ".jpg" );
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
        parser.parsePictureData(file);

    }

}