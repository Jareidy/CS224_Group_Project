package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Main;
import model.User;

import static model.UsersXMLHandler.userDataParser;

public class LoginViewController {
    public Main main;
    public Stage secondaryStage;
    public User currentUser;
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

            for (int i = 0; i < userDataParser.users.size(); i++) {
                if (userDataParser.users.get(i).getPassword().equals(password) && userDataParser.users.get(i).getUsername().equals(username)) {
                    currentUser = userDataParser.users.get(i);
                    login();
                    break;
                }
            }
            invalidCredentials();
    }

    private void login() {
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