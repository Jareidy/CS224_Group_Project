import javafx.scene.image.Image;
import model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

public class Tests {

    private final ImportFile importFile = new ImportFile();

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

}