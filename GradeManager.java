import java.util.ArrayList;

public class GradeManager {

    private ArrayList<Double> currentGrades;

    public GradeManager() {
        this.currentGrades = new ArrayList<>();
    }

    public ArrayList<Double> getCurrentGrades() {
        return currentGrades;
    }

    public void addGrade(double grade) {
        currentGrades.add(grade);
    }

    public void editGrade(int index, double newGrade) {
        currentGrades.set(index, newGrade);
    }

    public void deleteGrade(int index) {
        currentGrades.remove(index);
    }

    public double calculateAverage() {
        double total = 0;
        for (double grade : currentGrades) {
            total += grade;
        }
        return (currentGrades.size() > 0) ? total / currentGrades.size() : 0;
    }
}
