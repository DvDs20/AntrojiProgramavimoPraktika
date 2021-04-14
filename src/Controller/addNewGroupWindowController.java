package Controller;

import Backend.lecture;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;
import Date.mainRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.swing.*;

public class addNewGroupWindowController {

    @FXML
    private Button selectLectureButton;

    @FXML
    private ListView<String> listView3;

    @FXML
    private ListView<String> listView4;

    @FXML
    private TextField newGroupTitleTextField;

    @FXML
    private Button confirmButton;

    mainRepository mainRepository = new mainRepository();


    List<lecture> lectureList = mainRepository.getLecturesList();
    List<lecture> lectureList1 = new ArrayList<>();


    int numbers[];

    public addNewGroupWindowController() throws Exception {
    }

    public void initialize() throws Exception {
        List<String> listForTitle = new ArrayList<>();
        for(lecture lecture : lectureList){
            listForTitle.add(lecture.getLectureTitle());
        }
        ObservableList<String> listGroup = FXCollections.observableList(listForTitle);
        listView3.setItems(listGroup);

    }


    public void clickSaveButton(ActionEvent actionEvent) throws Exception {
        numbers = new int[lectureList1.size()];
        int i = 0;
        for (lecture lecture : lectureList1) {
            numbers[i] = lecture.getLectureID();
            i++;
        }
        try {
            if (newGroupTitleTextField.getText().isEmpty()) {
                throw new Exception("Ne visi duomenys suvesti!");
            } else {
                mainRepository.addNewGroup(numbers, newGroupTitleTextField.getText().trim());


                confirmButton.getScene().getWindow().hide();
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }

    public void clickButtonNext(ActionEvent actionEvent) throws Exception {
        List<String> listTitle = new ArrayList<>();
        ObservableList<Integer> SelIndexList = listView3.getSelectionModel().getSelectedIndices();

        lectureList1.add(lectureList.get(SelIndexList.get(0)));
        int ID = SelIndexList.get(0);
        lectureList.remove(ID);


        for(lecture lecture : lectureList){
            listTitle.add(lecture.getLectureTitle());
        }

        ObservableList<String> listGroup = FXCollections.observableList(listTitle);
        listView3.setItems(listGroup);

        List<String> listTitle1 = new ArrayList<>();
        for(lecture d : lectureList1){
            listTitle1.add(d.getLectureTitle());
        }

        ObservableList<String> listGroup1 = FXCollections.observableList(listTitle1);
        listView4.setItems(listGroup1);


    }
}
