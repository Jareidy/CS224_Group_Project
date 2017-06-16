package model;

import controller.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showMainWindow();
        MainViewController mainViewController = new MainViewController();
        mainViewController.fillImageTable();
    }

    private void showMainWindow(){

        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/MainView.fxml"));
            AnchorPane pane = loader.load();

            MainViewController mainMenuController = loader.getController();
            mainMenuController.setMain(this);

            Scene scene = new Scene(pane);

            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    void close(){
        primaryStage.close();
    }
}
