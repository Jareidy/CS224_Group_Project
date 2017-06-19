package model;

import controller.ImageViewController;
import controller.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Stage primaryStage;
    AnchorPane pane;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showMainWindow();
    }

    public void getScreenSize(){
        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int height = graphicsDevice.getDisplayMode().getHeight();
        if(height>864){
            setSceneDefault();
        }else{
            setSceneModified(height);
        }
    }

    private void setSceneModified(int height) {
        Scene scene = new Scene(pane, height, 630);
        primaryStage.setScene(scene);
        setStageExtremes();
        primaryStage.show();
    }

    public void setSceneDefault(){
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        setStageExtremes();
        primaryStage.show();
    }

    public void setStageExtremes(){
        primaryStage.setMinWidth(860);
        primaryStage.setMinHeight(580);
        primaryStage.setMaxHeight(570);
        primaryStage.setMaxWidth(1000);
    }

    public void showMainWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainView.fxml"));
            pane = loader.load();
            MainViewController mainViewController = loader.getController();
            mainViewController.setMain(this);
            getScreenSize();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showImageWindow(Picture picture){
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/ImageView.fxml"));
            pane = loader.load();
            ImageViewController imageViewController = loader.getController();
            imageViewController.setMain(this);
            imageViewController.setImage(picture);
            imageViewController.setText();
            getScreenSize();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void close(){
        primaryStage.close();
    }
}
