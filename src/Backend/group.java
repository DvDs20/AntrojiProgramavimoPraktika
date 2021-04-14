package Backend;

import java.util.ArrayList;
import java.util.List;

public class group {

    protected int groupID;
    protected String groupTitle;
    private List<user> userList;

    public group(int groupID, String groupTitle){
        this.groupID = groupID;
        this.groupTitle = groupTitle;
        userList = new ArrayList<user>();

    }

    public int getID() {
        return groupID;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public List<user> getListUser(){
        return userList;
    }
}
