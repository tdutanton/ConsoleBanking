package Bank;

import Account.*;
import Customer.Customer;
import Transaction.*;
import Utils.BankNumberGenerator;
import Utils.IDGenerator;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Основной класс банковской системы.
 * <p>
 * Управляет клиентами, счетами и транзакциями.
 * Обеспечивает уникальность номеров счетов и генерацию ID клиентов
 * через внешние генераторы.
 * </p>
 */
public class Bank {
    private ArrayList<Customer> customers;
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;
    private final IDGenerator idGenerator;
    private final BankNumberGenerator numberGenerator;
    private final TransactionService transactionService;

    public Bank(IDGenerator idGenerator, BankNumberGenerator numberGenerator) {
        this.idGenerator = idGenerator;
        this.numberGenerator = numberGenerator;
        this.customers = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.transactionService = new TransactionService();
    }

    public Customer createCustomer(String fullName) {
        Customer customer = new Customer(idGenerator.nextId(), fullName);
        customers.add(customer);
        return customer;
    }

    public boolean hasSameNumber(Integer number) {
        return accounts.stream().anyMatch(a -> Objects.equals(a.getAccountNumber(), number));
    }

    public Account openDebitAccount(Customer owner) {
        Integer number;
        do {
            number = numberGenerator.bankAccountNumber();
        } while (hasSameNumber(number));
        DebitAccount account = new DebitAccount(number, 0.0, owner);
        accounts.add(account);
        return account;
    }

    public Account openCreditAccount(Customer owner, double creditLimit) {
        Integer number;
        do {
            number = numberGenerator.bankAccountNumber();
        } while (hasSameNumber(number));
        CreditAccount account = new CreditAccount(number, 0.0, owner, creditLimit);
        accounts.add(account);
        return account;
    }

    public Account findAccount(Integer accountNumber) {
        return accounts.stream()
                .filter(a -> Objects.equals(a.getAccountNumber(), accountNumber))
                .findFirst()
                .orElse(null);
    }

    public Customer findCustomer(String customerName) {
        return customers.stream()
                .filter(a -> Objects.equals(a.getFullName(), customerName))
                .findFirst()
                .orElse(null);
    }

    public boolean deposit(Integer accountNumber, double amount) {
        Transaction res = transactionService.performDeposit(this.findAccount(accountNumber), amount);
        transactions.add(res);
        return res.isSuccess();
    }

    public boolean withdraw(Integer accountNumber, double amount) {
        Transaction res = transactionService.performWithdraw(this.findAccount(accountNumber), amount);
        transactions.add(res);
        return res.isSuccess();
    }

    public boolean transfer(Integer from, Integer to, double amount) {
        Account f = findAccount(from);
        Account t = findAccount(to);
        Transaction res = transactionService.performTransfer(f, t, amount);
        transactions.add(res);
        return res.isSuccess();
    }

    public void printCustomerAccounts(int customerId) {
        boolean found = false;
        for (Account account : accounts) {
            Customer owner = account.getOwner();
            if (owner != null && owner.getId() != null && owner.getId() == customerId) {
                System.out.println(account);
                found = true;
            }
        }
        if (!found) {
            System.out.printf("У клиента с ID %d нет счетов%n", customerId);
        }
    }

    public void printTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("Транзакции отсутствуют");
            return;
        }
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public void printReport() {
        System.out.println("=== ОБЩИЙ ОТЧЁТ БАНКА ===");

        int debitCount = 0;
        double debitBalance = 0.0;
        int creditCount = 0;
        double creditBalance = 0.0;
        for (Account account : accounts) {
            if (account instanceof DebitAccount) {
                debitCount++;
                debitBalance += account.getBalance();
            } else if (account instanceof CreditAccount) {
                creditCount++;
                creditBalance += account.getBalance();
            }
        }
        System.out.println("Счета по типам:");
        if (debitCount > 0) {
            System.out.printf("  Количество счетов типа DEBIT: %d, баланс: %.2f%n", debitCount, debitBalance);
        }
        if (creditCount > 0) {
            System.out.printf("  Количество счетов типа CREDIT: %d, баланс: %.2f%n", creditCount, creditBalance);
        }

        int successful = 0;
        int failed = 0;
        for (Transaction tx : transactions) {
            if (tx.isSuccess()) {
                successful++;
            } else {
                failed++;
            }
        }
        System.out.println("Транзакции:");
        System.out.printf("  Успешных: %d%n", successful);
        System.out.printf("  Неуспешных: %d%n", failed);
        System.out.println("========================");
    }
}
