package com.integrador.client.Exception;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
@Data
public class HandlerResponseException extends ResponseStatusException {
    private String message;
    private HttpStatus status;
    public HandlerResponseException(HttpStatus status, String message){
        super(status,message);
        this.status = status;
        this.message = message;
    }
}
