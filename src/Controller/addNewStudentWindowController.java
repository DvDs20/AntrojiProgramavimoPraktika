package Controller;

import Backend.group;
import Backend.user;
import Date.mainRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class addNewStudentWindowController {
    @FXML
    private CheckBox ckeckBoxIfIsBoy;

    @FXML
    private CheckBox checkBoxIfIsWoman;

    @FXML
    private ListView<String> stringListView;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private Button confirmButton;

    String gender;

    mainRepository mainRepository = new mainRepository();



    public void initialize() throws Exception {
        List<group> groupList = mainRepository.getGroupList();
        List<String> listTitle = new ArrayList<>();
        for(group group :groupList){
            listTitle.add(group.getGroupTitle());
        }

        ObservableList<String> stringObservableList = FXCollections.observableList(listTitle);
        stringListView.setItems(stringObservableList);
    }

    @FXML
    void womenCheckBox(ActionEvent event) {
        if(checkBoxIfIsWoman.isSelected()){
            ckeckBoxIfIsBoy.setSelected(false);
        }
    }

    @FXML
    void menCheckBox(ActionEvent event) {
        if(ckeckBoxIfIsBoy.isSelected()){
            checkBoxIfIsWoman.setSelected(false);
        }
    }

    @FXML
    void confirmButtonClicked(ActionEvent event) throws Exception {
        if (ckeckBoxIfIsBoy.isSelected()) {
            gender = "Vyras";
        } else if (checkBoxIfIsWoman.isSelected()) {
            gender = "Moteris";
        }
        int i = 0;

        try {
            if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty()) {
                throw new Exception("Ne visi duomenys suvesti!");
            } else {
                ObservableList<Integer> integerObservableList = stringListView.getSelectionModel().getSelectedIndices();
                List<group> groupList = mainRepository.getGroupList();
                i = groupList.get(integerObservableList.get(0)).getID();
                user user = new user(nameTextField.getText().trim(), surnameTextField.getText().trim(), gender, i);
                mainRepository.addNewStudent(user);
                confirmButton.getScene().getWindow().hide();
            }
        }catch (Exception exc){
            JOptionPane.showMessageDialog(null,exc.getMessage());
        }
    }
}
