package practiceLessons;

public class Student {

    //properties (similar to variables)
    private String name;
    private double[] grades = new double[4];

    //behaviors (methods)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getGrades() {
        return grades;
    }

    public void setGrades(double[] grades) {
        this.grades = grades;
    }

    public double getGradePointAverage() {
        double totalPoints = 0;

        for (int i = 0; i < grades.length; i++) {
            totalPoints += grades[i];
        }
        return totalPoints/grades.length;
    }
}
