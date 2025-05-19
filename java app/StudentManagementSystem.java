import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystem {
    private List<Student> students;
    private static final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadStudents();
    }

    // Add a new student
    public void addStudent(Student student) {
        students.add(student);
        saveStudents();
    }

    // Update student information
    public boolean updateStudent(int id, Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.set(i, updatedStudent);
                saveStudents();
                return true;
            }
        }
        return false;
    }

    // Delete a student
    public boolean deleteStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                saveStudents();
                return true;
            }
        }
        return false;
    }

    // Get student by ID
    public Student getStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Get all students
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    // Save students to file
    private void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    // Load students from file
    @SuppressWarnings("unchecked")
    private void loadStudents() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                students = (List<Student>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading students: " + e.getMessage());
                students = new ArrayList<>();
            }
        }
    }
} 