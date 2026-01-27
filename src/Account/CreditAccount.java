package Account;
import Customer.*;

public class CreditAccount extends Account{
    private final long creditLimit;

    public CreditAccount(Integer number, long balance, Customer owner, long limit) {
        super(number, balance, owner);
        this.creditLimit = limit;
    }

    public long getCreditLimit() {
        return this.creditLimit;
    }

    @Override
    protected boolean isWithdrawPossible(long amount) {
        return (getBalance() - amount) >= (this.creditLimit * -1);
    }
}
