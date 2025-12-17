package Interaction;

import Account.Account;
import Bank.Bank;
import Customer.Customer;

import java.util.Scanner;

public class Interaction {
    private final Bank bank;
    private final Scanner scanner;

    public Interaction(Bank bank, Scanner scanner) {
        this.bank = bank;
        this.scanner = scanner;
    }

    public void createCustomer() {
        System.out.print("Введите полное имя клиента: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        Customer customer = bank.createCustomer(name);
        System.out.printf("Создан клиент: %s%n", customer.getFullName());
    }

    public int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Пожалуйста, введите число: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public void openDebitAccount() {
        System.out.print("Введите полное имя клиента: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        Customer customer = bank.findCustomer(name);
        if (customer != null) {
            Account account = bank.openDebitAccount(customer);
            System.out.printf("Счёт для клиента %s открыт. № счета %d%n", account.getOwner().getFullName(), account.getAccountNumber());
        } else {
            System.out.printf("Клиент %s не найдет. Счет не удалось открыть.%n", name);
        }
    }
}
