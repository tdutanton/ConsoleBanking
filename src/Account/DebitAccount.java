package Account;
import Customer.*;

/**
 * Дебетовый банковский счет
 * Является дочерним классом абстрактного класса {@link Account}
 */
public class DebitAccount extends Account {
    public DebitAccount(Integer number, double balance, Customer owner) {
        super(number, balance, owner);
    }

    @Override
    protected boolean isWithdrawPossible(double amount) {
        return getBalance() >= amount;
    }
}
