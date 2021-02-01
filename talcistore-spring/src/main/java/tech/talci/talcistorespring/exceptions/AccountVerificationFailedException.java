package tech.talci.talcistorespring.exceptions;

public class AccountVerificationFailedException extends RuntimeException {

    public AccountVerificationFailedException() {
    }

    public AccountVerificationFailedException(String message) {
        super(message);
    }

    public AccountVerificationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountVerificationFailedException(Throwable cause) {
        super(cause);
    }

    public AccountVerificationFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
