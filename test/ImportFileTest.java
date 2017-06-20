import model.ImportFile;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class ImportFileTest {

    ImportFile importFile = new ImportFile();

    @Test
    public void findFileExtensionTest(){
        File file = new File("test.jpg");
        Assert.assertEquals(".jpg",importFile.findFileExtension(file));
    }


}
