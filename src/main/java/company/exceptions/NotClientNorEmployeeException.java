package company.exceptions;

public class NotClientNorEmployeeException extends Exception {
    public NotClientNorEmployeeException(String message) {
        super(message);
    }
}
