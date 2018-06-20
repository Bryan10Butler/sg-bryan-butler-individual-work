package classmodeling;

public class School2 {

    private int enrollment;
    private int numberOfTeachers;
    private String[] coursesOffered;
    private String sportsNickname;
    private String[] clubsOffered;
    private String[] studentRoster;

    //Method
    //Student parameter of type student
    public void enrollStudent(Student student){

    }

    //Method
    //Student parameter of type student
    public void unenrollStudent(Student student){

    }

    //Right click
    //Select "Generate"
    //Select "Getters and Setters
    public int getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public int getNumberOfTeachers() {
        return numberOfTeachers;
    }

    public void setNumberOfTeachers(int numberOfTeachers) {
        this.numberOfTeachers = numberOfTeachers;
    }

    public String[] getCoursesOffered() {
        return coursesOffered;
    }

    public void setCoursesOffered(String[] coursesOffered) {
        this.coursesOffered = coursesOffered;
    }

    public String getSportsNickname() {
        return sportsNickname;
    }

    public void setSportsNickname(String sportsNickname) {
        this.sportsNickname = sportsNickname;
    }

    public String[] getClubsOffered() {
        return clubsOffered;
    }

    public void setClubsOffered(String[] clubsOffered) {
        this.clubsOffered = clubsOffered;
    }

    public String[] getStudentRoster() {
        return studentRoster;
    }

    public void setStudentRoster(String[] studentRoster) {
        this.studentRoster = studentRoster;
    }
}//End of class School2
