package Backend;

public class grade {
    protected int gradeID;
    protected int LectureID;
    protected int StudentID;
    protected int grade;

    public grade(int gradeID, int LectureID, int StudentID, int grade){
        this.gradeID = gradeID;
        this.LectureID = LectureID;
        this.StudentID = StudentID;
        this.grade = grade;
    }

    public int getGradeID() {
        return gradeID;
    }

    public int getLectureID() {
        return LectureID;
    }

    public int getStudentID() {
        return StudentID;
    }

    public int getGrade() {
        return grade;
    }
}
