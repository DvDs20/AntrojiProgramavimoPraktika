package Controller;

import Backend.user;
import Date.mainRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class loginWindowController {

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField usernameTextField;


    @FXML
    private Button loginButton;

    @FXML
    void loginButtonClicked(ActionEvent event) throws Exception {
        user user;
        mainRepository mainRepository = new mainRepository();

        //admin -- 2
        //teacher -- 1
        //student -- 0

        try {
            if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
                throw new Exception("Jūsų prisijungimo duomenys neteisingi!");
            } else {
                user = mainRepository.Login(usernameTextField.getText().trim(), passwordTextField.getText().trim());
                loginButton.getScene().getWindow().hide();
                mainRepository.setLoginUser(user);
                if(user.getAccountType() == 2){
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Frontend/adminWindow.fxml"));
                    Parent parent = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene((new Scene(parent)));
                    stage.setTitle("Akademinė sistema | Administratorius: " + user.getName() + " " + user.getSurname());
                    stage.setResizable(false);
                    stage.show();
                }else if (user.getAccountType() == 0) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Frontend/studentWindow.fxml"));
                    Parent parent = fxmlLoader.load();
                    studentWindowController studentWindowController = fxmlLoader.getController();
                    studentWindowController.mainPaneContent(user);
                    studentWindowController.display();
                    Stage stage = new Stage();
                    stage.setScene((new Scene(parent, 811, 575)));
                    stage.show();
                }else if(user.getAccountType() == 1){
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Frontend/teacherWindow.fxml"));
                    Parent parent = fxmlLoader.load();
                    teacherWindowController teacherWindowController = fxmlLoader.getController();
                    teacherWindowController.setLecture(user.getLessonID());
                    teacherWindowController.displayGroups();
                    Stage stage = new Stage();
                    stage.setScene((new Scene(parent)));
                    stage.setTitle("Akademinė sistema | Dėstytojas: " + user.getName() + " " + user.getSurname());
                    stage.setResizable(false);
                    stage.show();
                }
            }
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null,exception);
        }
    }

}
