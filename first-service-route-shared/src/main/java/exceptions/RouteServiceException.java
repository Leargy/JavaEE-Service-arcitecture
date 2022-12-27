package exceptions;

import java.io.Serializable;

public class RouteServiceException extends Exception implements Serializable {
    private String message;

    public RouteServiceException(String message) {
        this.message = message;
    }
    public RouteServiceException() {}

    @Override
    public String getMessage() {
        return message;
    }
}
