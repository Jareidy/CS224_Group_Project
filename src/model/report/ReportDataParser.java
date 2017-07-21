package model.report;

import model.picture.Picture;
import model.picture.PictureDataParser;
import model.user.User;
import model.user.UserManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class ReportDataParser {

    public static ReportsManager reports = new ReportsManager();
    private static Document document;

    public static void parseReportData() {
        try{
            reports.getReportsList().clear();
            readXMLFile();
        }catch(IOException | SAXException | ParserConfigurationException e){
            e.printStackTrace();
        }
    }

    private static void readXMLFile() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(System.getProperty("user.dir")+"/src/res/Reports.xml");
        readImageFromDocument();
    }

    private static void readImageFromDocument() {
        NodeList reportsNodes = document.getElementsByTagName("reports");
        for(int i = 0; i<reportsNodes.getLength();i++){
            Node reportNode = reportsNodes.item(i);
            if(reportNode.hasChildNodes()){
                if (reportNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element reportElement = (Element) reportNode;
                    String username = reportElement.getElementsByTagName("user").item(0).getTextContent();
                    User reportingUser = null;
                    UserManager userManager = new UserManager();
                    ArrayList<User> userList = UserManager.getUsers();
                    for (User user : userList) {
                        if (user.getUsername().equals(username)) {
                            reportingUser = user;
                        }
                    }
                    String pictureTitle = reportElement.getElementsByTagName("picture").item(0).getTextContent();
                    Picture reportedPicture = null;
                    PictureDataParser pictureDataParser = new PictureDataParser();
                    ArrayList<Picture> imageList = pictureDataParser.getImages();
                    for (Picture picture : imageList) {
                        if (picture.getTitle().equals(pictureTitle)) {
                            reportedPicture = picture;
                        }
                    }
                    Report newReport = new Report(reportingUser, reportedPicture);
                    reports.addReport(newReport);
            }else {

                }
            }
        }
    }
}
