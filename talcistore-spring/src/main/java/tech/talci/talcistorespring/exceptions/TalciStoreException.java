package tech.talci.talcistorespring.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class TalciStoreException extends RuntimeException {
    public TalciStoreException() {
    }

    public TalciStoreException(String message) {
        super(message);
    }

    public TalciStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public TalciStoreException(Throwable cause) {
        super(cause);
    }

    public TalciStoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
