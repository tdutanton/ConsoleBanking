package Transaction;

/**
 * Неизменяемый класс, представляющий результат банковской операции.
 * <p>
 * Используется для безопасного возврата статуса операции и, при необходимости,
 * сообщения об ошибке.
 * </p>
 */
public class OperationResult {
    private final boolean success;
    private final String errorMessage;

    private OperationResult(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public static OperationResult success() {
        return new OperationResult(true, "OK");
    }

    public static OperationResult failure(String reason) {
        return new OperationResult(false, reason);
    }

    public boolean isSuccess() { return success; }
    public String getErrorMessage() { return errorMessage; }
}