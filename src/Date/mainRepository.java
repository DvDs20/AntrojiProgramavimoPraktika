package Date;

import Backend.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class mainRepository {
    private static user LogInUser;
    DataBaseSchemaHandler dataBaseSchemaHandler = new DataBaseSchemaHandler();


    List<user> userList;
    List<group> groupList;
    List<lecture> lectureList;

    public void register(user user) throws Exception {
        String sql = "SELECT * FROM " + cons.schemaTitle + "." + cons.userTable + " WHERE " + cons.userTable_username + " =?";
        PreparedStatement preparedStatement = dataBaseSchemaHandler.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, user.getUsername());
        ResultSet resultSet = preparedStatement.executeQuery();

        int number = 0;
        while (resultSet.next()) {
            number++;
        }

        if (number > 0) {
            throw new Exception("User are in System");
        }

        String sql1 = "INSERT INTO " + cons.schemaTitle + "." + cons.userTable + " (" + cons.userTable_Name + ", " + cons.userTable_Surname + ", " +
                cons.userTable_Gender + ", " + cons.userTable_lectureID + ", " + cons.userTable_groupID +
                ", " + cons.userTable_username + ", " + cons.userTable_password + ") VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement1 = dataBaseSchemaHandler.getConnection().prepareStatement(sql1);
        preparedStatement1.setString(1,user.getName());
        preparedStatement1.setString(2, user.getSurname());
        preparedStatement1.setString(3,user.getGender());
        preparedStatement1.setInt(4,user.getLessonID());
        preparedStatement1.setInt(5,user.getGroupID());
        preparedStatement1.setString(6,user.getUsername());
        preparedStatement1.setString(7,user.getPassword());

        preparedStatement1.executeUpdate();
        preparedStatement1.close();
    }

    public user Login(String username, String password) throws Exception{
        user user1 = null;

        String sql = "SELECT * FROM " + cons.userTable + " WHERE " + cons.userTable_username +
                "=? AND " + cons.userTable_password + "=?";

        PreparedStatement preparedStatement = dataBaseSchemaHandler.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);

        ResultSet resultSet = preparedStatement.executeQuery();
        int number = 0;

        while(resultSet.next()){
            number++;
            int userID = resultSet.getInt(cons.userTable_userID);
            String name = resultSet.getString(cons.userTable_Name);
            String surname = resultSet.getString(cons.userTable_Surname);
            String gender = resultSet.getString(cons.userTable_Gender);
            int lectureID = resultSet.getInt(cons.userTable_lectureID);
            int groupID = resultSet.getInt(cons.userTable_groupID);
            String username1 = resultSet.getString(cons.userTable_username);
            String password1 = null;
            int accountType = resultSet.getInt(cons.userTable_accountType);
            user1 = new user(name,surname,gender,username,password,userID,accountType,groupID,lectureID);
        }
        resultSet.close();
        if(number <= 0){
            throw new Exception("Blogai suvedėte duomenis!");
        }
        LogInUser = user1;
    return user1;
    }

    public void changePassword(String newPassword, String oldPassword, String confPassword) throws Exception{
        String sqlPassword = "SELECT " + cons.userTable_password + " FROM " + cons.schemaTitle + "." + cons.userTable + " WHERE " +
                cons.userTable_userID + " =?";
        PreparedStatement prST1 = dataBaseSchemaHandler.getConnection().prepareStatement(sqlPassword);
        prST1.setString(1,String.valueOf(LogInUser.getUserID()));
        ResultSet resSet = prST1.executeQuery();

        String password = null;
        while(resSet.next()){
            password = resSet.getString("password");
        }

        prST1.close();

        if(oldPassword.equals(password)) {
            if (newPassword.equals(confPassword)) {
                String sql = "UPDATE " + cons.schemaTitle + "." + cons.userTable + " SET " +
                        cons.userTable_password + " =? WHERE (" + cons.userTable_userID + " =?)";
                PreparedStatement prST = dataBaseSchemaHandler.getConnection().prepareStatement(sql);
                prST.setString(1, newPassword);
                prST.setString(2, String.valueOf(LogInUser.getUserID()));

                prST.executeUpdate();
                prST.close();
            } else
                throw new Exception("Neteisingas pakartotinis slaptazodis");
        }else
            throw new Exception("neteisingas senas slaptazodis");
    }


    public void addNewTeacher(user user) throws Exception {
        String sql = "INSERT INTO " + cons.schemaTitle + "." +
                cons.userTable + " (" + cons.userTable_Name + ", " + cons.userTable_Surname + ", " +
                cons.userTable_Gender + ", " + cons.userTable_lectureID + ", " + cons.userTable_groupID +
                ", " + cons.userTable_username + ", " + cons.userTable_password + "," +
                cons.userTable_accountType + ", " + cons.userTable_Rank +
                ") VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = dataBaseSchemaHandler.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getGender());
            preparedStatement.setInt(4, user.getLessonID());
            preparedStatement.setString(5, null);
            preparedStatement.setString(6, user.getName());
            preparedStatement.setString(7, user.getSurname());
            preparedStatement.setString(8, String.valueOf(1));
            preparedStatement.setString(9,user.getRank());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null, exception);
        }
    }

    public void addNewStudent(user user) throws Exception{
        String sql = "INSERT INTO " + cons.schemaTitle + "." +
                cons.userTable + " (" + cons.userTable_Name + ", " + cons.userTable_Surname + ", " +
                cons.userTable_Gender + ", " + cons.userTable_lectureID + ", " + cons.userTable_groupID +
                ", " + cons.userTable_username + ", " + cons.userTable_password + " , " +
                cons.userTable_accountType + ", " + cons.userTable_Rank + ") VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement prST = dataBaseSchemaHandler.getConnection().prepareStatement(sql);
            prST.setString(1, user.getName());
            prST.setString(2, user.getSurname());
            prST.setString(3, user.getGender());
            prST.setString(4, null);
            prST.setInt(5, user.getGroupID());
            System.out.println(user.getGroupID());
            prST.setString(6, user.getName());
            prST.setString(7, user.getSurname());
            prST.setString(8, String.valueOf(0));
            prST.setString(9, null);

            prST.executeUpdate();
            prST.close();
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null, exception);
        }

    }

    public void deleteLecture(lecture dal) throws Exception{
        String sql ="DELETE FROM " + cons.schemaTitle + "." + cons.lectureTable +
                " WHERE (" + cons.lectureTable_lectureID + " =?)";

        PreparedStatement preparedStatement = dataBaseSchemaHandler.getConnection().prepareStatement(sql);

        preparedStatement.setString(1,String.valueOf(dal.getLectureID()));
        preparedStatement.executeUpdate();
    }

    public void deleteUser(user user) throws Exception{
        String sql ="DELETE FROM " + cons.schemaTitle + "." + cons.userTable +
                " WHERE (" + cons.userTable_userID + " =?)";

        PreparedStatement preparedStatement = dataBaseSchemaHandler.getConnection().prepareStatement(sql);

        preparedStatement.setString(1,String.valueOf(user.getUserID()));
        preparedStatement.executeUpdate();
    }



    public void setLoginUser(user logInUser) {
        this.LogInUser = logInUser;
    }

    public List<group> getGroupList() throws Exception{
        groupList = new ArrayList<>();

        String sql = "SELECT * FROM " + cons.groupTable;
        PreparedStatement prST = dataBaseSchemaHandler.getConnection().prepareStatement(sql);
        ResultSet resSet = prST.executeQuery();
        while(resSet.next()){
            int groupID = resSet.getInt(cons.groupTable_groupID);
        String groupTitle = resSet.getString(cons.groupTable_groupTitle);
        groupList.add(new group(groupID,groupTitle));
        }
        return groupList;

    }

    public List<user> userList(int id) throws Exception {
        userList = new ArrayList<user>();

        String sql = "SELECT * FROM " + cons.schemaTitle + "." + cons.userTable + " WHERE (" + cons.userTable_groupID + " =?)";

        PreparedStatement preparedStatement = dataBaseSchemaHandler.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, String.valueOf(id));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int userID = resultSet.getInt(cons.userTable_userID);
            String name = resultSet.getString(cons.userTable_Name);
            String surname = resultSet.getString(cons.userTable_Surname);
            String gender = resultSet.getString(cons.userTable_Gender);
            int groupID = resultSet.getInt(cons.userTable_groupID);
            userList.add(new user(name, surname, gender,groupID,userID));
        }
        return userList;
    }

    public List<user> userListStud() throws Exception {
        userList = new ArrayList<user>();

        String sql = "SELECT * FROM " + cons.schemaTitle + "." + cons.userTable + " WHERE (" + cons.userTable_groupID + " > 0)";

        PreparedStatement prST = dataBaseSchemaHandler.getConnection().prepareStatement(sql);

        ResultSet resSet = prST.executeQuery();

        while (resSet.next()) {
            int userID = resSet.getInt(cons.userTable_userID);
            String name = resSet.getString(cons.userTable_Name);
            String surname = resSet.getString(cons.userTable_Surname);
            String gender = resSet.getString(cons.userTable_Gender);
            int groupID = resSet.getInt(cons.userTable_groupID);
            userList.add(new user(name, surname, gender, groupID,userID));
        }
        return userList;
    }


    public List<lecture> getLecturesList() throws Exception{
        lectureList = new ArrayList<>();

        String sql = "SELECT * FROM " + cons.lectureTable;
        PreparedStatement preparedStatement = dataBaseSchemaHandler.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            int lectureID = resultSet.getInt(cons.lectureTable_lectureID);
            String lectureTitle = resultSet.getString(cons.lectureTable_lectureTitle);
            String lectureDescription = resultSet.getString(cons.lectureTable_lectureDescription);
            lectureList.add(new lecture(lectureID,lectureTitle,lectureDescription));
        }
        return lectureList;

    }


    public void addNewGroup(int number[], String groupTitle)throws Exception{


        String sql = "INSERT INTO "+cons.schemaTitle + "." + cons.groupTable +  "(" +
                cons.groupTable_groupTitle + ") VALUES (?)";

        PreparedStatement preparedStatement = dataBaseSchemaHandler.getConnection().prepareStatement(sql);

        preparedStatement.setString(1,groupTitle);
        preparedStatement.executeUpdate();

        String sql1 = "SELECT " + cons.groupTable_groupID + " FROM " + cons.groupTable + " WHERE ( " + cons.groupTable_groupTitle +
                " = ?)";

        PreparedStatement preparedStatement1 = dataBaseSchemaHandler.getConnection().prepareStatement(sql1);
        preparedStatement1.setString(1,groupTitle);
        ResultSet resultSet = preparedStatement1.executeQuery();
        int groupID = 0;

        while(resultSet.next()){
            groupID = resultSet.getInt(cons.groupTable_groupID);
        }

        for(int i = 0; i < number.length ; i++){
            addNewLectureINGroup(number[i],groupID);
        }

    }


    public List<group> getGroupIDbyLesson(int lecture) throws Exception{
        List<group> groupArrayList = new ArrayList<>();


        String sql = "SELECT " + cons.group_lectureTable_groupID + " FROM " + cons.schemaTitle + "." +
                cons.group_lectureTable + " WHERE " + cons.group_lectureTable_lectureID + "=?";

        PreparedStatement prST = dataBaseSchemaHandler.getConnection().prepareStatement(sql);
        prST.setString(1,String.valueOf(lecture));

        ResultSet resSetString = prST.executeQuery();
        while(resSetString.next()) {
            String sql1 = "SELECT * FROM " + cons.groupTable + " WHERE " + cons.groupTable_groupID + "=?";
            PreparedStatement preparedStatement = dataBaseSchemaHandler.getConnection().prepareStatement(sql1);
            preparedStatement.setString(1,resSetString.getString(cons.group_lectureTable_groupID));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int groupID = resultSet.getInt(cons.groupTable_groupID);
                String groupTitle = resultSet.getString(cons.groupTable_groupTitle);
                groupArrayList.add(new group(groupID,groupTitle));
            }
        }
        return groupArrayList;
    }


    public void addNewLecture(lecture lecture) throws Exception{
        String sql = "INSERT INTO " + cons.schemaTitle + "." + cons.lectureTable + " (" +
                cons.lectureTable_lectureTitle + ", " + cons.lectureTable_lectureDescription + ") VALUES (?,?)";

        PreparedStatement preparedStatement = dataBaseSchemaHandler.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, lecture.getLectureTitle());
        preparedStatement.setString(2, lecture.getLectureDescription());
        preparedStatement.executeUpdate();
    }

    public int getGrade(int lectureID, int studentID) throws Exception{
        int grade = 0;


        String sql = "SELECT " + cons.gradeTable_grade + " FROM " + cons.schemaTitle + "." + cons.gradeTable +
                " WHERE " + cons.gradeTable_lectureID + "=? AND " + cons.gradeTable_studentID + " =?";
        PreparedStatement prST = dataBaseSchemaHandler.getConnection().prepareStatement(sql);
            prST.setString(1,String.valueOf(lectureID));
            prST.setString(2,String.valueOf(studentID));

            ResultSet resSet = prST.executeQuery();
            while(resSet.next()){
                grade = resSet.getInt(cons.gradeTable_grade);
            }
        return grade;
    }

    public void addNewLectureINGroup(int lecture, int group) throws Exception{
        String sql = "INSERT INTO " + cons.schemaTitle + "." + cons.group_lectureTable + " (" +
                cons.group_lectureTable_groupID + ", " + cons.group_lectureTable_lectureID + ") VALUES (?,?)";

        PreparedStatement preparedStatement = dataBaseSchemaHandler.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,String.valueOf(group));
        preparedStatement.setString(2,String.valueOf(lecture));

        preparedStatement.executeUpdate();

    }

    public void updateMark(String grade,int lectureID,int studentID) throws Exception{
        String sql = "UPDATE " + cons.schemaTitle + "." + cons.gradeTable + " SET " +
                cons.gradeTable_grade + " =? WHERE " + cons.gradeTable_lectureID + " =? AND " +
                cons.gradeTable_studentID + " =?";

        PreparedStatement preparedStatement = dataBaseSchemaHandler.getConnection().prepareStatement(sql);

        preparedStatement.setString(1,grade);
        preparedStatement.setString(2,String.valueOf(lectureID));
        preparedStatement.setString(3,String.valueOf(studentID));

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }


    public List<lecture> getLectureList(int group) throws Exception{
        List<lecture> lectureArrayList = new ArrayList<>();


        String sql = "SELECT " + cons.group_lectureTable_lectureID + " FROM " + cons.schemaTitle + "." +
                cons.group_lectureTable + " WHERE " + cons.group_lectureTable_groupID + "=?";

        PreparedStatement preparedStatement = dataBaseSchemaHandler.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,String.valueOf(group));

        ResultSet resSet = preparedStatement.executeQuery();
        while(resSet.next()) {
            String sql1 = "SELECT * FROM " + cons.lectureTable + " WHERE " + cons.lectureTable_lectureID + "=?";
            PreparedStatement preparedStatement1 = dataBaseSchemaHandler.getConnection().prepareStatement(sql1);
            preparedStatement1.setString(1,resSet.getString(cons.lectureTable_lectureID));

            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                int lectureID = resultSet.getInt(cons.lectureTable_lectureID);
                String lectureTitle = resultSet.getString(cons.lectureTable_lectureTitle);
                String lectureDescription = resultSet.getString(cons.lectureTable_lectureDescription);
                lectureArrayList.add(new lecture(lectureID,lectureTitle,lectureDescription));
            }
        }
        return lectureArrayList;
    }



    public void newGrade(String grade, int lectureID, int studentID) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO " + cons.schemaTitle + "." + cons.gradeTable + " (" +
                cons.gradeTable_lectureID + "," + cons.gradeTable_studentID + "," + cons.gradeTable_grade + ") VALUES (?,?,?)";

        PreparedStatement preparedStatement = dataBaseSchemaHandler.getConnection().prepareStatement(sql);

        preparedStatement.setString(1,String.valueOf(lectureID));
        preparedStatement.setString(2,String.valueOf(studentID));
        preparedStatement.setString(3,grade);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }


    public user userName(int lectureID) throws Exception{
        user user = null;

        String sql = "SELECT * FROM " + cons.schemaTitle + "." + cons.userTable + " WHERE " +
                cons.userTable_lectureID + " =?";
        PreparedStatement preparedStatement = dataBaseSchemaHandler.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,String.valueOf(lectureID));
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            String name = resultSet.getString(cons.userTable_Name);
            String surname = resultSet.getString(cons.userTable_Surname);
            String rank = resultSet.getString(cons.userTable_Rank);
            String gender = resultSet.getString(cons.userTable_Gender);
            int lectureID1 = resultSet.getInt(cons.userTable_lectureID);
            user = new user(name,surname,rank,gender,lectureID1);
        }
        return user;
    }

    public String GetGroupTitleForUser(int id) throws Exception{
        List<group> groups;
        groups = getGroupList();
        String groupTitle = null;


        for(group group : groups){
            if(group.getID() == id){
                groupTitle = group.getGroupTitle();
            }
        }
        return groupTitle;
    }
}