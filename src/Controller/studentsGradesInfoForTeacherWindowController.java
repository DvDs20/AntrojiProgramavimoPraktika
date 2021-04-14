package Controller;

import Backend.user;
import Date.mainRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class studentsGradesInfoForTeacherWindowController {

    @FXML
    private Button updateGradeButton;

    @FXML
    private Label nameSurnameLabel;

    @FXML
    private Label gradeLabel;

    @FXML
    private Button newGradeButton;


    user user;
    int lecture;

    mainRepository mainRepository = new mainRepository();


    public void displayStudent() throws Exception{
        nameSurnameLabel.setText(user.getName() + " " + user.getSurname());
        gradeLabel.setText(String.valueOf(mainRepository.getGrade(lecture,user.getUserID())));
        if(mainRepository.getGrade(lecture,user.getUserID()) > 0){
            newGradeButton.setVisible(false);
            updateGradeButton.setVisible(true);
        }else {
            gradeLabel.setText("(pažymys neįrašytas)");
            updateGradeButton.setVisible(false);
            newGradeButton.setVisible(true);
        }
    }

    @FXML
    void updateGradeButtonClicked(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Frontend/newGradeWindow.fxml"));
        Parent parent = fxmlLoader.load();
        newGradeWindowController controller = fxmlLoader.getController();
        controller.updateGradeDisplay(mainRepository.getGrade(lecture,user.getUserID()), lecture,user.getUserID(),this);
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("Pažymio redagavimas");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void newGradeButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Frontend/newGradeWindow.fxml"));
        Parent parent = fxmlLoader.load();
        newGradeWindowController fxmlLoaderController = fxmlLoader.getController();
        fxmlLoaderController.newGradeDisplay(lecture,user.getUserID(),this);
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("Pažymio įrašymas");
        stage.setResizable(false);
        stage.show();
    }

    public void updateData(user user, int lecture){
        this.user = user;
        this.lecture = lecture;
    }

}
