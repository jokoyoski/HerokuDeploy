package com.laundryservice.laundryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordNotEqualException  extends RuntimeException {
    public PasswordNotEqualException(String message) {
        super(message);
    }
}
