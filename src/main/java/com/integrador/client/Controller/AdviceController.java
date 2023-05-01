package com.integrador.client.Controller;

import com.integrador.client.DTO.ErrorDTO;
import com.integrador.client.Exception.ClientNotFoundException;
import com.integrador.client.Exception.HandlerResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(value = HandlerResponseException.class)
    public ResponseEntity<ErrorDTO> handlerResponseException(HandlerResponseException ex){
        ErrorDTO error = ErrorDTO.builder().code(String.valueOf(ex.getStatus().value())).messaje(ex.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(value = ClientNotFoundException.class)
    public ResponseEntity<ErrorDTO> clientNotFoundException(ClientNotFoundException ex){
        ErrorDTO error = ErrorDTO.builder().code(String.valueOf(ex.getStatus().value())).messaje(ex.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
