import model.ImportFile;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.io.File;

public class ImportFileTest {

    ImportFile importFile = new ImportFile();

    @Test
    public void findFileExtensionTest(){
        File file = new File("test.jpg");
        Assert.assertEquals(".jpg",importFile.findFileExtension(file));
    }


}
