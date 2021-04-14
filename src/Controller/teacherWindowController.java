package Controller;

import Backend.group;
import Backend.user;
import Date.mainRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.List;

public class teacherWindowController {

    @FXML
    private Pane groupGradePane;

    @FXML
    private FlowPane groupGradeFlowPane;

    @FXML
    private Button propertiesButton;

    @FXML
    private Label lectureTitleLabel;

    @FXML
    private Button groupButton;


    @FXML
    private Button confirmPasswordButton;

    @FXML
    private Button logoutButton;

    @FXML
    private FlowPane contentOfGroupsFlowPane;

    @FXML
    private FlowPane contentOfStudentsFlowPane;

    @FXML
    private Pane pane;

    @FXML
    private Pane propertiesPane;

    @FXML
    private Label nameSurnamelabel;

    @FXML
    private TextField oldPasswordTextField;

    @FXML
    private TextField newPasswordTextField;

    @FXML
    private TextField againNewPasswordtextField;


    mainRepository mainRepository = new mainRepository();
    private int lecture;
    List<group> groupList;
    List<user> userList;

    @FXML
    void propertiesButtonClicked(ActionEvent event) {
        propertiesPane.setVisible(true);
        pane.setVisible(false);

    }

    @FXML
    void groupButtonClicked(ActionEvent event) {
        pane.setVisible(!pane.isVisible());
        propertiesPane.setVisible(false);
    }

    @FXML
    void confirmNewPasswordClicked(ActionEvent event) {
        try {
            if(!newPasswordTextField.getText().isEmpty() && !oldPasswordTextField.getText().isEmpty() && !againNewPasswordtextField.getText().isEmpty()) {
                mainRepository.changePassword(newPasswordTextField.getText().trim(), oldPasswordTextField.getText().trim(), againNewPasswordtextField.getText().trim());
                newPasswordTextField.clear();
                oldPasswordTextField.clear();
                againNewPasswordtextField.clear();
                JOptionPane.showMessageDialog(null,"Slaptažodis sėkmingai pakeistas!");
                propertiesPane.setVisible(false);
            }else
                throw new Exception("Blogai suvesti duomenys!");
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null, exception);
        }
    }

    @FXML
    void logoutButtonClicked(ActionEvent event) throws Exception {
            mainRepository.setLoginUser(null);
            logoutButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Frontend/loginWindow.fxml"));
            Parent parent = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene((new Scene(parent)));
            stage.setTitle("Akademinė sistema | Prisijungimas");
            stage.setResizable(false);
            stage.show();
        }


    public void displayGroups() throws Exception {
        groupList = mainRepository.getGroupIDbyLesson(lecture);
        contentOfGroupsFlowPane.getChildren().clear();
        for (group group : groupList) {
            Button button = new Button(group.getGroupTitle());
            button.setPrefWidth(100);
            groupsButtonHandler groupsButtonHandler = new groupsButtonHandler(group, this);
            button.setOnAction(groupsButtonHandler);
            contentOfGroupsFlowPane.getChildren().add(button);
        }
    }

    public void displayGradesForTeacher(int groupID) throws Exception {
        contentOfStudentsFlowPane.getChildren().clear();

        userList = mainRepository.userList(groupID);
        for (user user : userList) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Frontend/studentsGradesForTeacherWindow.fxml"));
            Parent parent = fxmlLoader.load();
            studentsGradesInfoForTeacherWindowController studentsGradesInfoForTeacherWindowController = fxmlLoader.getController();
            studentsGradesInfoForTeacherWindowController.updateData(user, lecture);
            studentsGradesInfoForTeacherWindowController.displayStudent();
            contentOfStudentsFlowPane.getChildren().add(parent);
        }

    }

    public void setLecture(int lectureID){
        this.lecture = lectureID;
    }
}

