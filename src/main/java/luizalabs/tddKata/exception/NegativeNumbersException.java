package luizalabs.tddKata.exception;

public class NegativeNumbersException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;

    public NegativeNumbersException(String message) {
        super();
        this.setMessage(message);
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
