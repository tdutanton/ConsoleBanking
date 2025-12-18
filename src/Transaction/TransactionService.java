package Transaction;

import Account.*;

import java.time.LocalDateTime;

/**
 * Сервис для выполнения банковских операций и создания записей о транзакциях.
 * <p>
 * Обеспечивает инкапсуляцию логики выполнения операций (депозит, снятие, перевод)
 * и автоматическое создание соответствующих объектов {@link Transaction}
 * с фиксацией времени, статуса и сообщения.
 * </p>
 */
public class TransactionService {

    public Transaction performDeposit(Account account, double amount) {
        Transaction tx = new Transaction(Type.DEPOSIT, amount, null, account.getAccountNumber());
        OperationResult result = account.deposit(amount);
        tx.setTimestamp(LocalDateTime.now());
        tx.setSuccess(result.isSuccess());
        tx.setMessage(result.getErrorMessage());
        return tx;
    }

    public Transaction performWithdraw(Account account, double amount) {
        Transaction tx = new Transaction(Type.WITHDRAW, amount, account.getAccountNumber(), null);
        OperationResult result = account.withdraw(amount);
        tx.setTimestamp(LocalDateTime.now());
        tx.setSuccess(result.isSuccess());
        tx.setMessage(result.getErrorMessage());
        return tx;
    }

    public Transaction performTransfer(Account from, Account to, double amount) {
        Transaction tx = new Transaction(
                Type.TRANSFER,
                amount,
                from.getAccountNumber(),
                to.getAccountNumber()
        );
        OperationResult result = from.transfer(to, amount);
        tx.setTimestamp(LocalDateTime.now());
        tx.setSuccess(result.isSuccess());
        tx.setMessage(result.getErrorMessage());
        return tx;
    }
}