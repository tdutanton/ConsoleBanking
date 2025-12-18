package Interaction;

import Account.*;
import Bank.Bank;
import Customer.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс для взаимодействия с пользователем через консоль.
 * <p>
 * Реализует меню управления банковской системой и обработку пользовательского ввода.
 * Связывает действия пользователя с методами {@link Bank}.
 * </p>
 */
public class Interaction {
    private final Bank bank;
    private final Scanner scanner;
    private final Map<Integer, Runnable> menuActions;

    private static final String END_MSG = "< Конец сообщения >%n%n";

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
        map.put(4, this::deposit);
        map.put(5, this::withdraw);
        map.put(6, this::transfer);
        map.put(7, this::showCustomerAccounts);
        map.put(8, this::showTransactions);
        map.put(9, this::showBankReport);
        return map;
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
                System.out.println("Выход из консольного банка.");
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
        System.out.printf(END_MSG);
    }

    public void openDebitAccount() {
        System.out.print("Введите полное имя клиента: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        Customer customer = bank.findCustomer(name);
        if (customer != null) {
            Account account = bank.openDebitAccount(customer);
            if (account instanceof DebitAccount) {
                System.out.printf("Дебетовый счёт для клиента %s открыт. № счета %d%n", account.getOwner().getFullName(), account.getAccountNumber());
            }
        } else {
            System.out.printf("Клиент %s не найдет. Счет не удалось открыть.%n", name);
        }
        System.out.printf(END_MSG);
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
        System.out.printf(END_MSG);
    }

    public void deposit() {
        System.out.print("Введите номер счёта: ");
        int accountNumber = getIntInput();
        Account account = bank.findAccount(accountNumber);
        if (account != null) {
            System.out.print("Введите сумму пополнения: ");
            double amount = getDoubleInput();
            if (bank.deposit(accountNumber, amount)) {
                System.out.printf("Счёт %d пополнен на %.2f руб.%n", accountNumber, amount);
            } else {
                System.out.printf("Пополнение не удалось.%n");
            }
        } else {
            System.out.printf("Клиент с номером счета %d не найдет.%n", accountNumber);
        }
        System.out.printf(END_MSG);
    }

    public void withdraw() {
        System.out.print("Введите номер счёта: ");
        int accountNumber = getIntInput();
        Account account = bank.findAccount(accountNumber);
        if (account != null) {
            System.out.print("Введите сумму снятия: ");
            double amount = getDoubleInput();
            if (bank.withdraw(accountNumber, amount)) {
                System.out.printf("Со счета %d снято %.2f руб.%n", accountNumber, amount);
            } else {
                System.out.printf("Снятие не удалось.%n");
            }
        } else {
            System.out.printf("Клиент с номером счета %d не найдет.%n", accountNumber);
        }
        System.out.printf(END_MSG);
    }

    public void transfer() {
        System.out.print("Введите номер счёта отправителя: ");
        int accountNumberFrom = getIntInput();
        Account accountFrom = bank.findAccount(accountNumberFrom);
        System.out.print("Введите номер счёта получателя: ");
        int accountNumberTo = getIntInput();
        Account accountTo = bank.findAccount(accountNumberTo);
        if (accountFrom != null && accountTo != null) {
            System.out.print("Введите сумму перевода: ");
            double amount = getDoubleInput();
            if (bank.transfer(accountNumberFrom, accountNumberTo, amount)) {
                System.out.printf("Со счета %d на счет %d переведено %.2f руб.%n", accountNumberFrom, accountNumberTo, amount);
            } else {
                System.out.printf("Перевод не удался.%n");
            }
        } else {
            System.out.printf("Клиент(-ы) не найден(-ы).%n");
        }
        System.out.printf(END_MSG);
    }

    public void showCustomerAccounts() {
        System.out.print("Введите полное имя клиента: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        Customer customer = bank.findCustomer(name);
        if (customer != null) {
            bank.printCustomerAccounts(customer.getId());
        } else {
            System.out.printf("Клиент %s не найдет.%n", name);
        }
        System.out.printf(END_MSG);
    }

    public void showTransactions() {
        System.out.println("Отчет по транзакциям");
        bank.printTransactions();
        System.out.printf(END_MSG);
    }

    public void showBankReport() {
        System.out.println("Отчет банка сформирован");
        bank.printReport();
        System.out.printf(END_MSG);
    }
}
