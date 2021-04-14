package Backend;

public class user extends person {
    protected String username;
    protected String password;
    protected int userID;
    protected int accountType;
    protected int groupID;
    protected int lectureID;
    protected  String rank;

    public user(String name, String surname, String gender, String username, String password, int userID, int accountType, int groupID, int lectureID){
        super(name,surname,gender);

        this.userID = userID;
        this.username = username;
        this.password = password;
        this.accountType = accountType;
        this.groupID = groupID;
        this.lectureID = lectureID;

    }

    public user(String name, String surname, String gender, int groupID, int userID){
        super(name,surname,gender);
    this.groupID = groupID;
    this.userID = userID;
    }
    public user(String name,String surname,String gender,int groupID){
        super(name,surname,gender);
        this.groupID = groupID;
    }

    public user(String name, String surname, String rank, String gender, int lectureID){
        super(name,surname,gender);
        this.lectureID = lectureID;
        this.rank = rank;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUserID() {
        return userID;
    }

    public int getAccountType() {
        return accountType;
    }

    public int getGroupID() {
        return groupID;
    }

    public int getLessonID() {
        return lectureID;
    }

    public String getRank(){
        return rank;
    }
}
