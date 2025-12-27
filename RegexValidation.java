package assignment2;

import java.util.Scanner;
import java.util.regex.Pattern;

public class RegexValidation {

    // Mobile Number Validation
    static boolean validateMobile(String mobile) {
        return Pattern.matches("^[6-9][0-9]{9}$", mobile);
    }

    // Email Validation
    static boolean validateEmail(String email) {
        return Pattern.matches("^[a-zA-Z0-9_$#.-]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$", email);
    }

    // Username Validation
    static boolean validateUsername(String username) {
        return Pattern.matches("^[A-Za-z0-9]{5,15}$", username);
    }

    // Password Validation
    static boolean validatePassword(String password) {
        return Pattern.matches(
                "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%!]).{8,}$",
                password
        );
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        try {
            do {
                System.out.println("\n--- REGEX Input Validation Menu ---");
                System.out.println("1. Validate Mobile Number");
                System.out.println("2. Validate Email ID");
                System.out.println("3. Validate Username");
                System.out.println("4. Validate Password");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {

                    case 1:
                        System.out.print("Enter Mobile Number: ");
                        String mobile = sc.nextLine();
                        if (validateMobile(mobile))
                            System.out.println("✔ Valid Mobile Number. Welcome!");
                        else
                            System.out.println("✖ Invalid Mobile Number!");
                        break;

                    case 2:
                        System.out.print("Enter Email ID: ");
                        String email = sc.nextLine();
                        if (validateEmail(email))
                            System.out.println("✔ Valid Email ID. Welcome!");
                        else
                            System.out.println("✖ Invalid Email ID!");
                        break;

                    case 3:
                        System.out.print("Enter Username: ");
                        String username = sc.nextLine();
                        if (validateUsername(username))
                            System.out.println("✔ Valid Username. Welcome!");
                        else
                            System.out.println("✖ Invalid Username!");
                        break;

                    case 4:
                        System.out.print("Enter Password: ");
                        String password = sc.nextLine();
                        if (validatePassword(password))
                            System.out.println("✔ Strong Password. Welcome!");
                        else
                            System.out.println("✖ Weak Password!");
                        break;

                    case 5:
                        System.out.println("Thank you! Exiting program.");
                        break;

                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } while (choice != 5);

        } catch (Exception e) {
            System.out.println("Error: Invalid input!");
        } finally {
            sc.close();
        }
    }
}

