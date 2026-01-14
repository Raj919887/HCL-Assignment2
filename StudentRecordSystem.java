package assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;

// ---------- Interface ----------
interface StudentOperation {
    void addStudent();
    void displayStudents();
    void removeStudent();
    void searchStudent();
}

// ---------- Student Class ----------
class Students {
    private int rollNo;
    private String name;
    private String email;

    public Students(int rollNo, String name, String email) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
    }

    public int getRollNo() {
        return rollNo;
    }

    @Override
    public String toString() {
        return String.format("Roll: %d | Name: %s | Email: %s", rollNo, name, email);
    }
}

// ---------- Manager Class ----------
class StudentManager implements StudentOperation{

    private List<Students> studentList = new ArrayList<>();
    private Vector<Students> deleteStudentList = new Vector<>();
    Scanner sc = new Scanner(System.in);

    @Override
    public void addStudent() {
        try {
            System.out.print("Enter Roll No: ");
            int roll = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            if (!Pattern.matches("[a-zA-Z ]{3,30}", name)) {
                throw new Exception("Invalid Name. Only letters allowed!");
            }

            if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) {
                throw new Exception("Invalid Email format!");
            }

            studentList.add(new Students(roll, name, email));
            System.out.println("Student Added Successfully!");

        } catch (NumberFormatException ex) {
            System.out.println("Invalid numeric input!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void displayStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No Student Records Available.");
            return;
        }

        System.out.println("\n================ Student Records ================");
        studentList.forEach(System.out::println);
    }

    @Override
    public void removeStudent() {
        try {
            System.out.print("Enter Roll No to Remove: ");
            int roll = Integer.parseInt(sc.nextLine());

            Students stu = searchByRoll(roll);
            if (stu != null) {
                studentList.remove(stu);
                deleteStudentList.add(stu);
                System.out.println("Record Removed Successfully!");
            } else {
                System.out.println("Record Not Found!");
            }

        } catch (NumberFormatException ex) {
            System.out.println("Invalid roll number!");
        }
    }

    @Override
    public void searchStudent() {
        try {
            System.out.print("Enter Roll No to Search: ");
            int roll = Integer.parseInt(sc.nextLine());

            Students result = searchByRoll(roll);
            if (result != null) {
                System.out.println("Student Found:");
                System.out.println(result);
            } else {
                System.out.println("No record found.");
            }

        } catch (NumberFormatException ex) {
            System.out.println("Invalid roll number!");
        }
    }

    private Students searchByRoll(int roll) {
        return studentList.stream()
                .filter(s -> s.getRollNo() == roll)
                .findFirst()
                .orElse(null);
    }
}

// ---------- Main Class (ONLY public class) ----------
public class StudentRecordSystem {

    public static void main(String[] args) {

        StudentOperation manager = new StudentManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=========== Student Management System ===========");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");

            String ch = sc.nextLine();

            switch (ch) {
                case "1" -> manager.addStudent();
                case "2" -> manager.displayStudents();
                case "3" -> manager.removeStudent();
                case "4" -> manager.searchStudent();
                case "5" -> {
                    System.out.println("Exiting Program...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid Choice!");
            }
        }
    }
}

