package Backend;

public class cons {

    public static final String schemaTitle = "praktika1";

    public static final String userTable = "usertable";
    public static final String userTable_userID = "id";
    public static final String userTable_Name = "name";
    public static final String userTable_Surname = "surname";
    public static final String userTable_Gender = "gender";
    public static final String userTable_lectureID = "dalykoId";
    public static final String userTable_groupID = "grupesId";
    public static final String userTable_username = "username";
    public static final String userTable_password = "password";
    public static final String userTable_accountType = "type";
    public static final String userTable_Rank = "laipsnis";

    public static final String lectureTable = "dalykas";
    public static final String lectureTable_lectureID = "id";
    public static final String lectureTable_lectureTitle = "pavadinimas";
    public static final String lectureTable_lectureDescription = "aprasymas";
    
    public static final String group_lectureTable = "groupdal";
    public static final String group_lectureTable_groupLectureID = "id";
    public static final String group_lectureTable_groupID = "idGroup";
    public static final String group_lectureTable_lectureID = "idDal";

    public static final String groupTable = "grupe";
    public static final String groupTable_groupID = "id";
    public static final String groupTable_groupTitle = "pavadinimas";

    public static final String gradeTable = "pazymis";
    public static final String gradeTable_gradeID = "id";
    public static final String gradeTable_lectureID = "idDalyko";
    public static final String gradeTable_studentID = "idStudentas";
    public static final String gradeTable_grade = "pazymis";

}
