package Controller;

import Backend.lecture;
import Backend.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import Date.mainRepository;

public class lectureListController {

    @FXML
    private Button deleteLectureButton;

    @FXML
    private Label lectureTitleLabel;

    @FXML
    private Label teacherName;

    @FXML
    private Label lectureDescriptionLabel;



    mainRepository mainRepository =new mainRepository();
    lecture lecture;
    adminWindowController adminWindowController;


    @FXML
    void deleteLectureButtonClicked(ActionEvent event)throws Exception {
        mainRepository.deleteLecture(lecture);
        adminWindowController.updateLectures();
    }

    public void display(lecture lecture, adminWindowController adminWindowController) throws Exception {

        this.lecture = lecture;
        this.adminWindowController = adminWindowController;
        lectureDescriptionLabel.setText(lecture.getLectureDescription());
        lectureTitleLabel.setText(lecture.getLectureTitle());
        user user = mainRepository.userName(lecture.getLectureID());
        if (user != null) {
            teacherName.setText(user.getName() + " " + user.getSurname());
        } else {
            teacherName.setText("(dėstytojas nepriskirtas)");
        }
    }
}
