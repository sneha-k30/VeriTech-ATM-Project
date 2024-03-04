import java.util.HashMap;
import java.util.Scanner;

class ATM {
    private HashMap<Integer, Integer> userPINs;
    private HashMap<Integer, Double> userBalances;

    public ATM() {
        userPINs = new HashMap<>();
        userBalances = new HashMap<>();
    }

    public void addUser(int userId, int pin, double initialBalance) {
        userPINs.put(userId, pin);
        userBalances.put(userId, initialBalance);
    }

    public boolean verifyPIN(int userId, int pin) {
        Integer storedPIN = userPINs.get(userId);
        return storedPIN != null && storedPIN.equals(pin);
    }

    public double checkBalance(int userId) {
        return userBalances.getOrDefault(userId, 0.0);
    }

    public boolean withdraw(int userId, double amount) {
        if (userBalances.containsKey(userId) && userBalances.get(userId) >= amount) {
            userBalances.put(userId, userBalances.get(userId) - amount);
            return true;
        }
        return false;
    }

    public void changePIN(int userId, int newPIN) {
        userPINs.put(userId, newPIN);
    }
}

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.addUser(1234, 4321, 10000.0); // Add a user with ID 1234, PIN 4321, and initial balance $10000

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();

        if (atm.verifyPIN(userId, pin)) {
            System.out.println("PIN verified.");

            // Display menu
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Change PIN");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    double balance = atm.checkBalance(userId);
                    System.out.println("Your balance is: $" + balance);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: $");
                    double amount = scanner.nextDouble();
                    if (atm.withdraw(userId, amount))
                        System.out.println("Amount withdrawn successfully.");
                    else
                        System.out.println("Insufficient balance.");
                    break;
                case 3:
                    System.out.print("Enter new PIN: ");
                    int newPIN = scanner.nextInt();
                    atm.changePIN(userId, newPIN);
                    System.out.println("PIN changed successfully.");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } else {
            System.out.println("Invalid User ID or PIN.");
        }

        scanner.close();
    }
}
