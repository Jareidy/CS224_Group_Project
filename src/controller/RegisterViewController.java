package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.user.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterViewController {

    private Main main;
    private Stage secondaryStage;
    ObservableList<String> choices = FXCollections.observableArrayList("What is your mothers maiden name?","What was the first car you owned?","In what city were your born?");
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField emailAddressField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private PasswordField securityAnswerField;
    @FXML private ChoiceBox securityQuestionChoiceBox;

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
        if(passwordField.getText().equals(confirmPasswordField.getText())) {
            createUser();
            updateXML();
        }else{
            showUnmatchingPasswordsAlert();
        }
    }

    private void createUser(){
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(emailAddressField.getText());
        if (mat.matches()) {
            buildUserDetails();
            secondaryStage.close();
            main.showMainWindow();
        }
        else{
            showInvalidEmailAlert();
        }
    }

    public void buildUserDetails(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        String emailAddress = emailAddressField.getText();
        String secuirtyQuestion = securityQuestionChoiceBox.getValue().toString();
        String answer = securityAnswerField.getText();
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.setUsername(username);
        userBuilder.setEmail(emailAddress);
        userBuilder.setPassword(password);
        userBuilder.setSecurityQuestion(secuirtyQuestion);
        userBuilder.setAnswer(answer);
        User newUser = userBuilder.build();
        UserManager.addUser(newUser);
    }

    public void updateXML(){
        UsersXMLHandler usersXMLHandler = new UsersXMLHandler();
        usersXMLHandler.formatXmlFile(System.getProperty("user.dir") + "/src/res/" + "Users.xml");
        UserManager.clearUserData();
        UserDataParser.parseUserData(System.getProperty("user.dir")+"/src/res/"+"Users.xml");
    }

    public void showInvalidEmailAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Not a valid email address");
        alert.setContentText("Please input a valid email address.");
        alert.showAndWait();
    }

    public void showUnmatchingPasswordsAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Your passwords do not match.");
        alert.showAndWait();
    }

    @FXML
    public void initialize(){
        securityQuestionChoiceBox.setItems(choices);
    }
}