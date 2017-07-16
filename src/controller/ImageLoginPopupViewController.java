package controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Main;

public class ImageLoginPopupViewController {
    Main main;
    Stage secondaryStage;

    public void setMain(Main main,Stage secondaryStage){
        this.main=main;
        this.secondaryStage=secondaryStage;
    }

    @FXML
    public void handleLoginButton(){
        secondaryStage.close();
        main.showLoginViewWindow();
    }

    @FXML
    public void handleCancelButton(){
        secondaryStage.close();
    }

}
