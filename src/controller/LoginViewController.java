package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.BCrypt;
import model.user.User;
import model.user.UserDataParser;
import model.user.UserManager;

import java.io.File;
import java.util.ArrayList;

public class LoginViewController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    public Main main;
    public Stage secondaryStage;
    public static User currentUser;
    private String currentPrimaryScene;
    public static boolean login = false;

    public void setMain(Main main, Stage secondaryStage) {
        this.main=main;
        this.secondaryStage=secondaryStage;
    }

    public void setCurrentPrimaryScene(String currentPrimaryScene) {
        this.currentPrimaryScene = currentPrimaryScene;
    }

    @FXML
    public void handleBackButton(){
        secondaryStage.close();
    }

    @FXML
    public void handleConfirmButton() {
        checkCredentials();
    }

    @FXML
    public void handleForgotPasswordButton(){
        secondaryStage.close();
        main.showSecurityQuestionViewWindow(currentPrimaryScene);
    }

    public void checkCredentials(){
        currentUser = null;
        String username = usernameField.getText();
        String password = passwordField.getText();
        ArrayList<User> users = UserManager.getUsers();

        for (int i = 0; i < users.size(); i++) {
            if (BCrypt.checkpw(password,users.get(i).getPassword()) && users.get(i).getUsername().toLowerCase().equals(username.toLowerCase())) {
                currentUser = users.get(i);
                login();
            }else{
                invalidCredentials();
            }
        }
    }

    private void login() {
        login = true;
        secondaryStage.close();
        if(currentPrimaryScene.equals("imageView")){
            main.showImageWindow();
        }else if(currentPrimaryScene.equals("mainView")){
            main.showMainWindow();
        }
    }

    private void invalidCredentials() {
        errorLabel.setText("Your user name or password is incorrect.");
    }
}