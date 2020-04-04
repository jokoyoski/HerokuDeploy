package com.laundryservice.laundryservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityException extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object>handleDuplicateExceptionHandler(DuplicateRecordException ex, WebRequest request){
        ServerErrorResponse exceptionResponse=  new ServerErrorResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object>handlePasswordNotEqualExceptionHandler(PasswordNotEqualException ex, WebRequest request){
        PasswordNotEqualException exceptionResponse=  new PasswordNotEqualException(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


}
