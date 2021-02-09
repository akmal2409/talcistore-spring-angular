package tech.talci.talcistorespring.dto.errorHandlers;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ApiError {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timeStamp;
    private String message;
    private String debugMessage;
    private List<ApiSubError> subErrors;

    private ApiError() {
        timeStamp = LocalDateTime.now();
    }

    public static ApiError withStatus(HttpStatus status) {
        ApiError error = new ApiError();
        error.setStatus(status);
        return error;
    }

    public static ApiError withStatusAndMessage(HttpStatus status, String message, Throwable ex) {
        ApiError error = new ApiError();
        error.setStatus(status);
        error.setMessage(message);
        error.setDebugMessage(ex.getLocalizedMessage());
        return error;
    }
}
