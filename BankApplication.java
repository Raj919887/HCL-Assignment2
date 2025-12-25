package aasignment2;

interface BankOperations {
    void deposit(double amount);
    void withdraw(double amount) throws InsufficientBalanceException;
    double getBalance();
}

interface CustomerOperations {
    void displayCustomerDetails();
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}


class BankAccount implements BankOperations, CustomerOperations {

    private int accNumber;
    private String custName;
    private double balance;


    public BankAccount(int accountNumber, String customerName, double balance) {
        this.accNumber = accountNumber;
        this.custName = customerName;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        System.out.println("Deposited: " + amount);
    }


    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        balance -= amount;
        System.out.println("Withdrawn: " + amount);
    }


    public double getBalance() {
        return balance;
    }


    public void displayCustomerDetails() {
        System.out.println("Account Number : " + accNumber);
        System.out.println("Customer Name  : " + custName);
        System.out.println("Balance        : " + balance);
    }
}

public class BankApplication {
    public static void main(String[] args) {

        BankAccount account = new BankAccount(101, "Raj Jaiswal", 5000);

        try {
            account.displayCustomerDetails();
            account.deposit(2000);
            account.withdraw(3000);
            account.withdraw(6000);
        }
        catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (IllegalArgumentException e) {
            System.out.println("Invalid Input: " + e.getMessage());
        }
        finally {
            System.out.println("Final Balance: " + account.getBalance());
        }
    }
}
