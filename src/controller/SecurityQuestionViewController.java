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
    private User user;

    @FXML private Label securityQuestionLabel;
    @FXML private TextField usernameField;
    @FXML private TextField answerField;
    @FXML private Label answerLabel;
    @FXML private Button confirmButton;

    public void setMain(Main main,Stage secondaryStage){
        this.main=main;
        this.secondaryStage=secondaryStage;
        user=null;
    }

    public void setCurrentScene(String currentScene){
        this.currentScene=currentScene;
    }

    public void setUser(User tempUser){
        user=tempUser;
    }

    @FXML
    public void handleEnterButton(){
        String searchUser = usernameField.getText().toLowerCase();
        for(User tempUser : UserManager.users){
            if(searchUser.toLowerCase().equals(tempUser.getUsername().toLowerCase())){
                System.out.println(tempUser.getUsername());
                System.out.println(searchUser);
                securityQuestionLabel.setText(tempUser.getSecurityQuestion());
                answerField.setVisible(true);
                answerLabel.setVisible(true);
                confirmButton.setVisible(true);
                System.out.println(tempUser.getUsername());
                setUser(tempUser);
                System.out.println(user.getUsername());
                return;
            }
        }
    }

    @FXML
    public void handleConfirmButton(){
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