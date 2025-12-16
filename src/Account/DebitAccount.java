package Account;
import Customer.*;

public class DebitAccount extends Account {
    private Integer accountNumber;
    private double balance;
    private Customer owner;

    public DebitAccount(Integer number, double balance, Customer owner) {
        super(number, balance, owner);
    }

    @Override
    protected boolean isWithdrawPossible(double amount) {
        return this.balance >= amount;
    }
}
