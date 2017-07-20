package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    @FXML private Button confirmButton;
    @FXML private Button enterButton;

    public void setMain(Main main,Stage secondaryStage){
        this.main=main;
        this.secondaryStage=secondaryStage;
    }

    public void setCurrentScene(String currentScene){
        this.currentScene=currentScene;
    }

    @FXML
    public void handleEnterButton(){
        String searchUser = usernameField.getText().toLowerCase();
        for(User tempUser : UserManager.users){
            if(tempUser.getUsername().toLowerCase().equals(searchUser));{
                user=tempUser;
                securityQuestionLabel.setText(user.getSecurityQuestion());
                answerField.setVisible(true);
                answerLabel.setVisible(true);
                confirmButton.setVisible(true);
                return;
            }
        }
    }

    @FXML
    public void handleConfirmButton(){
            System.out.println(user.getUsername());
            System.out.println(user.getAnswer());
            if(answerField.getText().toLowerCase().equals(user.getAnswer().toLowerCase())){
                secondaryStage.close();
                main.showResetPasswordViewWindow(currentScene,user);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Your answer was incorrect.");
                alert.setContentText("Try again.");

                alert.showAndWait();
            }
    }

    @FXML
    public void handleBackButton(){
        secondaryStage.close();
        main.showLoginViewWindow(currentScene);
    }
}