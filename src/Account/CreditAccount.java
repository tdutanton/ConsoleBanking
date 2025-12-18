package Account;
import Customer.*;

public class CreditAccount extends Account{
    private final double creditLimit;

    public CreditAccount(Integer number, double balance, Customer owner, double limit) {
        super(number, balance, owner);
        this.creditLimit = limit;
    }

    public double getCreditLimit() {
        return this.creditLimit;
    }

    @Override
    protected boolean isWithdrawPossible(double amount) {
        return (getBalance() - amount) >= (this.creditLimit * -1);
    }
}
