package company.exceptions;

public class SendMoneyException extends RuntimeException {
    public SendMoneyException(String message) {
        super(message);
    }
}
