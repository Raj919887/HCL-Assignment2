package assignment2;


import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;


class InvalidEmployeeDataException extends RuntimeException {
    public InvalidEmployeeDataException(String message) {
        super(message);
    }
}


interface EmployeeOperations {
    void addEmployee();
    void displayAll();
    void searchEmployee();
    void removeEmployee();
    void showNullSupport();
}


class Employee {
    private Integer empId;
    private String empName;
    private String department;
    private Double salary;

    public Employee(Integer empId, String empName, String department, Double salary) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Dept: %s | Salary: %.2f",
                empId, empName, department, salary);
    }
}

class EmployeeService implements EmployeeOperations {

    private HashMap<Integer, Employee> hashMap = new HashMap<>();
    private Hashtable<Integer, Employee> hashtable = new Hashtable<>();
    private TreeMap<Integer, Employee> treeMap = new TreeMap<>();
    Scanner sc = new Scanner(System.in);

    @Override
    public void addEmployee() {
        try {
            System.out.print("Enter Employee ID: ");
            Integer id = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Employee Department: ");
            String dept = sc.nextLine();

            System.out.print("Enter Salary: ");
            Double sal = Double.parseDouble(sc.nextLine());

            validateEmployee(id, name, dept, sal);

            Employee emp = new Employee(id, name, dept, sal);
            hashMap.put(id, emp);
            hashtable.put(id, emp);
            treeMap.put(id, emp);

            System.out.println("Employee Added Successfully!");

        } catch (InvalidEmployeeDataException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Invalid Input Format!");
        }
    }

    private void validateEmployee(Integer id, String name, String dept, Double sal) {
        if (id == null || id < 1)
            throw new InvalidEmployeeDataException("Invalid Employee ID!");

        if (!Pattern.matches("[A-Za-z ]{3,30}", name))
            throw new InvalidEmployeeDataException("Invalid Name. Only alphabets allowed!");

        if (!Pattern.matches("[A-Za-z ]{2,20}", dept))
            throw new InvalidEmployeeDataException("Invalid Department Name!");

        if (sal < 1000)
            throw new InvalidEmployeeDataException("Salary too low!");
    }

    @Override
    public void displayAll() {
        if (hashMap.isEmpty()) {
            System.out.println("No Employee Records Found!");
            return;
        }

        System.out.println("\n========= Employees in HashMap =========");
        hashMap.values().forEach(System.out::println);

        System.out.println("\n========= Employees in Hashtable =========");
        hashtable.values().forEach(System.out::println);

        System.out.println("\n========= Employees in TreeMap =========");
        treeMap.values().forEach(System.out::println);
    }

    @Override
    public void searchEmployee() {
        try {
            System.out.print("Enter Employee ID to Search: ");
            int id = Integer.parseInt(sc.nextLine());

            Employee emp = hashMap.get(id);
            System.out.println(emp != null ? "Employee Found: " + emp : "Record Not Found!");

        } catch (NumberFormatException ex) {
            System.out.println("Invalid Employee ID format!");
        }
    }

    @Override
    public void removeEmployee() {
        try {
            System.out.print("Enter Employee ID to Remove: ");
            int id = Integer.parseInt(sc.nextLine());

            if (hashMap.containsKey(id)) {
                hashMap.remove(id);
                hashtable.remove(id);
                treeMap.remove(id);
                System.out.println("Record Removed Successfully!");
            } else {
                System.out.println("Employee ID Not Present!");
            }

        } catch (NumberFormatException ex) {
            System.out.println("Invalid Employee ID!");
        }
    }

    @Override
    public void showNullSupport() {
        System.out.println("\n===== Null Key / Value Support Demo =====");

        hashMap.put(null, new Employee(null, "NullGuy", "NA", 0.0));
        hashMap.put(200, null);

        System.out.println("HashMap allows null key & value:");
        System.out.println(hashMap);

        try {
            hashtable.put(null, null);
        } catch (Exception e) {
            System.out.println("Hashtable DOES NOT support null key/value!");
        }

        try {
            treeMap.put(null, null);
        } catch (Exception e) {
            System.out.println("TreeMap DOES NOT allow null key!");
        }
    }
}



public class UseEmployee {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EmployeeOperations service = new EmployeeService();

        while (true) {
            System.out.println("\n=========== Employee Record System ===========");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All");
            System.out.println("3. Search Employee");
            System.out.println("4. Remove Employee");
            System.out.println("5. Show Null Key/Value Support");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            String ch = sc.nextLine();

            switch (ch) {
                case "1" -> service.addEmployee();
                case "2" -> service.displayAll();
                case "3" -> service.searchEmployee();
                case "4" -> service.removeEmployee();
                case "5" -> service.showNullSupport();
                case "6" -> {
                    System.out.println("Program terminated...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid Choice!");
            }
        }
    }
}
