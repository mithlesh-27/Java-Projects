import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ATM {
    private String AccountNumber;//Created object for accountnumber
    private int pin; //Created object for storing pin
    private double balance; //Created object for checking balance
    private List<String> transactionHistory; //Created object for checking history of transactions.

    public ATM(String AccountNumber, int pin, double balance) {
        this.AccountNumber = AccountNumber;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<String>(); // Fixed: added type parameter
    }

    public void checkBalance(int enteredPin) {
        if (enteredPin == pin) {
            System.out.println("Your current balance is: $" + balance);
        } else {
            System.out.println("Invalid PIN. Please try again.");
        }
    }

    public void withdrawCash(int enteredPin, double amount) {
        if (enteredPin == pin) { //To check entered pin correct as default pin or new created pin.
            if (amount <= 0) { // Added check for invalid amount.
                System.out.println("Invalid amount. Please try again.");
            } else if (amount > balance) {
                System.out.println("Insufficient funds. Please try again.");
            } else {
                balance -= amount;
                transactionHistory.add("Withdrawal of $" + amount);
                System.out.println("Withdrawal successful. Your new balance is: $" + balance);
            }
        } else {
            System.out.println("Invalid PIN. Please try again.");
        }
    }

    public void depositCash(int enteredPin, double amount) {
        if (enteredPin == pin) {
            if (amount <= 0) { // Added check for invalid amount
                System.out.println("Invalid amount. Please try again.");
            } else {
                balance += amount;
                transactionHistory.add("Deposit of  $" + amount);
                System.out.println("Deposit successful. Your new balance is: $" + balance);
            }
        } else {
            System.out.println("Invalid PIN. Please try again.");
        }
    }

    public void changePin(int oldPin, int newPin) {
        if (oldPin == pin) {
            if (oldPin == newPin) { // Added check for same PIN
                System.out.println("New PIN cannot be the same as the old PIN. Please try again.");
            } else {
                pin = newPin;
                System.out.println("PIN changed successfully.");
            }
        } else {
            System.out.println("Invalid old PIN. Please try again.");
        }
    }

    public void viewTransactionHistory(int enteredPin) {
        if (enteredPin == pin) {
            if (transactionHistory.isEmpty()) { // Added check for empty history
                System.out.println("No transactions to display.");
            } else {
                for (String transaction : transactionHistory) {
                    System.out.println(transaction);
                }
            }
        } else {
            System.out.println("Invalid PIN. Please try again.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        
        ATM atm = new ATM("1000011", 1234, 0);
        Scanner scanner = new Scanner(System.in);   
        System.out.println("Welcome to the ATM! Please enter your Choice: ");  
       
       
        while (true) {
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Cash");
            System.out.println("3. Deposit Cash");
            System.out.println("4. Change PIN");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Enter  your choice: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter your PIN: ");
                    int pin = scanner.nextInt();
                    atm.checkBalance(pin);
                    break;
                case 2:
                    System.out.print("Enter your PIN: ");
                    pin = scanner.nextInt();
                    System.out.print("Enter the amount to withdraw: ");
                    double amount = scanner.nextDouble();
                    atm.withdrawCash(pin, amount);
                    break;
                case 3:
                    System.out.print("Enter your PIN: ");
                    pin = scanner.nextInt();
                    System.out.print("Enter the amount to deposit: ");
                    amount = scanner.nextDouble();
                    atm.depositCash(pin, amount);
                    break;
                case 4:
                    System.out.print("Enter your old PIN: ");
                    int oldPin = scanner.nextInt();
                    System.out.print("Enter your new PIN: ");
                    int newPin = scanner.nextInt();
                    atm.changePin(oldPin, newPin);
                    break;
                case 5:
                    System.out.print("Enter your PIN: ");
                    pin = scanner.nextInt();
                    atm.viewTransactionHistory(pin);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close(); // Added to close the scanner
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
