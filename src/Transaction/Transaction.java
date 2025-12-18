package Transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Класс, представляющий запись о банковской транзакции.
 * <p>
 * Содержит тип операции, сумму, участвующие счета, временную метку,
 * статус выполнения и поясняющее сообщение.
 * </p>
 */
public class Transaction {
    private Type type;
    private double amount;
    private Integer fromAccountNumber;
    private Integer toAccountNumber;
    private LocalDateTime timestamp;
    private boolean success;
    private String message;

    public Transaction(Type type, double amount, Integer from, Integer to, LocalDateTime timestamp, boolean success, String msg) {
        this.type = type;
        this.amount = amount;
        this.fromAccountNumber = from;
        this.toAccountNumber = to;
        this.timestamp = timestamp;
        this.success = success;
        this.message = msg;
    }

    public Transaction(Type type, double amount, Integer from, Integer to) {
        this.type = type;
        this.amount = amount;
        this.fromAccountNumber = from;
        this.toAccountNumber = to;
    }

    private static final Map<Type, String> nameOperations = Map.of(
            Type.DEPOSIT, "Пополнение",
            Type.TRANSFER, "Перевод между счетами",
            Type.WITHDRAW, "Снятие"
    );

    @Override
    public String toString() {
        return String.format("Операция: %s, сумма: %.2f руб., от счета № %d на счет %d, " +
                "дата-время: %s, статус: %s, статусное сообщение: %s",
                nameOperations.get(this.type), this.amount,
                this.getFromAccountNumber(),
                (this.getToAccountNumber() != null ? this.getToAccountNumber() : null),
                this.getTimestamp().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")), (this.isSuccess() ? "Успешно" : "Неуспешно"),
                this.getMessage());
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(Integer fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public Integer getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(Integer toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
