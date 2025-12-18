package Account;
import Customer.*;

public class DebitAccount extends Account {
    public DebitAccount(Integer number, double balance, Customer owner) {
        super(number, balance, owner);
    }

    @Override
    protected boolean isWithdrawPossible(double amount) {
        return getBalance() >= amount;
    }
}
