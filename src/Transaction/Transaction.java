package Transaction;

import java.time.LocalDateTime;

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
