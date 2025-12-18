package Interaction;

import Account.*;
import Bank.Bank;
import Customer.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Interaction {
    private final Bank bank;
    private final Scanner scanner;
    private final Map<Integer, Runnable> menuActions;

    public Interaction(Bank bank, Scanner scanner) {
        this.bank = bank;
        this.scanner = scanner;
        this.menuActions = createMenuActions();
    }

    private Map<Integer, Runnable> createMenuActions() {
        Map<Integer, Runnable> map = new HashMap<>();
        map.put(1, this::createCustomer);
        map.put(2, this::openDebitAccount);
        map.put(3, this::openCreditAccount);
        return map;
    }

    public int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Пожалуйста, введите число: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private double getDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Пожалуйста, введите корректную сумму: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    public void createCustomer() {
        System.out.print("Введите полное имя клиента: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        Customer customer = bank.createCustomer(name);
        System.out.printf("Создан клиент: %s%n", customer.getFullName());
    }

    public void openDebitAccount() {
        System.out.print("Введите полное имя клиента: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        Customer customer = bank.findCustomer(name);
        if (customer != null) {
            Account account = bank.openDebitAccount(customer);
            if (account instanceof CreditAccount resAccount) {
            System.out.printf("Дебетовый счёт для клиента %s открыт. № счета %d%n", account.getOwner().getFullName(), account.getAccountNumber());
            }
        } else {
            System.out.printf("Клиент %s не найдет. Счет не удалось открыть.%n", name);
        }
    }

    public void openCreditAccount() {
        System.out.print("Введите полное имя клиента: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        Customer customer = bank.findCustomer(name);
        if (customer != null) {
            System.out.print("Введите кредитный лимит (руб.).");
            double limit = getDoubleInput();
            Account account = bank.openCreditAccount(customer, limit);
            if (account instanceof CreditAccount resAccount) {
                System.out.printf("Кредитный счёт для клиента %s открыт с лимитом %.2f. № счета %d%n",
                        resAccount.getOwner().getFullName(),
                        resAccount.getCreditLimit(),
                        resAccount.getAccountNumber());
            }
        } else {
            System.out.printf("Клиент %s не найдет. Счет не удалось открыть.%n", name);
        }
    }

    public void showMenu() {
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

    public void runMenu() {
        boolean shouldExit = false;
        while (!shouldExit) {
            showMenu();
            int choice = getIntInput();

            if (choice == 0) {
                System.out.println("Спасибо за использование банка. До свидания!");
                shouldExit = true;
            } else {
                Runnable action = menuActions.get(choice);
                if (action != null) {
                    action.run();
                } else {
                    System.out.println("Неверный выбор. Попробуйте снова.\n");
                }
            }
        }
    }
}
