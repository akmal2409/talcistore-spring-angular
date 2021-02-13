package tech.talci.talcistorespring.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class InvalidQueryFilterException extends RuntimeException {
    public InvalidQueryFilterException() {
    }

    public InvalidQueryFilterException(String message) {
        super(message);
    }

    public InvalidQueryFilterException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidQueryFilterException(Throwable cause) {
        super(cause);
    }

    public InvalidQueryFilterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
