package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.BCrypt;
import model.user.User;
import model.user.UserManager;

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
        if(newPasswordField.getText().equals(confirmPasswordField.getText())){
            user.resetPassword(newPasswordField.getText());
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
        main.showSecurityQuestionViewWindow(currentScene);
    }

}
