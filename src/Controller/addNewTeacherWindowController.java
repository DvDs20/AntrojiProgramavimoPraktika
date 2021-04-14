package Controller;

import Backend.lecture;
import Backend.user;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import Date.mainRepository;

import javax.swing.*;

public class addNewTeacherWindowController {

    @FXML
    private ListView<String> stringListView;

    @FXML
    private CheckBox checkBoxIfIsMen;

    @FXML
    private CheckBox checkBoxIfIsWomen;

    @FXML
    private TextField rankTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private Button confirmButton;


    private String gender;


    mainRepository mainRepository = new mainRepository();

    public void initialize() throws Exception {
        List<lecture> lecturesList = mainRepository.getLecturesList();
        List<String> stringList = new ArrayList<>();
        for(lecture lecture : lecturesList){
            stringList.add(lecture.getLectureTitle());
        }
        ObservableList<String> stringObservableList = FXCollections.observableList(stringList);
        stringListView.setItems(stringObservableList);
    }

    @FXML
    void confirmButtonClicked(ActionEvent event) throws Exception {
        if (checkBoxIfIsMen.isSelected()) {
            gender = "Vyras";
        } else if (checkBoxIfIsWomen.isSelected()) {
            gender = "Moteris";
        }
        try {
            if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty()) {
                throw new Exception("Ne visi duoemenys suvesti!");
            } else {
                ObservableList<Integer> integerObservableList = stringListView.getSelectionModel().getSelectedIndices();
                List<lecture> lecturesList = mainRepository.getLecturesList();
                int i = lecturesList.get(integerObservableList.get(0)).getLectureID();

                user user = new user(nameTextField.getText().trim(), surnameTextField.getText().trim(), rankTextField.getText().trim(), gender, i);
                mainRepository.addNewTeacher(user);

                confirmButton.getScene().getWindow().hide();
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }

    @FXML
    void menChekBox(ActionEvent event) {
        if(checkBoxIfIsMen.isSelected()){
            checkBoxIfIsWomen.setSelected(false);
        }
    }

    @FXML
    void womenChekBox(ActionEvent event) {
        if(checkBoxIfIsWomen.isSelected()){
            checkBoxIfIsMen.setSelected(false);
        }
    }
}
