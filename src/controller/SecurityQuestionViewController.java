package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.user.User;
import model.user.UserManager;

public class SecurityQuestionViewController {

    Main main;
    Stage secondaryStage;
    String currentScene;
    User user=null;

    @FXML private Label securityQuestionLabel;
    @FXML private TextField usernameField;
    @FXML private Label usernameLabel;
    @FXML private TextField answerField;
    @FXML private Label answerLabel;

    public void setMain(Main main,Stage secondaryStage){
        this.main=main;
        this.secondaryStage=secondaryStage;
    }

    public void setCurrentScene(String currentScene){
        this.currentScene=currentScene;
    }

    @FXML
    public void handleConfirmButton(){
        if(usernameLabel.isVisible()){
            for(User tempUser : UserManager.users){
                if(tempUser.getUsername().toLowerCase().equals(usernameField.getText().toLowerCase()));{
                    user=tempUser;
                    usernameField.setVisible(false);
                    usernameLabel.setVisible(false);
                    securityQuestionLabel.setText(user.getSecurityQuestion());
                    answerField.setVisible(true);
                    answerLabel.setVisible(true);
                }
            }
        }else{
            System.out.println(user.getAnswer());
            if(answerField.getText().toLowerCase().equals(user.getAnswer().toLowerCase())){
                usernameField.setVisible(false);
                usernameLabel.setVisible(false);
                securityQuestionLabel.setText("Your password is "+user.getPassword()+".");
                answerField.setVisible(false);
                answerLabel.setVisible(false);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Your answer was incorrect.");
                alert.setContentText("Try again.");

                alert.showAndWait();
            }

        }
    }

    @FXML
    public void handleBackButton(){
        secondaryStage.close();
        main.showLoginViewWindow(currentScene);
    }
}
