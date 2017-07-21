import javafx.scene.image.Image;
import model.picture.Picture;
import model.picture.PictureBuilder;
import model.report.Report;
import model.report.ReportXMLHandler;
import model.report.ReportsManager;
import model.user.User;
import model.user.UserBuilder;
import model.user.UserManager;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

public class ReportTests {

    @Rule
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
    public String imagePath = "file:///" + System.getProperty("user.dir")+"/Test assets/"+"test.png";
    public Report report;
    public Picture newPicture;

    public void createReports(){
        Image image = new Image(imagePath);
        PictureBuilder builder = new PictureBuilder();
        builder.setTitle("Arizona Clouds");
        builder.setImageLink(image);
        builder.setExtension(".png");
        builder.setDescription("Pretty clouds");
        builder.setLocation("Arizona");
        builder.setContintent("America");
        newPicture = builder.build();
        UserManager.clearUserData();
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.setUsername("jon");
        userBuilder.setPassword("jon");
        userBuilder.setAnswer("jon@jon.com");
        userBuilder.setSecurityQuestion("jon");
        userBuilder.setEmail("jon@jon.com");
        User newUser = userBuilder.build();
        report = new Report(newUser, newPicture);
    }

    public void createReportXML(){
        createReports();
        ReportsManager manager = new ReportsManager();
        manager.addReport(report);
        ReportXMLHandler reportXML = new ReportXMLHandler();
        reportXML.formatXmlFile("file:///" + System.getProperty("user.dir")+"/Test assets/"+"ReportTest.xml");
    }

    @Test
    public void testReportXml(){
        createReportXML();
        File file = new File(System.getProperty("user.dir")+"/Test assets/"+"ReportTest.xml");
        Assert.assertTrue(file.exists());
        file.delete();
    }

    @Test
    public void testReportsManager(){
        createReports();
        ReportsManager manager = new ReportsManager();
        manager.addReport(report);
        Assert.assertNotNull(ReportsManager.reports);
    }
}
