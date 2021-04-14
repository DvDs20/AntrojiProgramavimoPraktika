package Controller;

import Backend.lecture;
import Backend.group;
import Backend.user;
import Date.mainRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class adminWindowController {


    @FXML
    private Button openNewPropertiesButton;

    @FXML
    private Button showGroupButton;

    @FXML
    private Button createNewLectureButton;

    @FXML
    private Button createNewGroupButton;

    @FXML
    private Button showAllGroupsButton;

    @FXML
    private Button showAllLecturesButton;

    @FXML
    private Button propertiesButton;

    @FXML
    private Button addNewStudentButton;

    @FXML
    private Button addNewTeacherButton;

    @FXML
    private Button changePasswordButton;

    @FXML
    private Pane lecturesListPane;

    @FXML
    private FlowPane contentLecturesListFlowPane;


    @FXML
    private Pane studentsListPane;


    @FXML
    private Button logoutButton;

    @FXML
    private Pane pane;

    @FXML
    private TextField oldPasswordTextField;

    @FXML
    private TextField newPasswordTextField;

    @FXML
    private TextField againNewPasswordField;

    @FXML
    private FlowPane contentCategoryGroupPane;

    @FXML
    private FlowPane contentStudentsListFlowPane;

    @FXML
    private Pane propertiesPane;


    mainRepository mainRepository = new mainRepository();
    private List<lecture> lecturesList;
    private List<group> groupsList;
    private List<user> listsUser;

    public void updateLectures() throws Exception{
        contentLecturesListFlowPane.getChildren().clear();
        displayLectures();
    }

    public void updateGroups() throws Exception{
        contentStudentsListFlowPane.getChildren().clear();
        displayUsers();
    }

    @FXML
    void PropertiesButtonClicked(ActionEvent event) {
        pane.setVisible(false);
        propertiesPane.setVisible(!propertiesPane.isVisible());
        lecturesListPane.setVisible(false);
        studentsListPane.setVisible(false);

    }

    @FXML
    void showNewsPaneButtonClicked(ActionEvent event) {
        propertiesPane.setVisible(false);
        pane.setVisible(!pane.isVisible());
        studentsListPane.setVisible(false);
        lecturesListPane.setVisible(false);
    }

    public void displayGroups() throws Exception{
        groupsList = mainRepository.getGroupList();
        contentCategoryGroupPane.getChildren().clear();
        for(group group: groupsList){
            Button button = new Button(group.getGroupTitle());
            button.setPrefWidth(100);
            groupsButtonHandler groupsButtonHandler = new groupsButtonHandler(group,this);
            button.setOnAction(groupsButtonHandler);
            contentCategoryGroupPane.getChildren().add(button);
        }
    }
    public void displayUsers() throws Exception{
        contentStudentsListFlowPane.getChildren().clear();
        listsUser = mainRepository.userListStud();
        for (user user : listsUser) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Frontend/studentInfoWindow.fxml"));
            Parent parent = fxmlLoader.load();
            studentInfoWindowController studentInfoWindowController = fxmlLoader.getController();
            studentInfoWindowController.displayStudentInfo(user,this);
            contentStudentsListFlowPane.getChildren().add(parent);

        }
    }

    public void displayUserGroup(int groupID) throws Exception{
        contentStudentsListFlowPane.getChildren().clear();
        listsUser = mainRepository.userList(groupID);
        for (user user : listsUser) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Frontend/studentInfoWindow.fxml"));
            Parent parent = fxmlLoader.load();
            studentInfoWindowController studentInfoWindowController = fxmlLoader.getController();
            studentInfoWindowController.displayStudentInfo(user,this);
            contentStudentsListFlowPane.getChildren().add(parent);
        }
    }

    @FXML
    void addNewLectureButtonClicked(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Frontend/addNewLectureWindow.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.setTitle("Naujos paskaitos pridėjimas");
        stage.show();
    }

    @FXML
    void addNewStudentButtonClicked(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Frontend/addNewStudentWindow.fxml"));
        Parent  parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.setTitle("Naujo studento pridėjimas");
        stage.show();
    }


    @FXML
    void addNewGroupButtonClicked(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Frontend/addNewGroupWindow.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.setTitle("Naujos grupės pridėjimas");
        stage.show();
    }

    @FXML
    void addNewTeacherButtonClicked(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Frontend/addNewTeacherWindow.fxml"));
        Parent  parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.setTitle("Naujo dėstytojo pridėjimas");
        stage.show();
    }

    @FXML
    void lecturesButtonClicked(ActionEvent event)throws Exception{
        propertiesPane.setVisible(false);
        studentsListPane.setVisible(false);
        pane.setVisible(false);
        lecturesListPane.setVisible(!lecturesListPane.isVisible());
        displayLectures();
    }

    @FXML
    void showGroupsButtonClicked(ActionEvent event) throws Exception {
        propertiesPane.setVisible(false);
        lecturesListPane.setVisible(false);
        pane.setVisible(false);
        studentsListPane.setVisible(!studentsListPane.isVisible());
        displayGroups();
        displayUsers();
    }

    public void displayLectures() throws Exception{
        lecturesList = new ArrayList<>();
        lecturesList = mainRepository.getLecturesList();
        contentLecturesListFlowPane.getChildren().clear();
        for(lecture lecture : lecturesList) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Frontend/lectureListWindow.fxml"));
            Parent parent = fxmlLoader.load();
            lectureListController lectureListController = fxmlLoader.getController();
            lectureListController.display(lecture, this);
            contentLecturesListFlowPane.getChildren().add(parent);
        }
    }

    @FXML
    void showAllList(ActionEvent event)throws Exception{
        displayUsers();
    }

    @FXML
    void logoutButtonClicked(ActionEvent event) throws Exception {
        mainRepository.setLoginUser(null);
        logoutButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Frontend/loginWindow.fxml"));
        Parent parent =fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene((new Scene(parent)));
        stage.setTitle("Akademinė sistema | Prisijungimas");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void changePasswordButtonClicked(ActionEvent event) throws Exception {
        try {
            if(!newPasswordTextField.getText().isEmpty() && !againNewPasswordField.getText().isEmpty() && !oldPasswordTextField.getText().isEmpty()) {
                mainRepository.changePassword(newPasswordTextField.getText().trim(), oldPasswordTextField.getText().trim(), againNewPasswordField.getText().trim());
                newPasswordTextField.clear();
                oldPasswordTextField.clear();
                againNewPasswordField.clear();
                JOptionPane.showMessageDialog(null,"Slaptažodis sėkmingai pakeistas!");
                propertiesPane.setVisible(false);
            }else
                throw new Exception("Slaptažodžiai buvo suvesti blogai!");
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null, exception);
        }
    }
}
