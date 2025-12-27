package assignment2;

import java.util.*;
import java.util.regex.Pattern;


interface StudentOperations {
    void addStudent();
    void displayStudents();
    void removeStudent(int id);
    void searchStudent(int id);
    void sortStudentsByMarks();
    void convertHashMapToTreeMap();
    void countStudentsCourseWise();
    void displayCourses();
}


class Student {
    int id;
    String name;
    String course;
    int marks;

    Student(int id, String name, String course, int marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    public String toString() {
        return id + "\t" + name + "\t" + course + "\t" + marks;
    }
}


class UniversityManager implements StudentOperations {


    List<Student> studentList = new ArrayList<>();
    Vector<Student> studentVector = new Vector<>();
    Stack<Student> studentStack = new Stack<>();

    HashMap<Integer, Student> studentMap = new HashMap<>();
    Hashtable<String, Integer> courseCount = new Hashtable<>();
    Set<String> courseSet = new HashSet<>();

    Scanner sc = new Scanner(System.in);


    boolean validId(String id) {
        return Pattern.matches("\\d+", id);
    }

    boolean validName(String name) {
        return Pattern.matches("[A-Za-z ]+", name);
    }

    boolean validCourse(String course) {
        return Pattern.matches("[A-Za-z]+", course);
    }

    @Override
    public void addStudent() {
        try {
            System.out.print("Enter Student ID: ");
            String sid = sc.nextLine();
            if (!validId(sid))
                throw new Exception("Invalid ID!");

            int id = Integer.parseInt(sid);

            if (studentMap.containsKey(id))
                throw new Exception("Duplicate Student ID!");

            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            if (!validName(name))
                throw new Exception("Invalid Name!");

            System.out.print("Enter Course: ");
            String course = sc.nextLine();
            if (!validCourse(course))
                throw new Exception("Invalid Course!");

            System.out.print("Enter Marks: ");
            int marks = sc.nextInt();
            sc.nextLine();

            Student s = new Student(id, name, course, marks);

            studentList.add(s);
            studentVector.add(s);
            studentStack.push(s);
            studentMap.put(id, s);

            courseSet.add(course);
            courseCount.put(course, courseCount.getOrDefault(course, 0) + 1);

            System.out.println("Student added successfully.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            sc.nextLine();
        }
    }

    @Override
    public void displayStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No student records.");
            return;
        }

        System.out.println("\nID\tName\tCourse\tMarks");
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    @Override
    public void removeStudent(int id) {
        Student s = studentMap.remove(id);

        if (s != null) {
            studentList.remove(s);
            studentVector.remove(s);
            courseCount.put(s.course, courseCount.get(s.course) - 1);
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    @Override
    public void searchStudent(int id) {
        Student s = studentMap.get(id);
        if (s != null) {
            System.out.println("\nStudent Found:");
            System.out.println("ID: " + s.id);
            System.out.println("Name: " + s.name);
            System.out.println("Course: " + s.course);
            System.out.println("Marks: " + s.marks);
        } else {
            System.out.println("Student not found.");
        }
    }

    @Override
    public void sortStudentsByMarks() {
        studentList.sort(Comparator.comparingInt(st -> st.marks));
        System.out.println("Students sorted by marks.");
        displayStudents();
    }

    @Override
    public void convertHashMapToTreeMap() {
        TreeMap<Integer, Student> treeMap = new TreeMap<>(studentMap);
        System.out.println("\nTreeMap (Sorted by Student ID):");
        for (Map.Entry<Integer, Student> e : treeMap.entrySet()) {
            System.out.println(e.getValue());
        }
    }

    @Override
    public void countStudentsCourseWise() {
        System.out.println("\nCourse-wise Student Count:");
        for (Map.Entry<String, Integer> e : courseCount.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
    }

    @Override
    public void displayCourses() {
        System.out.println("\nUnique Courses:");
        for (String course : courseSet) {
            System.out.println(course);
        }
    }
}


public class UniversityApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentOperations manager = new UniversityManager();

        int choice;

        do {
            System.out.println("\n--- University Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Remove Student by ID");
            System.out.println("4. Search Student by ID");
            System.out.println("5. Sort Students by Marks");
            System.out.println("6. Convert HashMap to TreeMap");
            System.out.println("7. Count Students Course-wise");
            System.out.println("8. Display All Courses");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        sc.nextLine();
                        manager.addStudent();
                        break;
                    case 2:
                        manager.displayStudents();
                        break;
                    case 3:
                        System.out.print("Enter ID: ");
                        manager.removeStudent(sc.nextInt());
                        break;
                    case 4:
                        System.out.print("Enter ID: ");
                        manager.searchStudent(sc.nextInt());
                        break;
                    case 5:
                        manager.sortStudentsByMarks();
                        break;
                    case 6:
                        manager.convertHashMapToTreeMap();
                        break;
                    case 7:
                        manager.countStudentsCourseWise();
                        break;
                    case 8:
                        manager.displayCourses();
                        break;
                    case 9:
                        System.out.println("Exiting program...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                sc.nextLine();
                choice = 0;
            }
        } while (choice != 9);

        sc.close();
    }
}

