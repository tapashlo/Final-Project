import java.util.ArrayList;
import java.util.Scanner;

public class CourseGradePredictor {

    public static void main(String[] args) {
        GradeManager gradeManager = new GradeManager();
        gradeManager.getCurrentGrades().addAll(FileManager.loadGrades());

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            displayMenu();
            choice = validateMenuChoice(scanner);
            handleMenuChoice(choice, scanner, gradeManager);
        } while (choice != 5);

        FileManager.saveGrades(gradeManager.getCurrentGrades());
        System.out.println("Goodbye!");
    }

    private static void displayMenu() {
        System.out.println("\n--- Course Grade Predictor Menu ---");
        System.out.println("1. View Current Grades");
        System.out.println("2. Add/Edit/Delete Current Grades");
        System.out.println("3. Convert Grades to Letter Grade");
        System.out.println("4. Predict Final Grade");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int validateMenuChoice(Scanner scanner) {
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > 5) {
                throw new IllegalArgumentException("Invalid menu choice. Please choose a number between 1 and 5.");
            }
            return choice;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return validateMenuChoice(scanner); // Recursive call for invalid input
        }
    }

    private static void handleMenuChoice(int choice, Scanner scanner, GradeManager gradeManager) {
        switch (choice) {
            case 1:
                viewGrades(gradeManager);
                break;
            case 2:
                manageCurrentGrades(scanner, gradeManager);
                break;
            case 3:
                convertGradesToLetter(scanner, gradeManager);
                break;
            case 4:
                predictFinalGrade(gradeManager);
                break;
            case 5:
                break;
            default:
                System.out.println("Unexpected error.");
        }
    }

    private static void viewGrades(GradeManager gradeManager) {
        System.out.println("\n--- Current Grades ---");
        for (int i = 0; i < gradeManager.getCurrentGrades().size(); i++) {
            System.out.printf("%d. %.2f\n", i + 1, gradeManager.getCurrentGrades().get(i));
        }
    }

    private static void manageCurrentGrades(Scanner scanner, GradeManager gradeManager) {
        System.out.println("\n--- Manage Current Grades ---");
        System.out.println("1. Add Grade");
        System.out.println("2. Edit Grade");
        System.out.println("3. Delete Grade");
        System.out.print("Choose an option: ");

        int choice = validateMenuChoice(scanner);
        switch (choice) {
            case 1:
                addCurrentGrade(scanner, gradeManager);
                break;
            case 2:
                editCurrentGrade(scanner, gradeManager);
                break;
            case 3:
                deleteCurrentGrade(scanner, gradeManager);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void addCurrentGrade(Scanner scanner, GradeManager gradeManager) {
        System.out.print("Enter the grade to add (0-100): ");
        double grade = validateGradeInput(scanner);
        gradeManager.addGrade(grade);
        System.out.println("Grade added successfully.");
    }

    private static void editCurrentGrade(Scanner scanner, GradeManager gradeManager) {
        System.out.print("Enter the index of the grade to edit: ");
        int index = validateIndex(scanner, gradeManager.getCurrentGrades().size());
        System.out.print("Enter the new grade (0-100): ");
        double grade = validateGradeInput(scanner);
        gradeManager.editGrade(index, grade);
        System.out.println("Grade updated successfully.");
    }

    private static void deleteCurrentGrade(Scanner scanner, GradeManager gradeManager) {
        System.out.print("Enter the index of the grade to delete: ");
        int index = validateIndex(scanner, gradeManager.getCurrentGrades().size());
        gradeManager.deleteGrade(index);
        System.out.println("Grade deleted successfully.");
    }

    // Updated to tally grades and give letter grade for the average
    private static void convertGradesToLetter(Scanner scanner, GradeManager gradeManager) {
        if (gradeManager.getCurrentGrades().isEmpty()) {
            System.out.println("No grades available to convert.");
            return;
        }

        double average = gradeManager.calculateAverage();
        String letterGrade = GradeConverter.convertToLetterGrade(average);

        System.out.printf("\nYour average grade is: %.2f\n", average);
        System.out.println("Your letter grade is: " + letterGrade);
    }

    private static void predictFinalGrade(GradeManager gradeManager) {
        double average = gradeManager.calculateAverage();
        System.out.printf("\nPredicted Final Grade: %.2f\n", average);
    }

    private static double validateGradeInput(Scanner scanner) {
        try {
            double grade = Double.parseDouble(scanner.nextLine());
            if (grade < 0 || grade > 100) {
                throw new IllegalArgumentException("Grade must be between 0 and 100.");
            }
            return grade;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return validateGradeInput(scanner); // Recursive call for invalid input
        }
    }

    private static int validateIndex(Scanner scanner, int size) {
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("Invalid index. Please choose a valid option.");
            }
            return index;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return validateIndex(scanner, size); // Recursive call for invalid input
        }
    }
}
