package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import model.Main;

import java.awt.*;

public class RegisterViewController {
    public Main main;

    @FXML
    private TextField inputUserName;
    @FXML
    private PasswordField inputPassword;
    @FXML
    private TextField inputEmailAddress;
    @FXML
    private Label title;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label emailAddressLabel;

    public void handleBackButton(){
        main.showMainWindow();
    }

    public void handleConfirmButton(){

    }


}