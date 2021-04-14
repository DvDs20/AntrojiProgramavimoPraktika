package Controller;

import Backend.lecture;
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

public class studentWindowController {


    @FXML
    private Button singOutbutton;

    @FXML
    private Button propertiesButton;

    @FXML
    private FlowPane contentFlowPane;

    @FXML
    private Label vardasPavardeLabel;

    @FXML
    private Label groupLabel;

    @FXML
    private Pane propertiesPane1;

    @FXML
    private TextField oldPasswordField1;

    @FXML
    private TextField newPasswordField1;

    @FXML
    private TextField confPasswordField1;

    @FXML
    private Button savePasswordButton1;

    @FXML
    private Button LessonButton;

    mainRepository rp = new mainRepository();
    user user;

    @FXML
    void clicklessonButton(ActionEvent event) {
        propertiesPane1.setVisible(false);
        contentFlowPane.setVisible(true);
    }


    @FXML
    void clickPropertiesButton(ActionEvent event) {
        contentFlowPane.setVisible(false);
        propertiesPane1.setVisible(true);
    }

    @FXML
    void clickSingOut(ActionEvent event) throws Exception {
        rp.setLoginUser(null);
        singOutbutton.getScene().getWindow().hide();
        FXMLLoader root = new FXMLLoader(getClass().getResource("../Frontend/logInWindow.fxml"));
        Parent loader =root.load();
        Stage stage = new Stage();
        stage.setScene((new Scene(loader, 811, 575)));
        stage.show();
    }

    @FXML
    void clieckSavePasswordButton(ActionEvent event) throws Exception {
        try {
            if(!newPasswordField1.getText().isEmpty() && !oldPasswordField1.getText().isEmpty() && !confPasswordField1.getText().isEmpty()) {
                rp.changePassword(newPasswordField1.getText().trim(), oldPasswordField1.getText().trim(), confPasswordField1.getText().trim());
                newPasswordField1.clear();
                oldPasswordField1.clear();
                confPasswordField1.clear();
                JOptionPane.showMessageDialog(null,"Slaptazodis pasikeite!");
            }else
                throw new Exception("Truksta irasu");
        }catch (Exception exc){
            JOptionPane.showMessageDialog(null, exc);
        }
    }

    public void mainPaneContent(user user) throws Exception{
        vardasPavardeLabel.setText(user.getName() + " " + user.getSurname());
        groupLabel.setText(rp.GetGroupTitleForUser(user.getGroupID()));
        this.user = user;
    }

    public void display() throws Exception {
        List<lecture> dalList = rp.getLectureList(user.getGroupID());

        contentFlowPane.getChildren().clear();
        for (lecture dal : dalList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/infoGradesForStudentsWindow.fxml"));
            Parent root = loader.load();
            infoGradesForStudentsWindowController controller = loader.getController();

            controller.display(rp.userName(dal.getLectureID()),dal.getLectureTitle(),rp.getGrade(dal.getLectureID(),user.getUserID()));
            contentFlowPane.getChildren().add(root);
        }
    }

}