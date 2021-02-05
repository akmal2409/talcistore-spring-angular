package tech.talci.talcistorespring.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class CategoryManipulationException extends RuntimeException {
    public CategoryManipulationException() {
    }

    public CategoryManipulationException(String message) {
        super(message);
    }

    public CategoryManipulationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryManipulationException(Throwable cause) {
        super(cause);
    }

    public CategoryManipulationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
