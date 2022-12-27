package exceptions;

import java.io.Serializable;

public class CRUDServiceException extends Exception implements Serializable {

    private String message;
    private Integer status;

    public CRUDServiceException(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }
}
