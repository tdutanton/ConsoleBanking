import Bank.Bank;
import Interaction.Interaction;
import Utils.BankNumberGenerator;
import Utils.IDGenerator;
import Utils.MathRandomGenerator;
import Utils.SequentialIDGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final IDGenerator idGenerator = new SequentialIDGenerator();
        final BankNumberGenerator bankNumberGenerator = new MathRandomGenerator();
        final Bank bank = new Bank(idGenerator, bankNumberGenerator);
        final Interaction interaction = new Interaction(bank, scanner);

        final Map<Integer, Runnable> menuActions = new HashMap<>() {{
            put(1, interaction::createCustomer);
            put(2, interaction::openDebitAccount);
            put(0, () -> {
                System.out.println("Спасибо за использование банка. До свидания!");
                System.exit(0);
            });
        }};

        System.out.println("Добро пожаловать в консольный банк!\n");
        runMenu(scanner, interaction, menuActions);
       }


    private static void runMenu(Scanner scanner, Interaction interaction, Map<Integer, Runnable> menuActions) {
        boolean[] shouldExit = { false };
        Map<Integer, Runnable> actions = new HashMap<>(menuActions);
        actions.put(0, () -> {
            System.out.println("Спасибо за использование банка. До свидания!");
            shouldExit[0] = true;
        });
        while (!shouldExit[0]) {
            showMenu();
            int choice = interaction.getIntInput();
            Runnable action = actions.get(choice);
            if (action != null) {
                action.run();
            } else {
                System.out.println("Неверный выбор. Попробуйте снова.\n");
            }
        }
    }

    private static void showMenu() {
        System.out.println("--- МЕНЮ ---");
        System.out.println("Выберите действие: ");
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
/*
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
    }*/
}