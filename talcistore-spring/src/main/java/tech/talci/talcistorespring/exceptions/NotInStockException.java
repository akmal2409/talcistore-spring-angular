package tech.talci.talcistorespring.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class NotInStockException extends RuntimeException {

    public NotInStockException() {
    }

    public NotInStockException(String message) {
        super(message);
    }

    public NotInStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotInStockException(Throwable cause) {
        super(cause);
    }

    public NotInStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
