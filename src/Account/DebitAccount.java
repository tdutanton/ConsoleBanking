package Account;
import Customer.*;

public class DebitAccount extends Account {
    public DebitAccount(Integer number, long balance, Customer owner) {
        super(number, balance, owner);
    }

    @Override
    protected boolean isWithdrawPossible(long amount) {
        return getBalance() >= amount;
    }
}
