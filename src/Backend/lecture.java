package Backend;

public class lecture {
    protected int lectureID;
    protected String lectureTitle;
    protected String lectureDescription;

    public lecture(int lectureID, String lectureTitle, String lectureDescription){
        this.lectureDescription = lectureDescription;
        this.lectureID = lectureID;
        this.lectureTitle = lectureTitle;
    }

    public lecture(String lectureTitle, String lectureDescription){
        this.lectureDescription = lectureDescription;
        this.lectureTitle = lectureTitle;
    }

    public int getLectureID() {
        return lectureID;
    }

    public String getLectureTitle() {
        return lectureTitle;
    }

    public String getLectureDescription() {
        return lectureDescription;
    }
}
