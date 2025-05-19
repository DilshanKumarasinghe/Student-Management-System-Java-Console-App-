import java.util.List;
import java.util.Scanner;

public class Main {
    private static StudentManagementSystem sms = new StudentManagementSystem();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    viewStudent();
                    break;
                case 5:
                    viewAllStudents();
                    break;
                case 6:
                    System.out.println("Thank you for using Student Management System!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Student Management System ===");
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. View Student");
        System.out.println("5. View All Students");
        System.out.println("6. Exit");
    }

    private static void addStudent() {
        System.out.println("\n=== Add Student ===");
        int id = getIntInput("Enter student ID: ");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        int age = getIntInput("Enter student age: ");
        System.out.print("Enter student grade: ");
        String grade = scanner.nextLine();

        Student student = new Student(id, name, age, grade);
        sms.addStudent(student);
        System.out.println("Student added successfully!");
    }

    private static void updateStudent() {
        System.out.println("\n=== Update Student ===");
        int id = getIntInput("Enter student ID to update: ");
        Student student = sms.getStudent(id);

        if (student != null) {
            System.out.print("Enter new name (press Enter to keep current): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                student.setName(name);
            }

            String ageStr = getStringInput("Enter new age (press Enter to keep current): ");
            if (!ageStr.isEmpty()) {
                student.setAge(Integer.parseInt(ageStr));
            }

            System.out.print("Enter new grade (press Enter to keep current): ");
            String grade = scanner.nextLine();
            if (!grade.isEmpty()) {
                student.setGrade(grade);
            }

            if (sms.updateStudent(id, student)) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Failed to update student.");
            }
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void deleteStudent() {
        System.out.println("\n=== Delete Student ===");
        int id = getIntInput("Enter student ID to delete: ");
        
        if (sms.deleteStudent(id)) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void viewStudent() {
        System.out.println("\n=== View Student ===");
        int id = getIntInput("Enter student ID: ");
        Student student = sms.getStudent(id);

        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void viewAllStudents() {
        System.out.println("\n=== All Students ===");
        List<Student> students = sms.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found!");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
} 