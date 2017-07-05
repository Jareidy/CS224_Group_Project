package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Main;
import model.user.User;
import model.user.UserManager;

import java.util.ArrayList;


public class LoginViewController {
    public static LoginViewController loginViewController = new LoginViewController();
    public Main main;
    public Stage secondaryStage;
    public User currentUser = null;
    public boolean login = false;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    public void setMain(Main main, Stage secondaryStage) {
        this.main=main;
        this.secondaryStage=secondaryStage;
    }

    @FXML
    public void handleBackButton(){
        secondaryStage.close();
        main.showMainWindow();
    }

    @FXML
    public void handleConfirmButton() {
        checkCredentials();
    }

    public void checkCredentials(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        ArrayList<User> users = UserManager.returnUsers();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getPassword().equals(password) && users.get(i).getUsername().equals(username)) {
                loginViewController.currentUser = users.get(i);
                login();
                break;
            }
        }
        invalidCredentials();
    }

    private void login() {
        loginViewController.login = true;
        main.showMainWindow();
        secondaryStage.close();
    }

    private void invalidCredentials() {
        errorLabel.setText("Your user name or password is incorrect.");
    }

    public User getCurrentUser(){
        return currentUser;
    }
}