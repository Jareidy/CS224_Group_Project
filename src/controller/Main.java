package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.picture.PictureDataParser;
import model.report.ReportDataParser;
import model.user.User;
import model.user.UserDataParser;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        PictureDataParser pictureDataParser= new PictureDataParser();
        File file = new File(System.getProperty("user.dir")+"/src/res/"+"PictureData.xml");
        UserDataParser.parseUserData(System.getProperty("user.dir")+"/src/res/"+"Users.xml");
        pictureDataParser.parsePictureData(file);
        ReportDataParser.parseReportData();
        launch(args);
    }

    private Stage primaryStage;
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

    private void setSecondarySceneDefault(Stage secondaryStage){
        Scene scene = new Scene(pane);
        secondaryStage.initOwner(primaryStage);
        secondaryStage.initModality(Modality.WINDOW_MODAL);
        secondaryStage.setScene(scene);
        secondaryStage.show();
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

    public void showLoginViewWindow(String currentScene){
        try{
            Stage secondaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/LogInView.fxml"));
            pane = loader.load();
            LoginViewController loginViewController = loader.getController();
            loginViewController.setMain(this,secondaryStage);
            loginViewController.setCurrentPrimaryScene(currentScene);
            setSecondarySceneDefault(secondaryStage);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showSecurityQuestionViewWindow(String currentScene){
        try{
            Stage secondaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/SecurityQuestionView.fxml"));
            pane = loader.load();
            SecurityQuestionViewController securityQuestionViewController = loader.getController();
            securityQuestionViewController.setMain(this,secondaryStage);
            securityQuestionViewController.setCurrentScene(currentScene);
            setSecondarySceneDefault(secondaryStage);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showResetPasswordViewWindow(String currentScene,User user){
        try{
            Stage secondaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/ResetPasswordView.fxml"));
            pane = loader.load();
            ResetPasswordViewController resetPasswordViewController = loader.getController();
            resetPasswordViewController.setMain(this,secondaryStage);
            resetPasswordViewController.setResettingUser(user);
            resetPasswordViewController.setCurrentScene(currentScene);
            setSecondarySceneDefault(secondaryStage);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showRegisterViewWindow(){
        try{
            Stage secondaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/RegisterView.fxml"));
            pane = loader.load();
            RegisterViewController registerViewController = loader.getController();
            registerViewController.setMain(this,secondaryStage);
            setSecondarySceneDefault(secondaryStage);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showImageLoginPopupViewWindow(){
        try{
            Stage secondaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/ImageLoginPopupView.fxml"));
            pane = loader.load();
            ImageLoginPopupViewController imageLoginPopupViewController = loader.getController();
            imageLoginPopupViewController.setMain(this,secondaryStage);
            setSecondarySceneDefault(secondaryStage);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}