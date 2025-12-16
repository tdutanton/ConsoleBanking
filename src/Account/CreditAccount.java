package Account;
import Customer.*;

public class CreditAccount extends Account{
    private Integer accountNumber;
    private double balance;
    private Customer owner;
    private final double creditLimit;

    public CreditAccount(Integer number, double balance, Customer owner, double limit) {
        super(number, balance, owner);
        this.creditLimit = limit;
    }

    @Override
    protected boolean isWithdrawPossible(double amount) {
        return (this.balance - amount) >= (this.creditLimit * -1);
    }
}
