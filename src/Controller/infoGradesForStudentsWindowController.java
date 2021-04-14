package Controller;

import Backend.user;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class infoGradesForStudentsWindowController {

    @FXML
    private Label gradeLabel;

    @FXML
    private Label LectureTitle;

    @FXML
    private Label nameSurnameLabel;

    public void display(user user, String lectureTitle, int grade) {
        nameSurnameLabel.setText(user.getName() + " " + user.getSurname());
        LectureTitle.setVisible(true);
        LectureTitle.setText(lectureTitle);
        if (grade == 0) {
            gradeLabel.setText("(pažymio nėra)");
        } else {
            gradeLabel.setText(String.valueOf(grade));
        }
    }
}
