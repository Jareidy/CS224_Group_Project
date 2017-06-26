package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import model.Account;
import model.Main;
import java.awt.*;

public class LoginViewController {
    public Main main;

    @FXML
    private TextField inputUserName;
    @FXML
    private PasswordField inputPassword;

    @FXML
    private Label title;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label passwordLabel;

    public void handleBackButton(){
        main.showMainWindow();
    }

    public void handleConfirmButton() {

    }
}