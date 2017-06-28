package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

import static model.UsersXMLHandler.userDataParser;

public class RegisterViewController {

    public Main main;
    public Stage secondaryStage;

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField emailAddressField;

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
        createUser();
        UsersXMLHandler usersXMLHandler = new UsersXMLHandler();
        usersXMLHandler.formatXmlFile(System.getProperty("user.dir")+"/src/res/"+"Users.xml");
        secondaryStage.close();
        main.showMainWindow();
    }

    public void createUser(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        String emailAddress = emailAddressField.getText();
        User user = new User(username,password,emailAddress);
        UserManager.addUser(user);
    }
}