import model.PictureDataParser;
import model.XMLHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class XMLTester {
    @BeforeEach
    void setUp() {
        XMLHandler xml = new XMLHandler();
    }

    void testXML(){
        PictureDataParser parser = new PictureDataParser();
        parser.parsePictureData();
    }

    @AfterEach
    void tearDown() {
    }

}