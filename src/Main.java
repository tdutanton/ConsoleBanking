import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в консольный банк!\n");

        while (true) {
            showMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    createCustomer();
                    break;
                case 2:
                    openAccount();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
                    printCustomerAccounts();
                    break;
                case 6:
                    printReport();
                    break;
                case 0:
                    System.out.println("Спасибо за использование банка. До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.\n");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n--- МЕНЮ ---");
        System.out.print("Выберите действие: ");
        System.out.println("1.	Создать клиента");
        System.out.println("2.	Открыть дебетовый счёт");
        System.out.println("3.	Открыть кредитный счёт");
        System.out.println("4.	Пополнить");
        System.out.println("5.	Снять");
        System.out.println("6.	Перевести");
        System.out.println("7.	Показать счета клиента");
        System.out.println("8.	Показать транзакции");
        System.out.println("9.	Отчёт банка");
        System.out.println("0.	Выход");
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Пожалуйста, введите число: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void createCustomer() {
        System.out.print("Введите имя клиента: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        // Customer customer = bank.createCustomer(name);
        System.out.println("Клиент '" + name + "' создан.");
    }

    private static void openAccount() {
        System.out.print("Введите ID клиента: ");
        int customerId = getIntInput();
        // bank.openDebitAccount(...);
        System.out.println("Счёт для клиента ID=" + customerId + " открыт.");
    }

    private static void deposit() {
        System.out.print("Введите номер счёта: ");
        int accountNumber = getIntInput();
        System.out.print("Введите сумму пополнения: ");
        double amount = getDoubleInput();
        // bank.deposit(accountNumber, amount);
        System.out.println("Счёт " + accountNumber + " пополнен на " + amount);
    }

    private static void withdraw() {
        System.out.print("Введите номер счёта: ");
        int accountNumber = getIntInput();
        System.out.print("Введите сумму снятия: ");
        double amount = getDoubleInput();
        // bank.withdraw(accountNumber, amount);
        System.out.println("Со счёта " + accountNumber + " снято " + amount);
    }

    private static void printCustomerAccounts() {
        System.out.print("Введите ID клиента: ");
        int customerId = getIntInput();
        // bank.printCustomerAccounts(customerId);
        System.out.println("Счета клиента ID=" + customerId + " показаны.");
    }

    private static void printReport() {
        // bank.printReport();
        System.out.println("Общий отчёт сформирован.");
    }

    private static double getDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Пожалуйста, введите корректную сумму: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}