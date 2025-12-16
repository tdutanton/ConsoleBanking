package Bank;

import Account.Account;
import Customer.Customer;
import Transaction.Transaction;
import Utils.IDGenerator;
import Utils.SequentialIDGenerator;

import java.util.ArrayList;

public class Bank {
    private ArrayList<Customer> customers;
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;
    private IDGenerator idGenerator = new SequentialIDGenerator();

    public Customer createCustomer(String fullName) {
        return new Customer(idGenerator.nextId(), fullName);
    }
}
