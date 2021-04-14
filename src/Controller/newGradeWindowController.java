package Controller;

import Date.mainRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.sql.SQLException;

public class newGradeWindowController {

    @FXML
    private Label titleWindowLabel;

    @FXML
    private Pane renewalPane;

    @FXML
    private Pane renewalPaneNumber_2;

    @FXML
    private TextField gradeTextField;

    @FXML
    private Label gradeLabel;

    @FXML
    private Button confirmButton;

    @FXML
    private Button confirmNewGradeButton;

    @FXML
    private TextField newGradeTextField;


    mainRepository mainRepository = new mainRepository();

    studentsGradesInfoForTeacherWindowController studentsGradesInfoForTeacherWindowController;


    int lecture;
    int student;

    @FXML
    void confirmButtonClicked(ActionEvent event) throws Exception {
        try {
            mainRepository.updateMark(gradeTextField.getText(), lecture, student);
            JOptionPane.showMessageDialog(null, "Pažymys sėkmingai pakeistas!");
            studentsGradesInfoForTeacherWindowController.displayStudent();
            confirmButton.getScene().getWindow().hide();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null,"Blogai įvestas pažymys");
        }
    }

    @FXML
    void confirmNewGradeButtonClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            mainRepository.newGrade(newGradeTextField.getText().trim(), lecture, student);
            JOptionPane.showMessageDialog(null, "Naujas pažymys įrašytas!");
            studentsGradesInfoForTeacherWindowController.displayStudent();
            confirmNewGradeButton.getScene().getWindow().hide();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Blogai įvestas pažymys!");
        }
    }


    public void newGradeDisplay(int lecture, int student, studentsGradesInfoForTeacherWindowController studentsGradesInfoForTeacherWindowController){
        titleWindowLabel.setText("Įrašykite naują pažymi:");
        renewalPane.setVisible(false);
        renewalPaneNumber_2.setVisible(true);

        this.lecture =  lecture;
        this.student = student;
        this.studentsGradesInfoForTeacherWindowController = studentsGradesInfoForTeacherWindowController;
    }

    public void updateGradeDisplay(int grade, int lecture, int student, studentsGradesInfoForTeacherWindowController studentsGradesInfoForTeacherWindowController){
        //titleWindowLabel.setText("Pažymio redagavimas");
        gradeLabel.setText(String.valueOf(grade));
        renewalPane.setVisible(true);
        renewalPaneNumber_2.setVisible(false);

        this.lecture =  lecture;
        this.student = student;
        this.studentsGradesInfoForTeacherWindowController = studentsGradesInfoForTeacherWindowController;
    }
}
