package Account;
import Customer.*;

/**
 * Дебетовый банковский счет
 * Является дочерним классом абстрактного класса {@link Account}
 */
public class DebitAccount extends Account {
    public DebitAccount(Integer number, long balance, Customer owner) {
        super(number, balance, owner);
    }

    @Override
    protected boolean isWithdrawPossible(long amount) {
        return getBalance() >= amount;
    }
}
