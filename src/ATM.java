import java.util.Scanner;

public class ATM {

    public void menu() {
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Show Account Status");
        System.out.println("5. Exit"); 
    }

    public boolean logIN(int correctPin, int attempts, Scanner scanner) {

        do {
            System.out.print("Enter your 4-digit PIN: ");
            int enteredPin = scanner.nextInt();

            if (enteredPin == correctPin) {
                System.out.println("Login successful!");
                return true;
            } else {
                attempts--;

                System.out.println("Incorrect PIN. Attempts remaining: " + attempts);
            }

        } while (attempts > 0);

        System.out.println("Your account has been locked.");
        return false;
    }

    public void checkBalance(double balance) {
        System.out.println("Your balance is: $ " + balance);
    }

    public double depositMoney(double balance, double amount) {

        balance += amount;

        System.out.println("Deposit successful: $ " + amount);
        System.out.println("Your new balance is: $ " + balance);

        return balance;
    }

    public double withdrawMoney(double balance, double amount) {

        balance -= amount;

        System.out.println("Withdrawal successful: $ " + amount);
        System.out.println("Your new balance is: $ " + balance);

        if (balance == 0) {
            System.out.println("Warning: Your account is empty.");
        }

        return balance;
    }

    public void accountStatus(double balance) {

        System.out.println("Account Status:");

        if (balance >= 5000) {
            System.out.println("VIP Customer");
        } else if (balance >= 1000) {
            System.out.println("Regular Customer");
        } else {
            System.out.println("Low Balance");
        }
    }

    public static void main(String[] args) {

        int pin = 1234;
        int attempts = 3;
        double balance = 2500.75;
        int transactionCount = 0;

        Scanner scanner = new Scanner(System.in);

        ATM atm = new ATM();

        System.out.println("Welcome to the ATM!");

        boolean loginSuccessful = atm.logIN(
            pin, attempts, scanner
        );

        if (!loginSuccessful) {
            return;
        }

        while (true) {

            atm.menu();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    atm.checkBalance(balance);
                    break;

                case 2:
                    System.out.print("Enter deposit amount: $");
                    double deposit = scanner.nextDouble();

                    if (deposit <= 0) {
                        System.out.println("Invalid amount.");
                    } else {
                        balance = atm.depositMoney(balance, deposit);
                        transactionCount++;
                    }
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: $");
                    double withdraw = scanner.nextDouble();

                    if (withdraw == 0) {
                        System.out.println("Transaction cancelled.");

                    } else if (withdraw < 0) {
                        System.out.println("Invalid amount.");

                    } else if (withdraw > balance) {
                        System.out.println("Insufficient balance.");

                    } else {
                        balance = atm.withdrawMoney(balance, withdraw);
                        transactionCount++;
                    }
                    break;

                case 4:
                    atm.accountStatus(balance);
                    break;

                case 5:
                    System.out.println("Thank you for using our ATM.");
                    System.out.println(
                        "Total successful transactions: " + transactionCount
                    );

                    return;

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
}
