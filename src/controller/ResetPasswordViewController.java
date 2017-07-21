package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.BCrypt;
import model.user.*;

import java.io.File;

public class ResetPasswordViewController {
    Main main;
    Stage secondaryStage;
    User user;
    String currentScene;

    @FXML private TextField newPasswordField;
    @FXML private TextField confirmPasswordField;

    public void setMain(Main main, Stage secondaryStage){
        this.main=main;
        this.secondaryStage=secondaryStage;
    }

    public void setResettingUser(User user){
        this.user=user;
    }

    public void setCurrentScene(String currentScene){
        this.currentScene=currentScene;
    }

    @FXML
    public void handleConfirmButton(){
        User currentUser = user;
        if(newPasswordField.getText().equals(confirmPasswordField.getText())){
            for(int i =0; i<UserManager.users.size();i++){
                if(UserManager.users.get(i).getUsername().equals(user.getUsername())){
                    UserManager.users.remove(i);
                    String password = confirmPasswordField.getText();
                    UserBuilder userBuilder = new UserBuilder();
                    userBuilder.setUsername(currentUser.getUsername());
                    userBuilder.setPassword(password);
                    userBuilder.setEmail(currentUser.getEmailAddress());
                    userBuilder.setSecurityQuestion(currentUser.getSecurityQuestion());
                    userBuilder.setAnswer(currentUser.getAnswer());
                    User resetUser = userBuilder.build();
                    UserManager.addUser(resetUser);
                }
            }
            UsersXMLHandler usersXMLHandler = new UsersXMLHandler();
            File file = new File(System.getProperty("user.dir") + "/src/res/" + "Users.xml");
            usersXMLHandler.formatXmlFile(System.getProperty("user.dir")+"/src/res/"+"Users.xml");
            UserManager.clearUserData();
            UserDataParser userDataParser = new UserDataParser();
            userDataParser.parseUserData(file);
            secondaryStage.close();
            main.showLoginViewWindow(currentScene);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Your passwords do not match.");

            alert.showAndWait();
        }
    }

    @FXML
    public void handleBackButton(){
        secondaryStage.close();
        main.showSecurityQuestionViewWindow(currentScene);
    }
}