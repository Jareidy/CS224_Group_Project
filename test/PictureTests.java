import model.ImportFile;
import model.picture.Picture;
import model.picture.PictureBuilder;
import model.picture.PictureDataParser;
import model.picture.PictureManager;
import model.user.User;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class PictureTests {

    PictureDataParser parser = new PictureDataParser();
    PictureManager pictureManager = new PictureManager();

    public void createPictureData(){
        for (int i = 0; i < 12; i++){
            PictureBuilder builder = new PictureBuilder();
            builder.setTitle("Arizona Clouds");
            builder.setImageLink(null);
            builder.setExtension(".jpg");
            builder.setDescription("Pretty clouds");
            builder.setLocation("Arizona");
            Picture newPicture = builder.build();
            pictureManager.addImage(newPicture);
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
        PictureManager pictureManager = new PictureManager();
        Assert.assertNotNull(pictureManager.searchImagesByLocation("testLocation"));
    }

    @Test
    public void imageManagerPictureTest(){
        createPictureData();
        for (Picture picture : pictureManager.getImages()){
            Assert.assertEquals("Arizona Clouds", picture.getTitle());
            Assert.assertSame(null, picture.getImageLink());
            Assert.assertEquals("Arizona", picture.getLocation());
            Assert.assertEquals("Pretty clouds", picture.getDescription());
            Assert.assertEquals(".jpg", picture.getFileExtension());
        }
    }
}