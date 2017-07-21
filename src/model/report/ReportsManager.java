package model.report;

import controller.LoginViewController;
import model.picture.Picture;
import model.user.User;

import java.util.ArrayList;

public class ReportsManager {

    public static ArrayList<Report> reports = new ArrayList<>();

    public void addReport(Report newReport){
        reports.add(newReport);
    }

    public void removeReport(Picture picture){
        Report sameReport = null;
        for(Report report:reports){
            if(report.getPicture().equals(picture.getTitle())){
                sameReport=report;
                break;
            }
        }
        reports.remove(sameReport);
    }

    public ArrayList<Report> getReportsList(){
        return reports;
    }
}
