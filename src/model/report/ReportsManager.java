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
        for(Report report:reports){
            if(report.getPicture().equals(picture)){
                reports.remove(report);
            }
        }
    }
}
