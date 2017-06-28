package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.Main;

import java.awt.*;

public class RegisterViewController {

    public Main main;
    public Stage secondaryStage;

    @FXML private TextField inputUserName;
    @FXML private PasswordField inputPassword;
    @FXML private TextField inputEmailAddress;
    @FXML private Label title;
    @FXML private Label userNameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label emailAddressLabel;

    public void setMain(Main main, Stage secondaryStage) {
        this.main=main;
        this.secondaryStage=secondaryStage;
    }

    public void handleBackButton(){
        secondaryStage.close();
        main.showMainWindow();
    }

    public void handleConfirmButton() {
    }

}