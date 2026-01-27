package Account;
import Customer.*;

public abstract class Account {
    private final Integer accountNumber;
    private long balance;
    private final Customer owner;

    public Account(Integer number, long balance, Customer owner) {
        this.accountNumber = number;
        this.balance = balance;
        this.owner = owner;
    }

    public OperationResult deposit(long amount) {
        if (!isAmountPositive(amount)) {
            return OperationResult.failure("Сумма депозита должна быть положительной");
        }
        this.balance += amount;
        return OperationResult.success();
    }

    public OperationResult withdraw(long amount) {
        if (!isAmountPositive(amount)) {
            return OperationResult.failure("Сумма снятия должна быть положительной");
        }
        if (!isWithdrawPossible(amount)) {
            return OperationResult.failure("Недостаточно средств на счёте");
        }
        this.balance -= amount;
        return OperationResult.success();
    }

    public OperationResult transfer(Account to, long amount) {
        if (!isAmountPositive(amount)) {
            return OperationResult.failure("Сумма перевода должна быть положительной");
        }
        if (!isWithdrawPossible(amount)) {
            return OperationResult.failure("Недостаточно средств для перевода");
        }
        this.balance -= amount;
        to.balance += amount;
        return OperationResult.success();
    }

    protected boolean isAmountPositive(long amount) {
        return amount > 0;
    }

    protected boolean isWithdrawPossible(long amount) {
        return this.balance > amount;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public Customer getOwner() { return this.owner; }
    public long getBalance() { return this.balance; }

    @Override
    public String toString() {
        return String.format("Банковский счет № %d, баланс: %.2f", getAccountNumber(), getBalance() / 100.0);
    }
}
