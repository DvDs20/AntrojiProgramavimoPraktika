package Controller;

import Backend.lecture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import Date.mainRepository;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;

public class addNewLectureWindowController {

    @FXML
    private TextField newLectureTitleTextField;

    @FXML
    private TextArea newLectureDescriptionTextArea;

    @FXML
    private Button confirmButton;


    public void confirmButtonClicked(ActionEvent actionEvent) throws Exception {
        mainRepository mainRepository = new mainRepository();
        try {
            if (newLectureTitleTextField.getText().isEmpty() || newLectureDescriptionTextArea.getText().isEmpty()) {
                throw new Exception("Ne visi duomenys suvesti!");
            } else {
                mainRepository.addNewLecture(new lecture(newLectureTitleTextField.getText().trim(), newLectureDescriptionTextArea.getText().trim()));
                confirmButton.getScene().getWindow().hide();
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }
}
