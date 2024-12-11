# Final-Project


# Course Grade Predictor

## Problem Solved
The **Course Grade Predictor** helps students track their current grades, predict their final grade, and convert numeric grades to letter grades. The program allows users to add, edit, and delete grades, then calculates and converts the average grade to a letter grade.

## How I Used Core Concepts

- **Arrays**: Used for storing current grades in a fixed-size structure.
- **ArrayLists**: Used for managing a flexible list of hypothetical grades.
- **Recursion**: Employed for validating user inputs, ensuring correct values are entered for grades and menu options.
- **File Persistence**: Grades are saved to and loaded from text files (`current_grades.txt` and `hypothetical_grades.txt`) to maintain data between sessions.
- **Exception Handling**: Implemented `try-catch` blocks to handle invalid inputs, such as incorrect grade formats or invalid menu selections.

## How to Run the Program

1. Download or clone the repository.
2. Open a terminal and navigate to the directory where the program is stored.
3. Compile the program:
   ```bash
   javac CourseGradePredictor.java

4. Run the program:
    ```bash
    java CourseGradePredictor

