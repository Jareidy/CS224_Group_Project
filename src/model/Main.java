package model;

import controller.ImageViewController;
import controller.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

    public void showMainWindow(){

        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainView.fxml"));
            pane = loader.load();

            MainViewController mainViewController = loader.getController();
            mainViewController.setMain(this);


            Scene scene = new Scene(pane);

            primaryStage.setScene(scene);
            primaryStage.show();
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

            Scene scene = new Scene(pane);

            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void close(){
        primaryStage.close();
    }


}
