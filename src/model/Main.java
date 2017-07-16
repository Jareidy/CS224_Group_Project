package model;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.picture.Picture;
import model.picture.PictureDataParser;
import model.report.ReportDataParser;
import model.user.UserDataParser;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        PictureDataParser pictureDataParser= new PictureDataParser();
        File file = new File(System.getProperty("user.dir")+"/src/res/"+"PictureData.xml");
        UserDataParser.parseUserData();
        pictureDataParser.parsePictureData(file);
        ReportDataParser.parseReportData();
        launch(args);
    }

    private Stage primaryStage;
    private Stage secondaryStage;
    private AnchorPane pane;
    public static MainViewController mainViewController;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showMainWindow();
    }

    private void setSceneDefault(){
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        setStageExtremes();
        primaryStage.show();
    }

    private void setStageExtremes(){
        primaryStage.setMinWidth(860);
        primaryStage.setMinHeight(580);
        primaryStage.setMaxHeight(700);
        primaryStage.setMaxWidth(1000);
    }

    public void showMainWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainView.fxml"));
            pane = loader.load();
            mainViewController = loader.getController();
            mainViewController.setMain(this);
            mainViewController.setLoginButtons();
            setSceneDefault();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showMapViewWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MapView.fxml"));
            pane = loader.load();
            MapViewController mapViewController = loader.getController();
            mapViewController.setMain(this);
            setSceneDefault();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showImageWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/ImageView.fxml"));
            pane = loader.load();
            ImageViewController imageViewController = loader.getController();
            imageViewController.setMain(this);
            imageViewController.setButtons();
            setSceneDefault();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showImportDetailsWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/ImportDetailsView.fxml"));
            pane = loader.load();
            ImportDetailsViewController importDetailsViewController = loader.getController();
            importDetailsViewController.setMain(this);
            setSceneDefault();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showReportsWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/ReportsView.fxml"));
            pane = loader.load();
            ReportsViewController reportsViewController = loader.getController();
            reportsViewController.setMain(this);
            setSceneDefault();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showLoginViewWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/LogInView.fxml"));
            pane = loader.load();
            Scene scene = new Scene(pane);
            secondaryStage = new Stage();
            LoginViewController loginViewController = loader.getController();
            loginViewController.setMain(this,secondaryStage);
            secondaryStage.initOwner(primaryStage);
            secondaryStage.initModality(Modality.WINDOW_MODAL);
            secondaryStage.setScene(scene);
            secondaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showRegisterViewWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/RegisterView.fxml"));
            pane = loader.load();
            Scene scene = new Scene(pane);
            secondaryStage = new Stage();
            RegisterViewController registerViewController = loader.getController();
            registerViewController.setMain(this,secondaryStage);
            secondaryStage.initOwner(primaryStage);
            secondaryStage.initModality(Modality.WINDOW_MODAL);
            secondaryStage.setScene(scene);
            secondaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void showImageLoginPopupViewWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/ImageLoginPopupView.fxml"));
            pane = loader.load();
            Scene scene = new Scene(pane);
            secondaryStage = new Stage();
            ImageLoginPopupViewController imageLoginPopupViewController = loader.getController();
            imageLoginPopupViewController.setMain(this,secondaryStage);
            secondaryStage.initOwner(primaryStage);
            secondaryStage.initModality(Modality.WINDOW_MODAL);
            secondaryStage.setScene(scene);
            secondaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
