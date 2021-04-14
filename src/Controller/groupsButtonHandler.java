package Controller;

import Backend.group;
import javafx.event.Event;
import javafx.event.EventHandler;

public class groupsButtonHandler<ActionEvent extends Event> implements EventHandler<ActionEvent> {

    private group group;
    private adminWindowController adminWindowController;
    private teacherWindowController teacherWindowController;


    public groupsButtonHandler(group group, adminWindowController adminWindowController){
        this.group = group;
        this.adminWindowController = adminWindowController;
    }

    public groupsButtonHandler(group group, teacherWindowController teacherWindowController){
        this.group = group;
        this.teacherWindowController = teacherWindowController;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            if(adminWindowController !=null)
                adminWindowController.displayUserGroup(group.getID());
            else if(teacherWindowController != null)
                teacherWindowController.displayGradesForTeacher(group.getID());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
