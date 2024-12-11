import java.io.*;
import java.util.ArrayList;

public class FileManager {

    public static void saveGrades(ArrayList<Double> grades) {
        try (ObjectOutputStream currentOutput = new ObjectOutputStream(new FileOutputStream("current_grades.txt"))) {
            currentOutput.writeObject(grades);
        } catch (IOException e) {
            System.out.println("Error saving grades: " + e.getMessage());
        }
    }

    public static ArrayList<Double> loadGrades() {
        ArrayList<Double> grades = new ArrayList<>();
        try (ObjectInputStream currentInput = new ObjectInputStream(new FileInputStream("current_grades.txt"))) {
            grades = (ArrayList<Double>) currentInput.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Data files not found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading grades: " + e.getMessage());
        }
        return grades;
    }
}
