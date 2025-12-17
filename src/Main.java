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
        System.out.println("Добро пожаловать в консольный банк!\n");
        interaction.runMenu();
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