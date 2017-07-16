package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.user.User;
import model.user.UserManager;
import model.user.UsersXMLHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterViewController {

    private Main main;
    private Stage secondaryStage;

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
    }

    private void createUser(){
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(emailAddressField.getText());
        if (mat.matches()) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String emailAddress = emailAddressField.getText();
            User user = new User(username, password, emailAddress);
            UserManager.addUser(user);
            secondaryStage.close();
            main.showMainWindow();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Not a valid email address");
            alert.setContentText("Please input a valid email address.");

            alert.showAndWait();
        }
    }
}