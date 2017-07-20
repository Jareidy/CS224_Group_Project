package controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ImageLoginPopupViewController {
    Main main;
    Stage secondaryStage;

    public void setMain(Main main,Stage secondaryStage){
        this.main=main;
        this.secondaryStage=secondaryStage;
    }

    @FXML
    public void handleLoginButton(){
        main.showLoginViewWindow("imageView");
        secondaryStage.close();
    }

    @FXML
    public void handleCancelButton(){
        secondaryStage.close();
    }
}