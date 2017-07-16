package model.report;

import model.XMLBase;
import model.user.User;
import model.user.UserManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

public class ReportXMLHandler {

    XMLBase xmlCreator = new XMLBase();
    Document doc;
    Element reports;

    public void formatXmlFile(String localFileName){
        ArrayList<Report> parsedReports = ReportsManager.reports;
        doc = xmlCreator.docCreator();
        Element rootElement = doc.createElement("reports");
        doc.appendChild(rootElement);
        for (Report parsedReport: parsedReports) {
            createReportElement(rootElement,parsedReport);
            formatUser(reports,parsedReport);
            formatPicture(reports,parsedReport);
        }
        createFile(localFileName);
    }

    private void createReportElement(Element rootElement,Report parsedReport) {
        reports = doc.createElement("report");
        rootElement.appendChild(reports);
    }

    private void formatUser(Element reports, Report parsedReport) {
        Element user = doc.createElement("user");
        user.appendChild((doc.createTextNode(parsedReport.getUser())));
        reports.appendChild(user);
    }

    private void formatPicture(Element reports,Report parsedReport) {
        Element picture = doc.createElement("picture");
        picture.appendChild((doc.createTextNode(parsedReport.getPicture())));
        reports.appendChild(picture);
    }

    public void createFile(String localFileName){
        xmlCreator.createXmlFile(doc, localFileName);
    }
}
