package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import Backend.user;
import Date.mainRepository;

public class studentInfoWindowController {


    @FXML
    private Label nameLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label groupTitleLabel;

    @FXML
    private Button deleteUserButton;

    @FXML
    private Button deleteUserButton2;


    private user user;
    adminWindowController adminWindowController;

    public void displayStudentInfo(user user, adminWindowController adminWindowController) throws Exception{
        mainRepository mainRepository = new mainRepository();
        nameLabel.setText(user.getName());
        surnameLabel.setText(user.getSurname());
        String title = mainRepository.GetGroupTitleForUser(user.getGroupID());
        int groupID = user.getGroupID();
        this.adminWindowController = adminWindowController;
        this.user = user;
        groupTitleLabel.setText(mainRepository.GetGroupTitleForUser(user.getGroupID()));
    }

    @FXML
    void deleteUserButtonClicked(ActionEvent event) throws Exception {
        mainRepository mainRepository = new mainRepository();
        mainRepository.deleteUser(user);
        adminWindowController.updateGroups();
    }

}
